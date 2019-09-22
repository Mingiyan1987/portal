package ru.basanov.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.basanov.ajax.AdAjax;
import ru.basanov.model.Ad;
import ru.basanov.model.Category;
import ru.basanov.model.Company;
import ru.basanov.model.User;
import ru.basanov.service.AdService;
import ru.basanov.service.CategoryService;
import ru.basanov.service.CompanyService;
import ru.basanov.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("ads")
public class AdController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AdService adService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model uiModel) {
        Optional<Ad> ad = adService.get(id);
        uiModel.addAttribute("ad", ad);
        return "ad/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        Ad ad = new Ad();
        ad.setCompany(new Company());
        List<Category> categories = categoryService.getAll();
        uiModel.addAttribute("ad", ad).addAttribute("categories", categories);
        return "ad/add";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String add(Model uiModel, @ModelAttribute("ad") @Valid Ad ad, BindingResult bindingResult, @RequestParam("categoryId") Long categoryId, Locale locale, RedirectAttributes redirectAttributes){

        //Проверяем форму на наличие ошибок
        if(bindingResult.hasErrors() || categoryId.equals(0)){

            System.out.println("category is null");
            //Если ошибка найдена, то заново создаем объект ad для формы
            uiModel.addAttribute("ad", ad)
                    //список категорий для выбора категорий в форме
                    .addAttribute("categories", categoryService.getAll())
                    //и добавляем сообщение о результате добавления статьи
                    .addAttribute("message",messageSource.getMessage("ad_create_fail", new Object[]{}, locale) );
            return "ad/add";

        }
        //Получаем логин пользователя, публикующего статью
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        //По логину находим автора
        User user = userService.findByUserName(currentLogin);
        //Ищем категорию по id категории
        Category category = categoryService.get(categoryId);
        //Если валидация прошла успешно, то задаем категорию вновь созданной статьи
        ad.setCategory(category);
        //Устанавливаем автора
        // ad.setUser(user);
        //сохраняем статью
        adService.save(ad);
        //редиректим юзера на главную страницу, выводя сообщение об успехе добавления статьи
        redirectAttributes.addFlashAttribute("message", messageSource.getMessage("ad_create_success", new Object[]{}, locale));
        return "redirect:/";
    }

    @RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
    public String editForm(Model uiModel, @PathVariable("id") Long id, HttpServletRequest request){

        Optional<Ad> ad = adService.get(id);
        List<Category> categories = categoryService.getAll();
        uiModel.addAttribute("reff", request.getRequestURI()).addAttribute("ad", ad).addAttribute("categories", categories);
        return "ad/add";
    }

    @RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
    public String edit(Model uiModel, @ModelAttribute("ad") @Valid  Ad ad, BindingResult bindingResult, @RequestParam("categoryId") Long categoryId, Locale locale, RedirectAttributes redirectAttributes, HttpServletRequest request){

        if(bindingResult.hasErrors()){


            uiModel.addAttribute("ad", ad)
                    .addAttribute("reff",request.getRequestURI())
                    .addAttribute("categories", categoryService.getAll())
                    .addAttribute("message",messageSource.getMessage("ad_create_fail", new Object[]{}, locale) );
            return "ad/add";

        }
        if(!categoryId.equals(0)){

            ad.setCategory(categoryService.get(categoryId));

        }

        //adService.update(ad);
        //редиректим юзера на главную страницу, выводя сообщение об успехе добавления статьи
        redirectAttributes.addFlashAttribute("message", messageSource.getMessage("ad_create_success", new Object[]{}, locale));
        return "redirect:/";
    }

    @RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){

        adService.deleteById(id);
        return "redirect:/";
    }

    /**
     * Метод обрабатывающий асинхронный запрос
     */
    @RequestMapping(value="/ads_ajax",method=RequestMethod.GET, produces="application/json")
    @ResponseBody
/**
 * @param pageCounter-текущая страница(блок из number статей)
 * @param number - количество статей в одном блоке
 * @param order - порядок сортировки(ASC-прямая, DESC-обратная)
 * @param orderBy - поле по которому происходит сортировка
 * @return объект класса AdAjax, который содержит список статей,
 * данный объект преобразовывается в JSON-формат
 */
    public AdAjax listAjax(@RequestParam("pageCounter") Integer pageCounter, @RequestParam("number") Integer number, @RequestParam("order") String order, @RequestParam("orderBy") String orderBy){

        //объект, который будет содержать информацию о сортировке
        Sort sort = null;

        if(order.equalsIgnoreCase("DESC")){
            //конструктор Sort принимает в качестве параметров тип сортировки и поле,
            //по которому будет происходить соритровка
            sort = new Sort(Sort.Direction.DESC, orderBy);

        }else{


            sort = new Sort(Sort.Direction.ASC, orderBy);
        }
        //конструктор принимает полную информацию о текущем блоке,количестве статей и сортировке
        PageRequest pageable = new PageRequest(pageCounter,number, sort);

        Page<Ad> adPage = adService.getALL(pageable);

        AdAjax responsive = new AdAjax();
        //из объекта Page возвращаем итератор и с помощью библиотеки google guava создаем списочный массив
        responsive.setAds(Lists.newArrayList(adPage.iterator()));

        return responsive;

    }


}

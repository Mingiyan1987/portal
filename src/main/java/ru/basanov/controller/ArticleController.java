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
import ru.basanov.ajax.ArticleAjax;
import ru.basanov.model.Article;
import ru.basanov.model.Author;
import ru.basanov.model.Category;
import ru.basanov.service.ArticleService;
import ru.basanov.service.CategoryService;
import ru.basanov.service.CompanyService;
import ru.basanov.service.AuthorService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model uiModel) {
        Optional<Article> article = articleService.get(id);
        uiModel.addAttribute("article", article);
        return "article/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(Model uiModel, HttpServletRequest request) {
        Article article = new Article();
        article.setAuthor(new Author());
        List<Category> categories = categoryService.getAll();
        uiModel.addAttribute("reff", request.getRequestURI()).addAttribute("categories", categories).addAttribute("articles", article);
        return "article/add";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String add(Model uiModel, @ModelAttribute("article") @Valid Article article, BindingResult bindingResult, @RequestParam("categoryId") Long categoryId, Locale locale, RedirectAttributes redirectAttributes){

        //Проверяем форму на наличие ошибок
        if(bindingResult.hasErrors() || categoryId.equals(0)){

            System.out.println("category is null");
            //Если ошибка найдена, то заново создаем объект article для формы
            uiModel.addAttribute("article", article)
                    //список категорий для выбора категорий в форме
                    .addAttribute("categories", categoryService.getAll())
                    //и добавляем сообщение о результате добавления статьи
                    .addAttribute("message",messageSource.getMessage("article_create_fail", new Object[]{}, locale) );
            return "article/add";

        }
        //Получаем логин пользователя, публикующего статью
        String currentLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        //По логину находим автора
        Author author = authorService.findByLogin(currentLogin);
        //Ищем категорию по id категории
        Category category = categoryService.get(categoryId);
        //Если валидация прошла успешно, то задаем категорию вновь созданной статьи
        article.setCategory(category);
        //Устанавливаем автора
        // article.setAuthor(author);
        //сохраняем статью
        articleService.save(article);
        //редиректим юзера на главную страницу, выводя сообщение об успехе добавления статьи
        redirectAttributes.addFlashAttribute("message", messageSource.getMessage("article_create_success", new Object[]{}, locale));
        return "redirect:/";
    }

    @RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
    public String editForm(Model uiModel, @PathVariable("id") Long id, HttpServletRequest request){

        Optional<Article> article = articleService.get(id);
        List<Category> categories = categoryService.getAll();
        uiModel.addAttribute("reff", request.getRequestURI()).addAttribute("article", article).addAttribute("categories", categories);
        return "article/add";
    }

    @RequestMapping(value="/{id}/edit", method=RequestMethod.POST)
    public String edit(Model uiModel, @ModelAttribute("article") @Valid Article article, BindingResult bindingResult, @RequestParam("categoryId") Long categoryId, Locale locale, RedirectAttributes redirectAttributes, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            uiModel.addAttribute("article", article)
                    .addAttribute("reff",request.getRequestURI())
                    .addAttribute("categories", categoryService.getAll())
                    .addAttribute("message",messageSource.getMessage("article_create_fail", new Object[]{}, locale) );
            return "article/add";
        }
        if(!categoryId.equals(0)){
            article.setCategory(categoryService.get(categoryId));
        }
        //articleService.update(article);
        //редиректим юзера на главную страницу, выводя сообщение об успехе добавления статьи
        redirectAttributes.addFlashAttribute("message", messageSource.getMessage("article_create_success", new Object[]{}, locale));
        return "redirect:/";
    }

    @RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        articleService.deleteById(id);
        return "redirect:/";
    }

    /**
     * Метод обрабатывающий асинхронный запрос
     */
    @RequestMapping(value="/articles_ajax",method=RequestMethod.GET, produces="application/json")
    @ResponseBody
/**
 * @param pageCounter-текущая страница(блок из number статей)
 * @param number - количество статей в одном блоке
 * @param order - порядок сортировки(ASC-прямая, DESC-обратная)
 * @param orderBy - поле по которому происходит сортировка
 * @return объект класса ArticleAjax, который содержит список статей,
 * данный объект преобразовывается в JSON-формат
 */
    public ArticleAjax listAjax(@RequestParam("pageCounter") Integer pageCounter, @RequestParam("number") Integer number, @RequestParam("order") String order, @RequestParam("orderBy") String orderBy){

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

        Page<Article> articlePage = articleService.getALL(pageable);

        ArticleAjax responsive = new ArticleAjax();
        //из объекта Page возвращаем итератор и с помощью библиотеки google guava создаем списочный массив
        responsive.setArticles(Lists.newArrayList(articlePage.iterator()));

        return responsive;

    }


}

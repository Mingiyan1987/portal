package ru.basanov.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.basanov.ajax.AdAjax;
import ru.basanov.model.Ad;
import ru.basanov.model.Category;
import ru.basanov.service.AdService;
import ru.basanov.service.CategoryService;

import java.util.Optional;

@Controller
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdService adService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model uiModel) {
        Category category = categoryService.get(id);
        uiModel.addAttribute("catgeory", category);
        return "categry/view";
    }

    @RequestMapping(value = "/{id}/ads_ajax", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public AdAjax viewAjax(@PathVariable("id") Long id, @RequestParam("pageCounter") Integer pageCounter, @RequestParam("number") Integer number,
                           @RequestParam("order") String order, @RequestParam("ordreBy") String orderBy) {
        Sort sort = null;
        if (order.equalsIgnoreCase("DESC")) {
            sort = new Sort(Sort.Direction.DESC, orderBy);
        } else {
            sort = new Sort(Sort.Direction.ASC, orderBy);
        }

        PageRequest pageable = new PageRequest(pageCounter, number, sort);
        Page<Ad> adPage = adService.getByCatgoryId(id, pageable);
        AdAjax responsive = new AdAjax();
        responsive.setAds(Lists.newArrayList(adPage.iterator()));
        return responsive;
    }
}

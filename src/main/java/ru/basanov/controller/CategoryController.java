package ru.basanov.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.basanov.ajax.ArticleAjax;
import ru.basanov.model.Article;
import ru.basanov.model.Category;
import ru.basanov.service.ArticleService;
import ru.basanov.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService adService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model uiModel) {
        Category category = categoryService.get(id);
        uiModel.addAttribute("catgeory", category);
        return "categry/view";
    }

    @RequestMapping(value = "/{id}/ads_ajax", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArticleAjax viewAjax(@PathVariable("id") Long id, @RequestParam("pageCounter") Integer pageCounter, @RequestParam("number") Integer number,
                                @RequestParam("order") String order, @RequestParam("ordreBy") String orderBy) {
        Sort sort = null;
        if (order.equalsIgnoreCase("DESC")) {
            sort = new Sort(Sort.Direction.DESC, orderBy);
        } else {
            sort = new Sort(Sort.Direction.ASC, orderBy);
        }

        PageRequest pageable = new PageRequest(pageCounter, number, sort);
        Page<Article> adPage = adService.getByCategoryId(id, pageable);
        ArticleAjax responsive = new ArticleAjax();
        responsive.setArticles(Lists.newArrayList(adPage.iterator()));
        return responsive;
    }
}

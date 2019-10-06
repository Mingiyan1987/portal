package ru.basanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.basanov.model.Author;
import ru.basanov.service.AuthorService;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model uiModel) {
        Author author = new Author();
        uiModel.addAttribute("author", author);
        return "author/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult, Model uiModel, RedirectAttributes redAttributes, Locale locale) {
        System.out.println(bindingResult.toString());
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", messageSource.getMessage("author_create_fail", new Object[]{}, locale));
            return "author/registration";
        }
        if (authorService.getByLogin(author.getLogin()) != null) {
            uiModel.addAttribute("message", messageSource.getMessage("author_login_exist", new Object[]{}, locale));
            return "author/registration";
        }
        authorService.save(author);
        return "redirect:/";
    }
}

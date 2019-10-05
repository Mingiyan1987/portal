package ru.basanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.basanov.model.Author;
import ru.basanov.service.AuthorService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model uiModel) {
        Author author = new Author();
        uiModel.addAttribute("author", author);
        return "security/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(Model uiModel, @ModelAttribute("author") @Valid Author author, BindingResult bindingResult, RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", messageSource.getMessage("author_create_fail", new Object[]{}, locale));
            return "security/registration";
        }
        if (authorService.getByLogin(author.getLogin()) != null) {
            uiModel.addAttribute("message", messageSource.getMessage("author_login_exist", new Object[]{}, locale));
            return "security/registration";
        }
        authorService.save(author);
        return "redirect:/";
    }
}

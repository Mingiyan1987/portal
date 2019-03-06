package ru.basanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        System.out.println("INDEX PAGE...");
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        System.out.println("Login");
        return "login";
    }

    @GetMapping(value = "/free")
    public String free() {
        System.out.println("free");
        return "free";
    }
}

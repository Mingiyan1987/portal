package ru.basanov.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

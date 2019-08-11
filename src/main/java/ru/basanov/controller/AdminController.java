package ru.basanov.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    @Secured({"ROLE_ADMINISTRATOR"})
    public String admin() {
        System.out.println("admin");
        return "admin";
    }
}

package ru.basanov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class    HelloController {

    @GetMapping
    public String printWelcome(ModelMap modelMap) {
        modelMap.addAttribute("message", "SPTING 3");
        return "hello";
    }
}

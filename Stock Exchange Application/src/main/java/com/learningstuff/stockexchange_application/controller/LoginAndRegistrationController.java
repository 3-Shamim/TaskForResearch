package com.learningstuff.stockexchange_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginAndRegistrationController {

    @GetMapping(value = "login")
    public ModelAndView loginView()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "Login");

        modelAndView.setViewName("view/Login");

        return modelAndView;
    }

}

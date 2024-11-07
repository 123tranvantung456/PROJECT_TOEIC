package com.javaweb.toeic.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
    @GetMapping(value = "/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("web/homepage");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("web/login");
    }

    @GetMapping(value = "/register")
    public ModelAndView register() {
        return new ModelAndView("web/register");
    }

    @GetMapping(value = "/check-email/{email}")
    public ModelAndView checkEmail() {
        return new ModelAndView("web/check-email");
    }

    @GetMapping(value = "/forgot-password")
    public ModelAndView forgotPassword() {
        return new ModelAndView("web/forgot-password");
    }
}

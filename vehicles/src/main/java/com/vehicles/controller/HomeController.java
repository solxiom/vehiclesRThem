package com.vehicles.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

//    @Autowired

    @RequestMapping(value = "/")
    public ModelAndView test(HttpServletResponse response) throws IOException {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/test")
    public ModelAndView qunit(HttpServletResponse response) throws IOException {
        return new ModelAndView("test");
    }
    public void initTestGames(){
        
    }
}

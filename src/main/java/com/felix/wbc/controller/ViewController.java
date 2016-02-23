package com.felix.wbc.controller;

import com.felix.wbc.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

/**
 * Created by fsoewito on 2/23/2016.
 */

@Controller
public class ViewController {

    @Autowired
    MainService mainService;

    @RequestMapping(
            value = ""
    )
    public String index(Model model){
        model.addAttribute("title", "Welcome to Spring Boot Web Service");
        model.addAttribute("time", Calendar.getInstance().getTime().toString());
        model.addAttribute("info", "Service is up and kicking");
        return "index";
    }

    @RequestMapping(
            value = "/hello"
    )
    public String hello(
            @RequestParam(required = false, defaultValue = "World") String name,
            Model model) {
        return mainService.hello(model, name);
    }
}

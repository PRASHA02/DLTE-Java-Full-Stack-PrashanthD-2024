package com.example.elementdashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//controller for thymeleaf html pages
@Controller
public class WebController {
    //dashboard
    @GetMapping("/")
    public String index(){
        return "index";
    }
    //displaying the cards
    @GetMapping("/soap")
    public String soap(){
        return "soap";
    }
    //updating the cards
    @GetMapping("/rest")
    public String rest(){
        return "rest";
    }
}

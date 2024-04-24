package com.example.elementdashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//controller for thymeleaf html pages
@Controller
public class WebController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/soap")
    public String soap(){
        return "soap";
    }
    @GetMapping("/rest")
    public String rest(){
        return "rest";
    }
}

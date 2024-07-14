package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TabsController {
    
    @GetMapping({"/","/inicio"})
    public String getHomePage(){
        return "inicio";
    }
}

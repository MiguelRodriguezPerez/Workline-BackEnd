package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sesion")
@Controller
public class LoginController {
    
    @GetMapping("/error")
    public String showError(){
        return "sesion/error";
    }

    @GetMapping("/signin")
    public String showLogin(){
        return "sesion/logInView";
    }

    @GetMapping("/signout")
    public String showLogout() {
        return "sesion/logOut";
    }
}

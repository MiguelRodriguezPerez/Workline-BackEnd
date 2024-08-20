package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/sesion")
@Controller
public class LoginController {
    
    @GetMapping("/error")
    public String showError(){
        return "sesion/error";
    }

    @GetMapping("/error404")
    public String show404(){
        return "sesion/pagina404";
    }

    @GetMapping("/error500")
    public String show500(){
        return "sesion/pagina500";
    }

    @GetMapping("/signin")
    public String showLogin(@RequestParam(required = false) String error, Model model){
        if(error != null) model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "sesion/logInView";
    }

    @GetMapping("/signout")
    public String showLogout() {
        return "sesion/logOut";
    }
}

package com.example.demo.controllers;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {

    @GetMapping("/get-csrf-token")
    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }
}

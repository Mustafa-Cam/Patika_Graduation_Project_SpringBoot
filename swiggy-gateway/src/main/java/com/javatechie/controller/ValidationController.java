package com.javatechie.controller;


import com.javatechie.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/validate")
    public void validateToken(@RequestParam String token) {
        jwtUtil.validateToken(token);
    }
}

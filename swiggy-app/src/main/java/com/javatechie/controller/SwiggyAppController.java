package com.javatechie.controller;

import com.javatechie.dto.OrderResponseDTO;
import com.javatechie.service.SwiggyAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swiggy")
public class SwiggyAppController {

    @Autowired
    private SwiggyAppService service;

    @GetMapping("/home") //deneme amaçlı kullandım.
    public String greetingMessage() {
        return service.greeting();
    }

}

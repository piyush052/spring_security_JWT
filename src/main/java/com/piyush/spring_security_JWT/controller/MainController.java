package com.piyush.spring_security_JWT.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/helllo")
    String hello(){
        return "<h1> HELLO </h1>";
    }
}

package com.example.cicddemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
 
    @GetMapping
    public String hello(){
        System.out.println("Inside..");
        return "Hello World.... New Changes from develop";
    }
    
}

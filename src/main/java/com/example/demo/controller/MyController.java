package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/myController")
public class MyController {
    @GetMapping(value = "/status")
    public String status() {
        return "ok";
    }
}
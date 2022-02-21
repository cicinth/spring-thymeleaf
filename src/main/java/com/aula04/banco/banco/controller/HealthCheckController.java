package com.aula04.banco.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthCheckController {

    @GetMapping("/health-check")
    @ResponseBody
    public String healthCheck(){
        return "Server running!";
    }
}

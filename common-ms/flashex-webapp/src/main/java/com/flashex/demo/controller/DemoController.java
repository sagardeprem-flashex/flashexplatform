package com.flashex.demo.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class DemoController {
    @GetMapping("/hello")
    public  String hello() {
        System.out.println("In demo controller");
        return "hi working";
    }

}
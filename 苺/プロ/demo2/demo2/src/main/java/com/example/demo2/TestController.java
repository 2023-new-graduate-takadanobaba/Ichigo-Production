package com.example.demo2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }  
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}

package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo2.repository.KaimonoListRepository;

@Controller
public class TestController {

    @Autowired
    KaimonoListRepository repository;

    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }  
    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("items",repository.findAll());
        return "test";
    }

    @GetMapping("/")
    public String calender(){
        return "calender";
    }
}

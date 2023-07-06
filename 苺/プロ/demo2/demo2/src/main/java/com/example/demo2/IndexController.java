package com.example.demo2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
    
    @GetMapping("/index")
    public String index(){
        return "index";
    } 

    @PostMapping("/login")
    public String login(String name,HttpSession session){

        session.getAttribute("user",name);
        return "redirect:/";
    }
}

package com.example.demo2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
    
    @GetMapping("/")
    public String index(){
        return "index";
    } 

    @PostMapping("/login")
    public String login(String name,HttpSession session){
        session.setAttribute("user",name);
        return "redirect:/calendar";
    }
}

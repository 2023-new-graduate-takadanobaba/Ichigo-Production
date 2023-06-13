package com.example.demo2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpSession;

@Controller
public class ListController{
@GetMapping("/list")
public String list(){
    return "list";
}


}
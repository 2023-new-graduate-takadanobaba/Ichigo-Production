package com.example.demo2;

@Controller
public class ListController{
@GetMapping("/list")
public String list(){
    return "list";
}


}
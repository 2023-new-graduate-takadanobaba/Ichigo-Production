package com.example.demo2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class CalenderController{
    /**
     * @return
     */
    @RequestMapping(path="/form",method=RequestMethod.GET)
    public String Form(){
            return"form";
    }







}

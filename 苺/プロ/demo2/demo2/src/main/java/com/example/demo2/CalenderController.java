package com.example.demo2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo2.entity.Listtoform;
import com.example.demo2.repository.ListtoformRepository;

import com.example.demo2.form.RegisterForm;

import jakarta.servlet.http.HttpSession;

@Controller
public class CalenderController{
    /**
     * @return
     */
    @Autowired
    ListtoformRepository repository;

     //formを表示する
    @RequestMapping(path="/form/{date}",method=RequestMethod.GET)
    public String Form(@PathVariable String date, Model model){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date listtoForms = sdFormat.parse(date);
            model.addAttribute("listtoForms",repository.findByCreateTime(listtoForms));
            

        }catch (ParseException e){
            
        }
        
        return "form";
    }

    //カレンダーを表示する
    @RequestMapping(path="/ichigo",method=RequestMethod.GET)
    public String ichigo(){
        return"calender";
    }

    //formの完了押したときに動く機能
    @RequestMapping(path="/",method=RequestMethod.POST)
    public String doRegister(RegisterForm form,HttpSession session){
        //CRUDで登録する記述をする
        return"calender";
    }





}

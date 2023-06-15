package com.example.demo2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo2.form.RegisterForm;

import jakarta.servlet.http.HttpSession;

@Controller
public class CalenderController{
    /**
     * @return
     */

     //formを表示する
    @RequestMapping(path="/form/{date}",method=RequestMethod.GET)
    public String Form(){
            return"form";
    }

    //カレンダーを表示する
    @RequestMapping(path="/ichigo",method=RequestMethod.GET)
    public String ichigo(){
        return"calender";
    }

    //formの完了押したときに動く機能
    @RequestMapping(path="/doRegister",method=RequestMethod.POST)
    public String doRegister(RegisterForm form,HttpSession session){
        //CRUDで登録する記述をする
        return"calender";
    }





}

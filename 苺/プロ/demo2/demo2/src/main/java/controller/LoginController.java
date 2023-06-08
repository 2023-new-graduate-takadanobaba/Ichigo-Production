package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @RequestMapping(path="/login",method=RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path="/login",method=RequestMapping.POST)
    public String doLogin(LoginForm loginForm,Model model){
        return "redirect:/";
    }
}

package com.example.demo2;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo2.form.RegisterForm;
import jakarta.servlet.http.HttpSession;

@Controller
public class CalenderController {
    /**
     * @return
     */
   

    // カレンダーを表示する
    @GetMapping("/calendar")
    public String ichigo() {
        return "calender";
    }

    // formの完了押したときに動く機能
    @PostMapping("/")
    public String doRegister(RegisterForm form, HttpSession session) {
        session.removeAttribute("pageDate");
        return "calender";
    }

}

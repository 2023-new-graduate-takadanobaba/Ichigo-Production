package com.example.demo2;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo2.entity.Bought;
import com.example.demo2.form.RegisterForm;
import com.example.demo2.repository.BoughtRepository;


import jakarta.servlet.http.HttpSession;

@Controller
public class CalenderController {
    /**
     * @return
     */
    @Autowired
    BoughtRepository repository;

    // カレンダーを表示する
    @GetMapping("/calendar")
        public String ichigo(HttpSession session) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M");
        String str = sdf.format(now);
        List<Bought> boughts = new ArrayList<>();
        boughts = repository.findByCreateTimeContainingAndUser(str, ((String) session.getAttribute("user")));
        int sum = 0;
        for (Bought bought : boughts) {
            sum += bought.getTotal();
        }
        session.setAttribute("sum", sum);
        return "calender";
    }
    // formの完了押したときに動く機能
    @PostMapping("/")
    public String doRegister(RegisterForm form, HttpSession session) {
        session.removeAttribute("pageDate");
        return "redirect:/calendar";
    }

}

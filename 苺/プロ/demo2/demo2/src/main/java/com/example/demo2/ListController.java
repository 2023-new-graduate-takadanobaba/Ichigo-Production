package com.example.demo2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo2.bean.KaimonoListBean;
import com.example.demo2.entity.KaimonoList;
import com.example.demo2.form.RegisterForm;
import com.example.demo2.repository.KaimonoListRepository;



@Controller
public class ListController{

@Autowired
KaimonoListRepository repository;



@GetMapping("/list")
public String list(){
    return "list";
}

@GetMapping("/regist")
public String regist(RegisterForm form, Model model){

KaimonoList kaimonolist = new KaimonoList();

 BeanUtils.copyProperties(form, kaimonolist, "id");
 kaimonolist=repository.save(kaimonolist);
 KaimonoListBean itemBean = new KaimonoListBean();
 BeanUtils.copyProperties(kaimonolist,itemBean);
 model.addAttribute("kaimonolists", itemBean);



    return "list";

}
}
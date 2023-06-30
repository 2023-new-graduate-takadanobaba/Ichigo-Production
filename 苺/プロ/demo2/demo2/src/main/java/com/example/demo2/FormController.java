package com.example.demo2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo2.entity.Bought;
import com.example.demo2.repository.BoughtRepository;

@Controller
public class FormController {
    @Autowired
    BoughtRepository repository;

    // formを表示する
    @RequestMapping(path = "/form/{date}", method = RequestMethod.GET)
    public String Form(@PathVariable String date, Model model) {

        model.addAttribute("boughts", repository.findByCreateTimeContaining(date));

        return "form";
    }

    // formのデータを削除する
    public void delete(Integer id) {

        // idを指定して、データベースからデータを削除する
        repository.deleteById(id);
    }

    // 行のデータの削除を行う
    @RequestMapping(path="/form-delate", method = RequestMethod.GET)
    public String delateDataString(Model model, int id) {
        Bought bought = repository.getReferenceById(id);
        // データベースのデータを削除する
        delete(bought.getId());

        // Formの一覧画面にリダイレクト
        return "redirect:/form/"+bought.getCreateTime();

    }

    @PostMapping("/update")
    public String update(@RequestParam("goodsname") List<String> goodsNames,
            @RequestParam("price") List<Integer> prices,
            @RequestParam("amount") List<Integer> amounts,
            @RequestParam("id") List<Integer> ids){
        Bought bought = new Bought();
        for(){
            
        }
        return "/";
    }
}

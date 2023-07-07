package com.example.demo2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo2.entity.Bought;
import com.example.demo2.repository.BoughtRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class FormController {
    @Autowired
    BoughtRepository repository;

    // formを表示する
    @GetMapping("/form/{date}")
    public String Form(@PathVariable String date, Model model, HttpSession session) {

        model.addAttribute("boughts", repository.findByCreateTimeContainingAndUser(date,((String) session.getAttribute("user"))));

        return "form";
    }

    // formのデータを削除する
    public void delete(Integer id) {

        // idを指定して、データベースからデータを削除する
        repository.deleteById(id);
    }

    // 行のデータの削除を行う
    @GetMapping("/form-delete")
    public String delateDataString(Model model, int id) {
        Bought bought = repository.getReferenceById(id);
        // データベースのデータを削除する
        delete(bought.getId());

        // Formの一覧画面にリダイレクト
        return "redirect:/form/" + bought.getCreateTime();

    }

    @PostMapping("/update")
    // RequestParamの意味...HTML上の要素を取得する
    public String update(@RequestParam("goodsname") List<String> goodsNames,
            @RequestParam("price") List<Integer> prices,
            @RequestParam("amount") List<Integer> amounts,
            @RequestParam("id") List<Integer> ids) {
        Bought bought = new Bought();
        for (int i = 0; i < goodsNames.size(); i++) {
            bought = repository.getReferenceById(ids.get(i));
            bought.setGoodsname(goodsNames.get(i));
            bought.setPrice(prices.get(i));
            bought.setAmount(amounts.get(i));
            bought.setTotal(prices.get(i) * amounts.get(i));
            // bought.setId(ids.get(i));
            bought = repository.save(bought);
        }
        return "redirect:/form/" + bought.getCreateTime();
    }

    @PostMapping("/addgoods")
    public String addgoods(@RequestParam("goodsname") String goodsNames,
            @RequestParam("price") int prices,
            @RequestParam("amount") int amounts,
            HttpSession session) {
        Bought bought = new Bought();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        String str = sdf.format(now);
        bought.setCreateTime(str);
        bought.setGoodsname(goodsNames);
        bought.setPrice(prices);
        bought.setAmount(amounts);
        bought.setTotal(prices * amounts);
        bought.setUser((String)session.getAttribute("user"));
        bought = repository.save(bought);
        return "redirect:/form/" + bought.getCreateTime();
    }
}

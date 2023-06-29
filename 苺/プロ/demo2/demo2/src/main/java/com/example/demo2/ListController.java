package com.example.demo2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo2.entity.Bought;
import com.example.demo2.entity.KaimonoList;
import com.example.demo2.form.RegisterForm;
import com.example.demo2.repository.BoughtRepository;
import com.example.demo2.repository.KaimonoListRepository;

@Controller
public class ListController {

    @Autowired
    KaimonoListRepository repository;

    @Autowired
    BoughtRepository boughtRepository;

    @GetMapping("/list")
    public String showItemList(Model model) {
        model.addAttribute("items", repository.findAll());

        return "list";
    }

    @PostMapping("/regist")
    public String regist(@RequestParam("check") List<Integer> checks,
            @RequestParam("goodsname") List<String> goodsNames,
            @RequestParam("price") List<Integer> prices,
            @RequestParam("amount") List<Integer> amounts) {

        List<KaimonoList> kaimonoLists = new ArrayList<>();
        List<Bought> boughts = new ArrayList<>();

        for (int i = 0; i < goodsNames.size(); i++) {

            if (checks.get(i) == 1) {
                Bought bought = new Bought();
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
                String str = sdf.format(now);
                bought.setCreateTime(str);
                bought.setGoodsname(goodsNames.get(i));
                bought.setPrice(prices.get(i));
                bought.setAmount(amounts.get(i));

                boughts.add(bought);

                boughts = boughtRepository.saveAll(boughts);
            } else if (checks.get(i) == 0) {
                KaimonoList kaimonoList = new KaimonoList();
                kaimonoList.setGoodsname(goodsNames.get(i));
                kaimonoList.setPrice(prices.get(i));
                kaimonoList.setAmount(amounts.get(i));

                kaimonoLists.add(kaimonoList);
                kaimonoLists = repository.saveAll(kaimonoLists);
            }

        }

        return "list";
    }

    @RequestMapping(path = "/gorori")
    public String delete(RegisterForm form) {
        repository.deleteById(form.getId());
        return "redirect:/list";
    }
}
package com.example.demo2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo2.entity.Listtoform;
import com.example.demo2.entity.KaimonoList;
import com.example.demo2.repository.ListtoformRepository;
import com.example.demo2.repository.KaimonoListRepository;

@Controller
public class ListController {

    @Autowired
    KaimonoListRepository repository;

    @Autowired
    ListtoformRepository ListtoformRepository;

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
        List<Listtoform> Listtoforms = new ArrayList<>();

        for (int i = 0; i < goodsNames.size(); i++) {

            if (checks.get(i) == 1) {
                Listtoform Listtoform = new Listtoform();
                Date now = new Date();
                // kaimonoList.setId(i + 1);
                Listtoform.setCreateTime(now);
                Listtoform.setGoodsname(goodsNames.get(i));
                Listtoform.setPrice(prices.get(i));
                Listtoform.setAmount(amounts.get(i));

                Listtoforms.add(Listtoform);

                Listtoforms = ListtoformRepository.saveAll(Listtoforms);
            } else if (checks.get(i) == 0) {
                KaimonoList kaimonoList = new KaimonoList();
                // kaimonoList.setId(i + 1);
                kaimonoList.setGoodsname(goodsNames.get(i));
                kaimonoList.setPrice(prices.get(i));
                kaimonoList.setAmount(amounts.get(i));

                kaimonoLists.add(kaimonoList);
                kaimonoLists = repository.saveAll(kaimonoLists);
            }

        }

        return "list";
    }

}
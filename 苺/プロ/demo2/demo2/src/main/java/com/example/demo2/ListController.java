package com.example.demo2;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo2.entity.KaimonoList;
import com.example.demo2.repository.KaimonoListRepository;



@Controller
public class ListController{

@Autowired
KaimonoListRepository repository;



@GetMapping("/list")
public String list(){
    return "list";
}

// テスト用
// @PostMapping("/regist")
// public String regist(RegisterForm form, Model model){

// KaimonoList kaimonolist = new KaimonoList();

// form.setId(1);

//  BeanUtils.copyProperties(form, kaimonolist);
//  kaimonolist=repository.save(kaimonolist);
//  KaimonoListBean itemBean = new KaimonoListBean();
//  BeanUtils.copyProperties(kaimonolist,itemBean);
//  model.addAttribute("kaimonolists", itemBean);



//     return "list";

// }

@PostMapping("/regist")
public String regist(@RequestParam("goodsname") List<String> goodsNames,
                     @RequestParam("price") List<Integer> prices,
                     @RequestParam("amount") List<Integer> amounts) {

    List<KaimonoList> kaimonoLists = new ArrayList<>();

    for (int i = 0; i < goodsNames.size(); i++) {
        KaimonoList kaimonoList = new KaimonoList();
        kaimonoList.setId(i + 1);
        kaimonoList.setGoodsname(goodsNames.get(i));
        kaimonoList.setPrice(prices.get(i));
        kaimonoList.setAmount(amounts.get(i));

        kaimonoLists.add(kaimonoList);

    }

    kaimonoLists = repository.saveAll(kaimonoLists);

    return "list";
}

}
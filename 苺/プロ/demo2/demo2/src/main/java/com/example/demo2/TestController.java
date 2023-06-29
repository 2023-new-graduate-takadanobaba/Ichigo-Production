package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo2.repository.KaimonoListRepository;
import com.example.demo2.repository.BoughtRepository;

@Controller
public class TestController {

    @Autowired
    KaimonoListRepository KaimonoListrepository;

    @Autowired
    BoughtRepository ListtoformRepository;

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("items", KaimonoListrepository.findAll());
        return "test";
    }

    @GetMapping("/")
    public String calender() {
        return "calender";
    }

    // @PostMapping("/test/regist")
    // public String registTest(@RequestParam("check") List<Integer> checks,
    //         @RequestParam("goodsname") List<String> goodsNames,
    //         @RequestParam("price") List<Integer> prices,
    //         @RequestParam("amount") List<Integer> amounts) {

    //     List<Bought> Listtoforms = new ArrayList<>();

    //     for (int i = 0; i < goodsNames.size(); i++) {

    //         if (checks.get(i) == 1) {
    //             Bought Listtoform = new Bought();
    //             Date now = new Date();
    //             // kaimonoList.setId(i + 1);
    //             Listtoform.setCreateTime(str);
    //             Listtoform.setGoodsname(goodsNames.get(i));
    //             Listtoform.setPrice(prices.get(i));
    //             Listtoform.setAmount(amounts.get(i));

    //             Listtoforms.add(Listtoform);

    //             Listtoforms = ListtoformRepository.saveAll(Listtoforms);
    //         }
    //     }

        

    //     return "test";
    // }

}

package com.example.demo2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            @RequestParam("id")List<Integer>ids,
            @RequestParam("goodsname") List<String> goodsNames,
            @RequestParam("price") List<Integer> prices,
            @RequestParam("amount") List<Integer> amounts) {

        List<KaimonoList> kaimonoLists = new ArrayList<>();
        List<Bought> boughts = new ArrayList<>();

        for (int i = 0; i < goodsNames.size(); i++) {
            if(goodsNames.get(i).isEmpty()){
                continue;
            }
            if (checks.get(i) == 1 ) {
                Bought bought = new Bought();
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
                String str = sdf.format(now);
                bought.setCreateTime(str);
                bought.setGoodsname(goodsNames.get(i));
                bought.setPrice(prices.get(i));
                bought.setAmount(amounts.get(i));
                bought.setTotal(prices.get(i) * amounts.get(i));
                boughts.add(bought);
                boughts = boughtRepository.saveAll(boughts);
                if(ids.get(i) !=0);{
                repository.deleteById(ids.get(i));
                }
            
            } else if (checks.get(i) == 0 && ids.get(i)==0) {
                KaimonoList kaimonoList = new KaimonoList();
                kaimonoList.setGoodsname(goodsNames.get(i));
                kaimonoList.setPrice(prices.get(i));
                kaimonoList.setAmount(amounts.get(i));
                kaimonoLists.add(kaimonoList);
                kaimonoLists = repository.saveAll(kaimonoLists);
            }else {
                KaimonoList kaimonoList = repository.getReferenceById(ids.get(i));
                kaimonoList.setGoodsname(goodsNames.get(i));
                kaimonoList.setPrice(prices.get(i));
                kaimonoList.setAmount(amounts.get(i));
                BeanUtils.copyProperties(kaimonoList, kaimonoList, "id");
                kaimonoList = repository.save(kaimonoList);
            }
            
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/gorori")
    public String delete(RegisterForm form) {
        repository.deleteById(form.getId());
        return "redirect:/list";
    }

    @PostMapping(path = "/kanryo")
    public String formregist(//@RequestParam("check") List<Integer> abc
    ) {

        // for (int i = 0; i < abc.size(); i++) {
        //     if (abc.get(i) != 0) {
        //         KaimonoList bou = repository.getReferenceById(abc.get(i));
        //         Bought itemBean = new Bought();
        //         BeanUtils.copyProperties(bou, itemBean, "id");
        //         Date now = new Date();
        //         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        //         String str = sdf.format(now);
        //         itemBean.setCreateTime(str);
        //         itemBean.setTotal(itemBean.getPrice() * itemBean.getAmount());
        //         itemBean = boughtRepository.save(itemBean);
        //     }

        // }

        return "calender";

    }

        // 行のデータの削除を行う
    @RequestMapping(path = "/item-delete", method = RequestMethod.GET)
    public String delateListItem(int id) {
        KaimonoList kaimonoList = repository.getReferenceById(id);
        // データベースのデータを削除する
        delete(kaimonoList.getId());

        // Formの一覧画面にリダイレクト
        return "redirect:/list";

    }

        // formのデータを削除する
    public void delete(Integer id) {

        // idを指定して、データベースからデータを削除する
        repository.deleteById(id);
    }

    @GetMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id){
        repository.deleteById(id);
        return "list";
    }
}
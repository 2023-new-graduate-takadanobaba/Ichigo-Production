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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo2.entity.Bought;
import com.example.demo2.entity.KaimonoList;
import com.example.demo2.form.RegisterForm;
import com.example.demo2.repository.BoughtRepository;
import com.example.demo2.repository.KaimonoListRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ListController {

    @Autowired
    KaimonoListRepository repository;

    @Autowired
    BoughtRepository boughtRepository;

    @GetMapping("/list")
    public String showItemList(Model model,HttpSession session) {
        model.addAttribute("items", repository.findByUser((String) session.getAttribute("user")));

        return "list";
    }

    @PostMapping("/regist")
    public String regist(@RequestParam("check") List<Integer> checks,
            @RequestParam("id") List<Integer> ids,
            @RequestParam("goodsname") List<String> goodsNames,
            @RequestParam("price") List<Integer> prices,
            @RequestParam("amount") List<Integer> amounts,
            HttpSession session) {

        List<KaimonoList> kaimonoLists = new ArrayList<>();
        List<Bought> boughts = new ArrayList<>();

        for (int i = 0; i < goodsNames.size(); i++) {
            if (goodsNames.get(i).isEmpty()) {
                continue;
            }
            if (checks.get(i) == 1) {
                Bought bought = new Bought();
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
                String str = sdf.format(now);
                bought.setCreateTime(str);
                bought.setGoodsname(goodsNames.get(i));
                bought.setPrice(prices.get(i));
                bought.setAmount(amounts.get(i));
                bought.setTotal(prices.get(i) * amounts.get(i));
                bought.setUser((String) session.getAttribute("user"));
                boughts.add(bought);
                boughts = boughtRepository.saveAll(boughts);
                if (ids.get(i) != 0)
                {
                    repository.deleteById(ids.get(i));
                }

            } else if (checks.get(i) == 0 && ids.get(i) == 0) {
                KaimonoList kaimonoList = new KaimonoList();
                kaimonoList.setGoodsname(goodsNames.get(i));
                kaimonoList.setPrice(prices.get(i));
                kaimonoList.setAmount(amounts.get(i));
                kaimonoList.setUser((String)session.getAttribute("user"));
                kaimonoLists.add(kaimonoList);
                kaimonoLists = repository.saveAll(kaimonoLists);
            } else {
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

    @GetMapping("/gorori")
    public String delete(RegisterForm form) {
        repository.deleteById(form.getId());
        return "redirect:/list";
    }

    // 行のデータの削除を行う
    @GetMapping("/item-delete")
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
    public String deleteItem(@PathVariable int id) {
        repository.deleteById(id);
        return "list";
    }
}
package com.example.demo2.form;


public class RegisterForm {
    
	private String goodsname;

	
	private Integer price;

    
    private Integer amount;

    private Integer id;

    public String getGoodsname() {
        return goodsname;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }


    public Integer getPrice() {
        return price;
    }


    public void setPrice(Integer price) {
        this.price = price;
    }


    public Integer getAmount() {
        return amount;
    }


    public void setAmount(Integer amount) {
        this.amount = amount;
    }

	
}
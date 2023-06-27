package com.example.demo2.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kaimonolist")


public class KaimonoList {
    @Id
    @GeneratedValue
	private Integer id;

	@Column
	private String goodsname;

	@Column
	private Integer price;

    @Column
    private Integer amount;


	public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getGoodsname() {
        return goodsname;
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
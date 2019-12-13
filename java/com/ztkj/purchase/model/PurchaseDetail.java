package com.ztkj.purchase.model;

/**
 * @Description:采购单明细表实体类
 * @Author:nieyf
 * @Date:2019/11/20 17:25
 */
public class PurchaseDetail {
    private int id;
    private Purchase purchase;//采购单编号
    private Goods goods;
    private Vendor vendor;
    private Integer num;
    private Double price;   //单价
    private Double money;   //金额

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}

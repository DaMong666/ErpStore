package com.ztkj.market.model;

import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.Vendor;

/**
 * @Description: 订单明细表
 * @Auther: YDM
 * @Date: 2019/11/20 16:46
 */
public class OrderDetail {
    private int id;//主键id
    private Order order;//订单
    private Goods goods;//商品
    private Vendor vendor;//厂商
    private int num;//数量
    private double price;//单价
    private double money;//金额

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}

package com.ztkj.statis.model;

import com.ztkj.market.model.Customer;
import com.ztkj.market.model.Order;
import com.ztkj.sys.model.City;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/26 16:02
 */
public class Sales {
    private Customer cusname;
    private City city;
    private int rownum;
    private int count;
    private double summoney;
    private Order order;

    public Customer getCusname() {
        return cusname;
    }

    public void setCusname(Customer cusname) {
        this.cusname = cusname;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSummoney() {
        return summoney;
    }

    public void setSummoney(double summoney) {
        this.summoney = summoney;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }
}

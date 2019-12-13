package com.ztkj.storage.model;

import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.Vendor;

/**
 * @Description:库存表实体类
 * @Auther: huangx
 * @Date: 2019/11/20 16:55
 */
public class Inventory {
    private int id;
    private Storage storage;//仓库
    private Goods goods;//商品
    private Vendor vendor;//厂商
    private int num;//数量

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

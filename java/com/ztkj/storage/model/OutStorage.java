package com.ztkj.storage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ztkj.market.model.Order;
import com.ztkj.sys.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:出库表实体类
 * @Auther: huangx
 * @Date: 2019/11/20 16:48
 */
public class OutStorage {
    private int id;
    private Order order;//订单
    private Storage storage;//仓库
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private User outPerson;//出库人

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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getOutPerson() {
        return outPerson;
    }

    public void setOutPerson(User outPerson) {
        this.outPerson = outPerson;
    }
}

package com.ztkj.storage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ztkj.purchase.model.Purchase;
import com.ztkj.sys.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:入库表实体类
 * @Auther: huangx
 * @Date: 2019/11/20 16:51
 */
public class InStorage {
    private int id;
    private Purchase purchase;
    private Storage storage;//仓库
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private User inPerson;//入库人

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

    public User getInPerson() {
        return inPerson;
    }

    public void setInPerson(User inPerson) {
        this.inPerson = inPerson;
    }
}

package com.ztkj.purchase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ztkj.sys.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:商品表实体类
 * @Author:nieyf
 * @Date:2019/11/20 17:08
 */
public class Goods {
    private int id;
    private GoodsType goodsType;
    private String model;   //型号名称
    private String unit;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private User createUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }
}

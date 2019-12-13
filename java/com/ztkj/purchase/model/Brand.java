package com.ztkj.purchase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ztkj.sys.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:品牌表实体类
 * @Author:nieyf
 * @Date:2019/11/20 16:44
 */
public class Brand {
    private int id;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

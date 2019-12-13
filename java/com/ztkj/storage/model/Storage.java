package com.ztkj.storage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:仓库表实体类
 * @Auther: huangx
 * @Date: 2019/11/20 16:43
 */
public class Storage {
    private int id;
    private String name;
    private String address;
    private User principalPerson;//负责人
    private String tel;
    private String description;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private User createPerson;//创建人
    private City city;//所属区域

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getPrincipalPerson() {
        return principalPerson;
    }

    public void setPrincipalPerson(User principalPerson) {
        this.principalPerson = principalPerson;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public User getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(User createPerson) {
        this.createPerson = createPerson;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}

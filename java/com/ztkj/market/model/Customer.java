package com.ztkj.market.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 客户表
 * @Auther: YDM
 * @Date: 2019/11/20 16:35
 */
public class Customer {
    private int id;//主键id
    private String name;//姓名
    private String sex;//性别
    private String tel;//电话
    private String company;//所属公司
    private City city;//所属区域
    private String status;//状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间
    private User createUser;//创建人
    private String address;//联系地址
    private String description;//描述
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date allocateTime;//分配时间
    private User user;//客户人员

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAllocateTime() {
        return allocateTime;
    }

    public void setAllocateTime(Date allocateTime) {
        this.allocateTime = allocateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

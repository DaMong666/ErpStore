package com.ztkj.sys.model;

/**
 * @Description: 城市类，对应t_city
 * @Author: HuangKL
 * @Date: 2019/11/20 17:01
 */
public class City {
    private String cityId;
    private String name;
    private Province proId;

    public City() {
    }

    public City(String cityId, String name, Province proId) {
        this.cityId = cityId;
        this.name = name;
        this.proId = proId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProId() {
        return proId;
    }

    public void setProId(Province proId) {
        this.proId = proId;
    }
}

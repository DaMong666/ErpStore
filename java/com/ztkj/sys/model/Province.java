package com.ztkj.sys.model;

/**
 * @Description: 省份类，对应t_province
 * @Author: HuangKL
 * @Date: 2019/11/20 16:59
 */
public class Province {
    private String proId;
    private String name;

    public Province() {
    }

    public Province(String proId, String name) {
        this.proId = proId;
        this.name = name;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

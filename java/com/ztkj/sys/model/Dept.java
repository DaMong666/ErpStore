package com.ztkj.sys.model;

/**
 * @Description: 部门类，对应t_dept
 * @Author: HuangKL
 * @Date: 2019/11/20 16:42
 */
public class Dept {
    private String no;
    private String name;
    private String status;

    public Dept() {}

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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
}

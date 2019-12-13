package com.ztkj.sys.model;

/**
 * @Description: 职位类，对应t_position
 * @Author: HuangKL
 * @Date: 2019/11/20 16:44
 */
public class Position {
    private String no;
    private String name;
    private Dept deptNo;
    private Position mgr;
    private String status;

    public Position() {
    }

    public Position(String no, String name, Dept deptNo, Position mgr, String status) {
        this.no = no;
        this.name = name;
        this.deptNo = deptNo;
        this.mgr = mgr;
        this.status = status;
    }

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

    public Dept getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Dept deptNo) {
        this.deptNo = deptNo;
    }

    public Position getMgr() {
        return mgr;
    }

    public void setMgr(Position mgr) {
        this.mgr = mgr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

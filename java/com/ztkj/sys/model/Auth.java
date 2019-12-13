package com.ztkj.sys.model;

/**
 * @Description: 权限类，对应t_auth
 * @Author: HuangKL
 * @Date: 2019/11/20 17:03
 */
public class Auth {
    private Position positionNo;
    private Module moduleId;

    public Auth() {
    }

    public Auth(Position positionNo, Module moduleId) {
        this.positionNo = positionNo;
        this.moduleId = moduleId;
    }

    public Position getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(Position positionNo) {
        this.positionNo = positionNo;
    }

    public Module getModuleId() {
        return moduleId;
    }

    public void setModuleId(Module moduleId) {
        this.moduleId = moduleId;
    }
}

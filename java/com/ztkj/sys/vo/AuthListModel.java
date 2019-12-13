package com.ztkj.sys.vo;

import com.ztkj.sys.model.Auth;

import java.util.List;

/**
 * @Description: Auth的List模型类
 * @Author: HuangKL
 * @Date: 2019/11/26 09:36
 */
public class AuthListModel {
    private String positionNo;
    private List<Auth> authList;

    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    public List<Auth> getAuthList() {
        return authList;
    }

    public void setAuthList(List<Auth> authList) {
        this.authList = authList;
    }
}

package com.ztkj.sys.service;

import com.ztkj.sys.model.Auth;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 23:43
 */
public interface AuthService {

    /**
     * @description: 根据职位No查询
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Auth>
     * @auther: HuangKL
     * @date: 2019/11/25 23:43
     */
    public List<Auth> findById(String positionNo);

    /**
     * @description: 更新权限
     * @param: [positionNo, authList]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/26 09:39
     */
    public void updateAuth(String positionNo, List<Auth> authList);

}

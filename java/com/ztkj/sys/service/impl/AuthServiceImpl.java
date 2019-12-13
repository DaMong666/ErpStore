package com.ztkj.sys.service.impl;

import com.ztkj.sys.dao.AuthDao;
import com.ztkj.sys.model.Auth;
import com.ztkj.sys.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 23:45
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {
    @Resource
    private AuthDao authDao;

    /**
     * @description: 根据职位No查询
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Auth>
     * @auther: HuangKL
     * @date: 2019/11/25 23:43
     */
    public List<Auth> findById(String positionNo) {
        return authDao.findById(positionNo);
    }

    /**
     * @description: 更新权限
     * @param: [positionNo, authList]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/26 09:39
     */
    @Transactional
    public void updateAuth(String positionNo, List<Auth> authList) {
        authDao.delete(positionNo);
        if (authList != null) {
            for (Auth auth : authList) {
                if (auth.getModuleId() != null) {
                    authDao.add(positionNo,auth);
                }
            }
        }
    }
}

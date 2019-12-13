package com.ztkj.sys.controller;

import com.ztkj.sys.model.Auth;
import com.ztkj.sys.model.User;
import com.ztkj.sys.service.AuthService;
import com.ztkj.sys.vo.AuthListModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 23:47
 */
@RestController
@RequestMapping("sys/auth")
public class AuthController {
    @Resource
    private AuthService authService;

    /**
     * @description: 根据登录用户查询权限
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Auth>
     * @auther: HuangKL
     * @date: 2019/11/25 23:48
     */
    @RequestMapping("qSeAuth")
    public List<Auth> querySessionAuth(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return authService.findById(user.getPositionNo().getNo());
    }

    /**
     * @description: 查看权限
     * @param: [no]
     * @return: java.util.List<com.ztkj.sys.model.Auth>
     * @auther: HuangKL
     * @date: 2019/11/26 09:24
     */
    @RequestMapping("qUpdAuth")
    public List<Auth> queryUpdAuth(String no) {
        return authService.findById(no);
    }

    /**
     * @description: 更新权限
     * @param: [authListModel]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/26 09:37
     */
    @RequestMapping("updAuth")
    public Map<String, String> updateAuth(AuthListModel authListModel) {
        Map<String, String> map = new HashMap<>();
        try {
            authService.updateAuth(authListModel.getPositionNo(),authListModel.getAuthList());
            map.put("code", "1");
            map.put("info", "赋权成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "0");
            map.put("info", "赋权失败,请重试");
        }
        return map;
    }



}

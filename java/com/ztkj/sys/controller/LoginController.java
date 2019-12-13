package com.ztkj.sys.controller;

import com.ztkj.sys.model.User;
import com.ztkj.sys.service.UserService;
import com.ztkj.utils.EncryptUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 登录相关的Controller
 * @Author: HuangKL
 * @Date: 2019/11/20 19:42
 */
@Controller
@RequestMapping("login")
public class LoginController {
    @Resource
    private UserService userService;

    /**
     * @description: 登录
     * @param: [user]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/20 19:45
     */
    @RequestMapping("lo")
    public @ResponseBody Map<String, String> login(User user, HttpSession session) {//, HttpServletResponse response
        Map<String, String> map = new HashMap<>();
        String pass = user.getPass();
        user.setPass(EncryptUtil.encrypt(pass));
        User loginUser = userService.queryLoginUser(user);
        if (loginUser != null) {
            session.setAttribute("loginUser",loginUser);
            map.put("code", "1");
            map.put("cookie", loginUser.getNo() + "," + pass);
        } else {
            map.put("code", "0");
            map.put("info", "用户名或密码错误");
        }
        return map;
    }

    /**
     * @description: 获取session User
     * @param: [session]
     * @return: com.ztkj.sys.model.User
     * @auther: HuangKL
     * @date: 2019/11/20 20:59
     */
    @RequestMapping("curr")
    public @ResponseBody User currentUser(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return user;
    }

    @RequestMapping("currMy")
    public @ResponseBody Map<String,Object> currentUserForMy(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("loginUser");
        Map<String, Object> map = new HashMap<>();
        String pass = EncryptUtil.decrypt(user.getPass());
        map.put("user", user);
        map.put("pass", pass);
        return map;
    }

    /**
     * @description: 退出登录，清除session
     * @param: [mav, session]
     * @return: org.springframework.web.servlet.ModelAndView
     * @auther: HuangKL
     * @date: 2019/11/21 10:03
     */
    @RequestMapping("ex")
    public ModelAndView exit(ModelAndView mav,HttpSession session){
        session.invalidate();
        mav.setViewName("redirect:/login.html");
        return mav;
    }

}

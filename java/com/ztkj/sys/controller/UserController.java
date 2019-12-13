package com.ztkj.sys.controller;

import com.ztkj.sys.model.Dept;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.User;
import com.ztkj.sys.service.UserService;
import com.ztkj.utils.EncryptUtil;
import com.ztkj.utils.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户管理子模块Controller
 * @Author: HuangKL
 * @Date: 2019/11/21 13:22
 */
@RestController
@RequestMapping("sys/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * @description: 添加用户
     * @param: [user]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/21 13:26
     */
    @RequestMapping("ad")
    public Map<String, String> addUser(User user) {
        Map<String, String> map = new HashMap<>();
        user.setPass(EncryptUtil.encrypt(user.getPass()));
        try {
            userService.addUser(user);
            map.put("code", "1");
            map.put("info", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "0");
            map.put("info", "添加失败,请重新输入信息");
        }
        return map;
    }

    /**
     * @description: 获取所有部门
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Dept>
     * @auther: HuangKL
     * @date: 2019/11/21 14:59
     */
    @RequestMapping("gDe")
    public List<Dept> queryDept() {
        return userService.queryDept();
    }

    /**
     * @description: 根据部门No获取职位
     * @param: [deptNo]
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @auther: HuangKL
     * @date: 2019/11/21 15:01
     */
    @RequestMapping("gPo")
    public List<Position> queryPosition(String deptNo) {
        return userService.queryPosition(deptNo);
    }

    /**
     * @description: 根据部门No自动生成员工No
     * @param: [positionNo]
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/21 16:08
     */
    @RequestMapping("gUNo")
    public String queryUserNo(@RequestParam("positionNo") String positionNo) {
        return userService.queryUserNo(positionNo);
    }

    /**
     * @description: 模糊分页
     * @param: [pageNo, user]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/21 20:26
     */
    @RequestMapping("gPa")
    public Page queryPage(Integer pageNo, User user) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        Page page = new Page(pageNo, 5);
        page = userService.queryByPage(page, user);
        return page;
    }

    /**
     * @description: 离职
     * @param: [id]
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/22 12:47
     */
    @RequestMapping("can")
    public String cancelUser(int id) {
        try {
            userService.cancelUser(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * @description: 在职
     * @param: [id]
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/22 12:46
     */
    @RequestMapping("rec")
    public String recoverUser(int id) {
        try {
            userService.recoverUser(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * @description: 重置密码
     * @param: [id, session]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/22 12:47
     */
    @RequestMapping("reset")
    public Map<String,String> updateInitPass(int id, HttpSession session) {
        Map<String, String> map = new HashMap<>();
        try {
            String pass = EncryptUtil.encrypt("123456");
            userService.resetPass(id, pass);
            User user = (User) session.getAttribute("loginUser");
            if (user.getId() == id) {
                map.put("code", "2");
                map.put("info","重置成功，请重新登录");
            } else {
                map.put("code", "1");
                map.put("info","重置成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("info", "重置失败");
        }
        return map;
    }

    /**
     * @description: 根据Id查询
     * @param: [id]
     * @return: com.ztkj.sys.model.User
     * @auther: HuangKL
     * @date: 2019/11/22 12:48
     */
    @RequestMapping("qUBid")
    public User queryUserById(int id) {
        return userService.queryById(id);
    }

    /**
     * @description: 修改信息
     * @param: [user]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/22 13:27
     */
    @RequestMapping("uUs")
    public Map<String, String> updateUser(User user) {
        Map<String, String> map = new HashMap<>();
        try {
            userService.updateUser(user);
            map.put("code", "1");
            map.put("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "0");
            map.put("info", "修改失败,请重新输入信息");
        }
        return map;
    }

    @RequestMapping("my")
    public Map<String, String> mySelf(User user) {
        Map<String, String> map = new HashMap<>();
        try {
            user.setPass(EncryptUtil.encrypt(user.getPass()));
            userService.updateMy(user);
            map.put("code", "1");
            map.put("info", "个人信息修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "0");
            map.put("info", "个人信息修改失败,请重新输入信息");
        }
        return map;
    }

}

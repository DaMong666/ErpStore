package com.ztkj.sys.service.impl;

import com.ztkj.sys.dao.UserDao;
import com.ztkj.sys.model.Dept;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.User;
import com.ztkj.sys.service.UserService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @Description: 用户Service层实现类
 * @Author: HuangKL
 * @Date: 2019/11/20 19:18
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * @description: 登录查询
     * @param: [user]
     * @return: com.ztkj.sys.model.User
     * @auther: HuangKL
     * @date: 2019/11/20 19:21
     */
    @Transactional
    public User queryLoginUser(User user) {
        User loginUser = userDao.login(user);
        if (loginUser != null) {
            userDao.updateLoginTime(loginUser);
        }
        return loginUser;
    }

    /**
     * @description: 添加用户
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/21 13:22
     */
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    /**
     * @description: 查询部门
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Dept>
     * @auther: HuangKL
     * @date: 2019/11/21 15:05
     */
    public List<Dept> queryDept() {
        return userDao.findAllDept();
    }

    /**
     * @description: 查询职位
     * @param: [deptNo]
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @auther: HuangKL
     * @date: 2019/11/21 15:06
     */
    public List<Position> queryPosition(String deptNo) {
        return userDao.findPositionByDeptNo(deptNo);
    }

    /**
     * @description: 查询员工No
     * @param: [positionNo]
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/21 15:57
     */
    public String queryUserNo(String positionNo) {
        DecimalFormat df = new DecimalFormat("00000000");
        Integer no = userDao.getNo(positionNo);
        if (no == null) {
            no = Integer.valueOf(positionNo + "001");
        }
        return df.format(no);
    }

    /**
     * @description: 分页查询
     * @param: [page, user]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/21 19:24
     */
    public Page queryByPage(Page page, User user) {
        List<User> userList = userDao.findByPage(page.getStartIndex(), page.getEndIndex(), user);
        int rowCount = userDao.countPage(user);
        page.setResult(userList,rowCount);
        return page;
    }

    /**
     * @param id
     * @description: 离职
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 10:01
     */
    @Transactional
    public void cancelUser(int id) {
        userDao.cancel(id);
    }

    /**
     * @param id
     * @description: 在职
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 10:01
     */
    @Transactional
    public void recoverUser(int id) {
        userDao.recover(id);
    }

    /**
     * @description: 重置密码
     * @param: [id, pass]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 10:42
     */
    public void resetPass(int id, String pass) {
        userDao.resetPass(id, pass);
    }

    /**
     * @description: 根据Id查询
     * @param: [id]
     * @return: com.ztkj.sys.model.User
     * @auther: HuangKL
     * @date: 2019/11/22 12:45
     */
    public User queryById(int id) {
        return userDao.findById(id);
    }

    /**
     * @description: 修改
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 14:02
     */
    public void updateUser(User user) {
        userDao.update(user);
    }

    /**
     * @description: 修改个人信息
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/28 17:30
     */
    @Transactional
    public void updateMy(User user) {
        userDao.updateMy(user);
    }

}

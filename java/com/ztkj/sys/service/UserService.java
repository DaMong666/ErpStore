package com.ztkj.sys.service;

import com.ztkj.sys.model.Dept;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description: 用户Service层接口
 * @Author: HuangKL
 * @Date: 2019/11/20 19:17
 */
public interface UserService {

    /**
     * @description: 登录查询
     * @param: [user]
     * @return: com.ztkj.sys.model.User
     * @auther: HuangKL
     * @date: 2019/11/20 19:20
     */
    public User queryLoginUser(User user);

    /**
     * @description: 添加用户
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/21 13:22
     */
    public void addUser(User user);

    /**
     * @description: 查询部门
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Dept>
     * @auther: HuangKL
     * @date: 2019/11/21 15:03
     */
    public List<Dept> queryDept();

    /**
     * @description: 查询职位
     * @param: [deptNo]
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @auther: HuangKL
     * @date: 2019/11/21 15:06
     */
    public List<Position> queryPosition(String deptNo);

    /**
     * @description: 查询员工No
     * @param: [positionNo]
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/21 15:56
     */
    public String queryUserNo(String positionNo);

    /**
     * @description: 分页查询
     * @param: [page, user]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/21 19:24
     */
    public Page queryByPage(Page page, User user);

    /**
     * @description: 离职
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 10:01
     */
    public void cancelUser(int id);

    /**
     * @description: 在职
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 10:01
     */
    public void recoverUser(int id);

    /**
     * @description: 重置密码
     * @param: [id, pass]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 10:42
     */
    public void resetPass(int id, String pass);

    /**
     * @description: 根据Id查询
     * @param: [id]
     * @return: com.ztkj.sys.model.User
     * @auther: HuangKL
     * @date: 2019/11/22 12:45
     */
    public User queryById(int id);

    /**
     * @description: 修改
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 14:02
     */
    public void updateUser(User user);

    /**
     * @description: 修改个人信息
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/28 17:30
     */
    public void updateMy(User user);

}

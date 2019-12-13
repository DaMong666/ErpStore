package com.ztkj.sys.dao;

import com.ztkj.sys.model.Dept;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/20 18:55
 */
public interface UserDao {
    /**
     * @description: 登录
     * @param: [user]
     * @return: com.ztkj.sys.model.User
     * @auther: HuangKL
     * @date: 2019/11/20 19:01
     */
    public User login(@Param("user") User user);

    /**
     * @description: 修改用户登录时间
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/21 11:07
     */
    public void updateLoginTime(@Param("user") User user);

    /**
     * @description: 添加用户
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/21 13:13
     */
    public void addUser(@Param("user") User user);

    /**
     * @description: 获取所有部门
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Dept>
     * @auther: HuangKL
     * @date: 2019/11/21 13:31
     */
    public List<Dept> findAllDept();

    /**
     * @description: 根据部门No获取所有职位
     * @param: [deptNo]
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @auther: HuangKL
     * @date: 2019/11/21 14:55
     */
    public List<Position> findPositionByDeptNo(@Param("deptNo") String deptNo);

    /**
     * @description: 获取员工No
     * @param: [positionNo]
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/21 15:55
     */
    public Integer getNo(@Param("positionNo") String positionNo);

    /**
     * @description: 模糊分页查询
     * @param: [startIndex, endIndex, user]
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: HuangKL
     * @date: 2019/11/21 17:44
     */
    public List<User> findByPage(@Param("startIndex") int startIndex,
                                 @Param("endIndex") int endIndex, @Param("user") User user);

    /**
     * @description: 模糊分页统计
     * @param: [user]
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/21 17:48
     */
    public int countPage(@Param("user") User user);

    /**
     * @description: 离职
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 09:55
     */
    public void cancel(@Param("id") int id);

    /**
     * @description: 在职
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 09:55
     */
    public void recover(@Param("id") int id);

    /**
     * @description: 重置密码
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 10:41
     */
    public void resetPass(@Param("id") int id, @Param("pass") String pass);

    /**
     * @description: 根据Id查询
     * @param: [id]
     * @return: com.ztkj.sys.model.User
     * @auther: HuangKL
     * @date: 2019/11/22 11:53
     */
    public User findById(@Param("id")int id);

    /**
     * @description: 修改
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/22 13:28
     */
    public void update(@Param("user") User user);


    /**
     * @description: 修改个人信息
     * @param: [user]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/28 17:27
     */
    public void updateMy(@Param("user") User user);

}

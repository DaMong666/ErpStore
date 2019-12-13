package com.ztkj.sys.dao;

import com.ztkj.sys.model.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/24 13:45
 */
public interface DeptDao {

    /**
     * @description: 模糊分页查询
     * @param: [startIndex, endIndex, dept]
     * @return: java.util.List<com.ztkj.sys.model.Dept>
     * @auther: HuangKL
     * @date: 2019/11/24 13:49
     */
    public List<Dept> findByPage(@Param("startIndex") int startIndex,
                                 @Param("endIndex") int endIndex, @Param("dept") Dept dept);

    /**
     * @description: 分页统计
     * @param: [dept]
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/24 13:49
     */
    public int countPage(@Param("dept") Dept dept);

    /**
     * @description: 添加
     * @param: [dept]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 13:58
     */
    public void add(@Param("dept") Dept dept);

    /**
     * @description: 修改
     * @param: [dept]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:00
     */
    public void update(@Param("dept") Dept dept);

    /**
     * @description: 注销
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:00
     */
    public void cancel(@Param("no") String no);

    /**
     * @description: 恢复
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:01
     */
    public void recover(@Param("no") String no);

    /**
     * @description: 获取部门编号
     * @param: []
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/24 14:40
     */
    public int getNo();

    /**
     * @description: 根据查询No查询部门
     * @param: [no]
     * @return: com.ztkj.sys.model.Dept
     * @auther: HuangKL
     * @date: 2019/11/25 09:26
     */
    public Dept getByNo(@Param("no") String no);

}

package com.ztkj.sys.service;

import com.ztkj.sys.model.Dept;
import com.ztkj.utils.Page;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/24 14:28
 */
public interface DeptService {

    /**
     * @description: 分页查询
     * @param: [page, dept]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 14:28
     */
    public Page queryByPage(Page page, Dept dept);

    /**
     * @description: 添加部门
     * @param: [dept]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:29
     */
    public void addDept(Dept dept);

    /**
     * @description: 修改信息
     * @param: [dept]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    public void updateDept(Dept dept);

    /**
     * @description: 注销部门
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    public void cancelDept(int id);

    /**
     * @description: 恢复部门
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:32
     */
    public void recoverDept(int id);

    /**
     * @description: 自动生成部门编号
     * @param: []
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/24 14:58
     */
    public String queryDeptNo();

    /**
     * @description: 根据No获取部门
     * @param: [no]
     * @return: com.ztkj.sys.model.Dept
     * @auther: HuangKL
     * @date: 2019/11/25 09:29
     */
    public Dept queryDeptByNo(String no);

}

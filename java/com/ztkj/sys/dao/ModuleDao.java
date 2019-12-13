package com.ztkj.sys.dao;

import com.ztkj.sys.model.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 15:52
 */
public interface ModuleDao {

    /**
     * @description: 模糊分页查询
     * @param: [startIndex, endIndex, Module]
     * @return: java.util.List<com.ztkj.sys.model.Module>
     * @auther: HuangKL
     * @date: 2019/11/24 13:49
     */
    public List<Module> findByPage(@Param("startIndex") int startIndex,
                                   @Param("endIndex") int endIndex, @Param("module") Module module);

    /**
     * @description: 分页统计
     * @param: [Module]
     * @return: int
     * @auther: HuangKL
     * @date: 2019/11/24 13:49
     */
    public int countPage(@Param("module") Module module);

    /**
     * @description: 添加
     * @param: [Module]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 13:58
     */
    public void add(@Param("module") Module module);

    /**
     * @description: 修改
     * @param: [Module]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:00
     */
    public void update(@Param("module") Module module);

    /**
     * @description: 禁用
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/25 10:36
     */
    public void cancel(@Param("id") int id);

    /**
     * @description: 启用
     * @param: [no]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/25 10:36
     */
    public void recover(@Param("id") int id);

    /**
     * @description: 根据Id查询
     * @param: [no]
     * @return: com.ztkj.sys.model.Module
     * @auther: HuangKL
     * @date: 2019/11/25 10:35
     */
    public Module getById(@Param("id") int id);

    /**
     * @description: 获取父模块
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Module>
     * @auther: HuangKL
     * @date: 2019/11/25 16:47
     */
    public List<Module> findParent();

    /**
     * @description: 获取子模块
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Module>
     * @auther: HuangKL
     * @date: 2019/11/25 21:09
     */
    public List<Module> findChild();
    
}

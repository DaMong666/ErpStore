package com.ztkj.sys.service;

import com.ztkj.sys.model.Module;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 16:40
 */
public interface ModuleService {

    /**
     * @description: 分页查询
     * @param: [page, module]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 14:28
     */
    public Page queryByPage(Page page, Module module);

    /**
     * @description: 添加
     * @param: [module]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:29
     */
    public void addModule(Module module);

    /**
     * @description: 修改
     * @param: [module]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    public void updateModule(Module module);

    /**
     * @description: 禁用
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    public void cancelModule(int id);

    /**
     * @description: 启用
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:32
     */
    public void recoverModule(int id);

    /**
     * @description: 根据Id获取模块
     * @param: [id]
     * @return: com.ztkj.sys.model.Module
     * @auther: HuangKL
     * @date: 2019/11/25 16:43
     */
    public Module queryModuleById(int id);

    /**
     * @description: 查询父模块
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Module>
     * @auther: HuangKL
     * @date: 2019/11/25 13:57
     */
    public List<Module> queryParent();

    /**
     * @description: 查询子模块
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Module>
     * @auther: HuangKL
     * @date: 2019/11/25 23:18
     */
    public List<Module> queryChild();
    
}

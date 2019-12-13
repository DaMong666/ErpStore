package com.ztkj.sys.service.impl;

import com.ztkj.sys.dao.ModuleDao;
import com.ztkj.sys.model.Module;
import com.ztkj.sys.service.ModuleService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 16:44
 */
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleDao moduleDao;

    /**
     * @description: 分页查询
     * @param: [page, module]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 14:28
     */
    public Page queryByPage(Page page, Module module) {
        List<Module> moduleList = moduleDao.findByPage(page.getStartIndex(), page.getEndIndex(), module);
        int rows = moduleDao.countPage(module);
        page.setResult(moduleList, rows);
        return page;
    }

    /**
     * @description: 添加
     * @param: [module]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:29
     */
    @Transactional
    public void addModule(Module module) {
        moduleDao.add(module);
    }

    /**
     * @description: 修改
     * @param: [module]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    @Transactional
    public void updateModule(Module module) {
        moduleDao.update(module);
    }

    /**
     * @description: 禁用
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:30
     */
    @Transactional
    public void cancelModule(int id) {
        moduleDao.cancel(id);
    }

    /**
     * @description: 启用
     * @param: [id]
     * @return: void
     * @auther: HuangKL
     * @date: 2019/11/24 14:32
     */
    @Transactional
    public void recoverModule(int id) {
        moduleDao.recover(id);
    }

    /**
     * @description: 根据Id获取模块
     * @param: [id]
     * @return: com.ztkj.sys.model.Module
     * @auther: HuangKL
     * @date: 2019/11/25 16:43
     */
    public Module queryModuleById(int id) {
        return moduleDao.getById(id);
    }

    /**
     * @description: 查询父模块
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Module>
     * @auther: HuangKL
     * @date: 2019/11/25 13:57
     */
    public List<Module> queryParent() {
        return moduleDao.findParent();
    }

    /**
     * @description: 查询子模块
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Module>
     * @auther: HuangKL
     * @date: 2019/11/25 23:18
     */
    public List<Module> queryChild() {
        return moduleDao.findChild();
    }
}

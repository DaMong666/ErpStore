package com.ztkj.sys.controller;

import com.ztkj.sys.model.Module;
import com.ztkj.sys.service.ModuleService;
import com.ztkj.utils.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/25 16:49
 */
@RestController
@RequestMapping("sys/module")
public class ModuleController {
    @Resource
    private ModuleService moduleService;

    /**
     * @description: 模糊分页
     * @param: [pageNo, module]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/25 16:52
     */
    @RequestMapping("qPage")
    public Page queryPage(Integer pageNo, Module module) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        Page page = new Page(pageNo, 5);
        page = moduleService.queryByPage(page, module);
        return page;
    }

    /**
     * @description: 启用
     * @param: [id]
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/25 16:52
     */
    @RequestMapping("can")
    public String cancelModule(@RequestParam("id") int id) {
        try {
            moduleService.cancelModule(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * @description: 禁用
     * @param: [id]
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/25 16:52
     */
    @RequestMapping("rec")
    public String recoverModule(@RequestParam("id") int id) {
        try {
            moduleService.recoverModule(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * @description: 添加
     * @param: [module]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/25 16:53
     */
    @RequestMapping("addMo")
    public Map<String, String> addModule(Module module) {
        Map<String, String> map = new HashMap<>();
        try {
            moduleService.addModule(module);
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
     * @description: 修改
     * @param: [module]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/25 11:55
     */
    @RequestMapping("updMo")
    public Map<String, String> updateModule(Module module) {
        Map<String, String> map = new HashMap<>();
        try {
            moduleService.updateModule(module);
            map.put("code", "1");
            map.put("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "0");
            map.put("info", "修改失败,请重新输入信息");
        }
        return map;
    }

    /**
     * @description: 根据Id查询
     * @param: [id]
     * @return: com.ztkj.sys.model.Module
     * @auther: HuangKL
     * @date: 2019/11/25 16:54
     */
    @RequestMapping("qMBNo")
    public Module queryModuleByNo(@RequestParam("id") int id) {
        return moduleService.queryModuleById(id);
    }

    /**
     * @description: 获取父模块
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Module>
     * @auther: HuangKL
     * @date: 2019/11/25 16:54
     */
    @RequestMapping("qParent")
    public List<Module> queryParent() {
        return moduleService.queryParent();
    }

    /**
     * @description: 获取子模块
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Module>
     * @auther: HuangKL
     * @date: 2019/11/25 23:19
     */
    @RequestMapping("qChild")
    public List<Module> queryChild() {
        return moduleService.queryChild();
    }

}

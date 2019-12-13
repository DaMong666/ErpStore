package com.ztkj.sys.controller;

import com.ztkj.sys.model.Dept;
import com.ztkj.sys.service.DeptService;
import com.ztkj.utils.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/24 15:17
 */
@RestController
@RequestMapping("sys/dept")
public class DeptController {
    @Resource
    private DeptService deptService;

    /**
     * @description: 分页模糊查询
     * @param: [pageNo, dept]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 21:01
     */
    @RequestMapping("qPage")
    public Page queryPage(Integer pageNo, Dept dept) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        Page page = new Page(pageNo, 2);
        page = deptService.queryByPage(page, dept);
        return page;
    }

    /**
     * @description: 撤销
     * @param: [no]
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/24 21:01
     */
    @RequestMapping("can")
    public String cancelDept(@RequestParam("id") int id) {
        try {
            deptService.cancelDept(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * @description: 恢复
     * @param: [no]
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/24 21:01
     */
    @RequestMapping("rec")
    public String recoverDept(@RequestParam("id") int id) {
        try {
            deptService.recoverDept(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * @description: 添加部门
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/24 23:24
     */
    @RequestMapping("aDe")
    public Map<String, String> addDept(Dept dept) {
        Map<String, String> map = new HashMap<>();
        try {
            deptService.addDept(dept);
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
     * @description: 获取部门编号
     * @param: []
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/24 23:28
     */
    @RequestMapping("qDNo")
    public String queryDeptNo() {
        return deptService.queryDeptNo();
    }

    /**
     * @description: 修改
     * @param: [dept]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/25 09:40
     */
    @RequestMapping("uDe")
    public Map<String, String> updateDept(Dept dept) {
        Map<String, String> map = new HashMap<>();
        try {
            deptService.updateDept(dept);
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
     * @description: 根据No查询部门
     * @param: [no]
     * @return: com.ztkj.sys.model.Dept
     * @auther: HuangKL
     * @date: 2019/11/25 09:32
     */
    @RequestMapping("qDBNo")
    public Dept queryDeptByNo(@RequestParam("id") String no){
        return deptService.queryDeptByNo(no);
    }

}

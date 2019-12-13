package com.ztkj.sys.controller;

import com.ztkj.sys.model.Position;
import com.ztkj.sys.service.PositionService;
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
 * @Date: 2019/11/25 11:49
 */
@RestController
@RequestMapping("sys/position")
public class PositionController {
    @Resource
    private PositionService positionService;

    /**
     * @description: 模糊分页
     * @param: [pageNo, position]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/25 11:52
     */
    @RequestMapping("qPage")
    public Page queryPage(Integer pageNo, Position position) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        Page page = new Page(pageNo, 5);
        page = positionService.queryByPage(page, position);
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
    public String cancelPosition(@RequestParam("id") int id) {
        try {
            positionService.cancelPosition(id);
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
    public String recoverPosition(@RequestParam("id") int id) {
        try {
            positionService.recoverPosition(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * @description: 添加
     * @param: [position]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/25 11:56
     */
    @RequestMapping("addPo")
    public Map<String, String> addPosition(Position position) {
        Map<String, String> map = new HashMap<>();
        try {
            positionService.addPosition(position);
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
     * @description: 获取职位编号
     * @param: [deptNo]
     * @return: java.lang.String
     * @auther: HuangKL
     * @date: 2019/11/25 11:55
     */
    @RequestMapping("qPNo")
    public String queryPositionNo(String deptNo) {
        return positionService.queryPositionNo(deptNo);
    }

    /**
     * @description: 修改
     * @param: [position]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @auther: HuangKL
     * @date: 2019/11/25 11:55
     */
    @RequestMapping("updPo")
    public Map<String, String> updatePosition(Position position) {
        Map<String, String> map = new HashMap<>();
        try {
            positionService.updatePosition(position);
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
     * @description: 根据No查询职位
     * @param: [no]
     * @return: com.ztkj.sys.model.Position
     * @auther: HuangKL
     * @date: 2019/11/25 11:56
     */
    @RequestMapping("qPBNo")
    public Position queryPositionByNo(@RequestParam("id") String no) {
        return positionService.queryPositionByNo(no);
    }

    /**
     * @description: 获取上级
     * @param: [deptNo, no]
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @auther: HuangKL
     * @date: 2019/11/25 14:00
     */
    @RequestMapping("qMgr")
    public List<Position> queryMgr(String deptNo) {
        return positionService.queryMgr(deptNo, "");
    }

}

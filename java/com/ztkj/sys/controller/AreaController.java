package com.ztkj.sys.controller;

import com.ztkj.sys.model.City;
import com.ztkj.sys.service.AreaService;
import com.ztkj.utils.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 区域管理Controller
 * @Author: HuangKL
 * @Date: 2019/11/22 17:17
 */
@RestController
@RequestMapping("sys/area")
public class AreaController {
    @Resource
    private AreaService areaService;

    /**
     * @description: 分页+模糊查询
     * @param: [pageNo, city]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/24 13:48
     */
    @RequestMapping("qPage")
    public Page queryPage(Integer pageNo, City city) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        Page page = new Page(pageNo, 10);
        page = areaService.queryByPage(page, city);
        return page;
    }

}

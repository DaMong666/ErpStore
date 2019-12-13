package com.ztkj.sys.controller;

import com.ztkj.sys.model.Log;
import com.ztkj.sys.param.LogParam;
import com.ztkj.sys.service.LogService;
import com.ztkj.utils.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: HuangKL
 * @Date: 2019/11/26 17:13
 */
@RestController
@RequestMapping("sys/log")
public class LogController {
    @Resource
    public LogService logService;

    /**
     * @description: 模糊查询+分页
     * @param: [pageNo, logParam]
     * @return: com.ztkj.utils.Page
     * @auther: HuangKL
     * @date: 2019/11/26 17:14
     */
    @RequestMapping("qPage")
    public Page queryPage(Integer pageNo, LogParam logParam) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        Page page = new Page(pageNo, 8);
        page = logService.queryByPage(page, logParam);
        return page;
    }

    /**
     * @description: 根据Id查询
     * @param: [id]
     * @return: com.ztkj.sys.model.Log
     * @auther: HuangKL
     * @date: 2019/11/26 17:21
     */
    @RequestMapping("qLBId")
    public Log queryDeptByNo(@RequestParam("id") int id){
        return logService.queryById(id);
    }

}

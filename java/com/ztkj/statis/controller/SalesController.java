package com.ztkj.statis.controller;

import com.ztkj.statis.param.SalesParam;
import com.ztkj.statis.service.SalesService;
import com.ztkj.utils.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/26 15:40
 */
@RestController
@RequestMapping("statis/sales")
public class SalesController {

    @Resource
    private SalesService salesService;

    /**
     *模糊+分页查询营销情况
     * @param: [pageNo, salesParam]
     * @return: com.ztkj.utils.Page
     * @author: liss
     * @date: 2019/11/26 15:43
     */
    @RequestMapping("asyncpage")
    public Page querySalesByPage(Integer pageNo, SalesParam salesParam){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        Page page=new Page(pageNo,4);
        page = salesService.queryByPage(page,salesParam);
        return page;
    }

    /**
     *营销统计值查看详情
     * @param: [pageNo, id]
     * @return: com.ztkj.utils.Page
     * @author: liss
     * @date: 2019/11/26 20:34
     */
    @RequestMapping("asyncsalesdetail")
    public Page querySalesDetail(Integer pageNo,int id){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        Page page=new Page(pageNo,4);
        page = salesService.queryOrderByCustomer(id,page);
        return page;
    }
}

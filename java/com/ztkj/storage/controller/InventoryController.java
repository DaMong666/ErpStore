package com.ztkj.storage.controller;

import com.ztkj.storage.param.InventoryParam;
import com.ztkj.storage.service.InventoryService;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Description:库存类控制层
 * @Auther: huangx
 * @Date: 2019/11/22 17:47
 */
@Controller
@RequestMapping("storage/inventory")
public class InventoryController {
    @Resource
    private InventoryService inventoryService;
    /*
     *仓库模糊+分页
     * @param: [pageNo, storageName, provinceId, cityId]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/20 20:13
     */
    @RequestMapping("inventorypage")
    public @ResponseBody Page queryPage(Integer pageNo, InventoryParam inventoryParam, HttpSession session){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        Page page = new Page(pageNo,2);//当前页，每页2条
        //获取当前登录对象
        User user = (User) session.getAttribute("loginUser");
        //获取到登录对象的id主键（负责人）
        int principalId = user.getId();
        Integer.valueOf(principalId);
        System.out.println("==========Integer.valueOf(principalId);============"+Integer.valueOf(principalId));
        if(user.getNo().substring(0, 7).equals("0400500")){//一组组员04005001
            principalId = 101;//老马 一组主管
        }
        if(user.getNo().substring(0, 7).equals("0400700")){//二组组员04007001
            principalId = 6;//吕布 二组主管
        }
        page = inventoryService.queryByPage(page, inventoryParam,principalId);
        return page;
    }
}

package com.ztkj.purchase.controller;

import com.ztkj.purchase.model.GoodsType;
import com.ztkj.purchase.param.GoodsTypeParam;
import com.ztkj.purchase.service.GoodsTypeService;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Description:品牌类型
 * @Author:nieyf
 * @Date:2019/11/20 21:10
 */
@Controller
@RequestMapping("purchase/productType")
public class GoodsTypeController {
    @Resource
    private GoodsTypeService goodsTypeService;
    /**
     *分页查询商品类型
     * @param: [pageNo, goodsTypeParam]
     * @return: com.ztkj.utils.Page
     * @author: nyf
     * @date: 2019/11/22 16:48
     */
    @RequestMapping("gtpage")
    public @ResponseBody Page page(Integer pageNo, GoodsTypeParam goodsTypeParam){
        if (pageNo==null||pageNo<1){
            pageNo=1;
        }
        Page page=new Page(pageNo,5);
        page=goodsTypeService.queryByPage(page,goodsTypeParam);
        return page;
    }
    @RequestMapping("add")
    public @ResponseBody String add(GoodsType goodsType, HttpSession session){
        String info="商品类型添加成功！";
        User user = (User) session.getAttribute("loginUser");
        goodsType.setCreateUser(user);
        try {
            goodsTypeService.add(goodsType);
        } catch (Exception e) {
            e.printStackTrace();
            info="商品类型添加失败！";
        }
        return info;
    }
    /**
     *到达修改页面
     * @param: [id]
     * @return: com.ztkj.purchase.model.GoodsType
     * @author: nyf
     * @date: 2019/11/22 16:52
     */
    @RequestMapping("gttupdate")
    public @ResponseBody GoodsType queryToUpdate(@RequestParam("id") int id){
        return goodsTypeService.queryById(id);
    }
    /**
     *修改商品类型
     * @param: [goodsType]
     * @return: java.lang.String
     * @author: nyf
     * @date: 2019/11/22 16:52
     */
    @RequestMapping("gtupdate")
    public @ResponseBody String update(GoodsType goodsType){
        String info="品牌信息修改成功！";
        try {
            goodsTypeService.update(goodsType);
        } catch (Exception e) {
            e.printStackTrace();
            info="品牌信息修改失败！";
        }
        return info;
    }
    @RequestMapping("can")
    public @ResponseBody String cancel(int id){
        try {
            goodsTypeService.cancelGoodsType(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @RequestMapping("rec")
    public @ResponseBody String recover(int id){
        try {
            goodsTypeService.recoverGoodsType(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

}

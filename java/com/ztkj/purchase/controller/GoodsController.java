package com.ztkj.purchase.controller;

import com.ztkj.purchase.model.Brand;
import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.GoodsType;
import com.ztkj.purchase.param.GoodsParam;
import com.ztkj.purchase.service.GoodsService;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:商品的控制层
 * @Author:nieyf
 * @Date:2019/11/22 20:56
 */
@Controller
@RequestMapping("purchase/product")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @RequestMapping("gpage")
    public @ResponseBody Page page(Integer pageNo, GoodsParam goodsParam){
        if (pageNo==null||pageNo<1){
            pageNo=1;
        }
        Page page=new Page(pageNo,5);
        page=goodsService.queryByPage(page,goodsParam);
        return page;
    }
    /**
     *查询所有的品牌
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Brand>
     * @auther: nyf
     * @date: 2019/11/24 11:26
     */
    @RequestMapping("brand")
    public @ResponseBody List<Brand> queryAllBrand(){
        return goodsService.queryAllBrand();
    }
    /**
     *根据品牌id查询商品类型
     * @param: [brandId]
     * @return: java.util.List<com.ztkj.purchase.model.GoodsType>
     * @auther: nyf
     * @date: 2019/11/24 11:26
     */
    @RequestMapping("goodsType")
    public @ResponseBody List<GoodsType> queryAllGoodsType(int brandId){
        return goodsService.queryAllGoodsTypeByBrandId(brandId);
    }
    @RequestMapping("add")
    public @ResponseBody String add(Goods goods, HttpSession session){
        String info="商品添加成功！";
        User user = (User) session.getAttribute("loginUser");
        goods.setCreateUser(user);
        try {
            goodsService.add(goods);
        } catch (Exception e) {
            e.printStackTrace();
            info="商品添加失败！";
        }
        return info;
    }
    @RequestMapping("gtupdate")
    public @ResponseBody Goods queryToUpdate(@RequestParam("id") int id){
        return goodsService.queryById(id);
    }
    @RequestMapping("gupdate")
    public @ResponseBody String update(Goods goods){
        String info="品牌信息修改成功！";
        try {
            goodsService.update(goods);
        } catch (Exception e) {
            e.printStackTrace();
            info="品牌信息修改失败！";
        }
        return info;
    }
    @RequestMapping("can")
    public @ResponseBody String cancel(int id){
        try {
            goodsService.cancelGoods(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @RequestMapping("rec")
    public @ResponseBody String recover(int id){
        try {
            goodsService.recoverGoods(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
}

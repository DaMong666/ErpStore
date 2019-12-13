package com.ztkj.purchase.controller;

import com.ztkj.purchase.model.Brand;
import com.ztkj.purchase.param.BrandParam;
import com.ztkj.purchase.service.BrandService;
import com.ztkj.sys.model.User;
import com.ztkj.utils.DateUtil;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:品牌
 * @Author:nieyf
 * @Date:2019/11/20 19:45
 */
@Controller
@RequestMapping("purchase/brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    /**
     *分页查询
     * @param: [pageNo, brandParam]
     * @return: com.ztkj.utils.Page
     * @author: nyf
     * @date: 2019/11/20 19:51
     */
    @RequestMapping("bpage")
    public @ResponseBody Page queryPage(Integer pageNo, BrandParam brandParam){
        if (pageNo==null||pageNo<1){
            pageNo=1;
        }
        Page page=new Page(pageNo,5);
        page=brandService.queryByPage(page,brandParam);
        return page;
    }
    @RequestMapping("add")
    public @ResponseBody String add(Brand brand, HttpSession session){
        String info="品牌信息添加成功！";
        User user = (User) session.getAttribute("loginUser");
        brand.setCreateUser(user);
        try {
            brandService.add(brand);
        } catch (Exception e) {
            e.printStackTrace();
            info="品牌信息添加失败！";
        }
        return info;
    }
    @RequestMapping("btupdate")
    public @ResponseBody Brand queryToUpdate(@RequestParam("id") int id){
        return brandService.queryById(id);
    }
    @RequestMapping("bupdate")
    public @ResponseBody String update(Brand brand){
        String info="品牌信息修改成功！";
        try {
            brandService.update(brand);
        } catch (Exception e) {
            e.printStackTrace();
            info="品牌信息修改失败！";
        }
        return info;
    }
    /**
     *注销品牌
     * @param: [brand]
     * @return: java.lang.String
     * @author: nyf
     * @date: 2019/11/21 17:07
     */
    @RequestMapping("can")
    public @ResponseBody String cancel(int id){
        try {
            brandService.cancelBrand(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @RequestMapping("rec")
    public @ResponseBody String recover(int id){
        try {
            brandService.recoverBrand(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }


    @RequestMapping("curruser")
    public @ResponseBody User queryCurrentUser(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        return user;
    }
    @RequestMapping("currdate")
    public @ResponseBody String queryDate(){
        return DateUtil.currentDate("yyyy-MM-dd HH:mm:ss");
    }
    @RequestMapping("brandList")
    public @ResponseBody List<Brand> queryAllBrand(){
        return brandService.queryAll();
    }
}

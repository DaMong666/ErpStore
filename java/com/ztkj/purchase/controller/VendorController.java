package com.ztkj.purchase.controller;

/**
 * @Description:厂商的控制层
 * @Author:nieyf
 * @Date:2019/11/21 19:52
 */

import com.ztkj.purchase.model.Vendor;
import com.ztkj.purchase.param.VendorParam;
import com.ztkj.purchase.service.VendorService;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Province;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("purchase/manufacturer")
public class VendorController {
    @Resource
    private VendorService vendorService;
    @RequestMapping("vpage")
    public @ResponseBody Page queryPage(Integer pageNo, VendorParam vendorParam){
        if (pageNo==null||pageNo<1){
            pageNo=1;
        }
        Page page=new Page(pageNo,5);
        page=vendorService.queryByPage(page,vendorParam);
        return page;
    }
    /**
     *查询所有的省份
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @author: nyf
     * @date: 2019/11/21 21:48
     */
    @RequestMapping("province")
    public @ResponseBody List<Province> queryProvinceList(){
        return vendorService.queryAllProvince();
    }
    @RequestMapping("city")
    public @ResponseBody List<City> queryCityList(int provinceId){
        return vendorService.queryAllCityByProId(provinceId);
    }
    @RequestMapping("vadd")
    public @ResponseBody String add(Vendor vendor, HttpSession session){
        String info="品牌信息添加成功！";
        User user = (User) session.getAttribute("loginUser");
        vendor.setCreateUser(user);
        try {
            vendorService.add(vendor);
        } catch (Exception e) {
            e.printStackTrace();
            info="品牌信息添加失败！";
        }
        return info;
    }
    @RequestMapping("vtupdate")
    public @ResponseBody Vendor queryToUpdate(@RequestParam("id") int id){
        return vendorService.queryById(id);
    }
    @RequestMapping("vupdate")
    public @ResponseBody String update(Vendor vendor){
        String info="品牌信息修改成功！";
        try {
            vendorService.update(vendor);
        } catch (Exception e) {
            e.printStackTrace();
            info="品牌信息修改失败！";
        }
        return info;
    }
    @RequestMapping("can")
    public @ResponseBody String cancel(int id){
        try {
            vendorService.cancelVendor(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @RequestMapping("rec")
    public @ResponseBody String recover(int id){
        try {
            vendorService.recoverVendor(id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
    @RequestMapping("vendorList")
    public @ResponseBody List<Vendor> queryAll(){
        return vendorService.queryAll();
    }
}

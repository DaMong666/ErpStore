package com.ztkj.market.controller;

import com.ztkj.market.model.Customer;
import com.ztkj.market.service.CustomerService;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.Province;
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
 * @Description: 客户
 * @Auther: YDM
 * @Date: 2019/11/20 19:45
 */
@Controller
@RequestMapping("market/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    /**
     * @description: 客户管理分页查询
     * @param: [pageNo, customer]
     * @return: com.ztkj.utils.Page
     * @auther: YDM
     * @date: 2019/11/20 19:51
     */
    @RequestMapping("cusPage")
    public @ResponseBody
    Page queryPage(Integer pageNo, Customer customer, Integer provinceId, Integer cityId, Integer userIsNull,HttpSession session) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        Page page = new Page(pageNo, 5);
        User user = (User) session.getAttribute("loginUser");
        page = customerService.queryByPage(page, customer, provinceId, cityId, userIsNull,user);
        return page;
    }

    /**
     * @description: 查看详情（根据Id查询）
     * @param: [id]
     * @return: com.ztkj.market.model.Customer
     * @auther: YDM
     * @date: 2019/11/21 20:14
     */
    @RequestMapping("cusById")
    public @ResponseBody
    Customer findBypage(int id) {
        Customer customer = customerService.queryById(id);
        return customer;
    }

    @RequestMapping("addCus")
    public @ResponseBody String add(Customer customer, HttpSession session, @RequestParam("createTime") String createTime) {
        String info = "客户信息添加成功！";
        System.out.println(customer.getName());
        User user = (User) session.getAttribute("loginUser");
        customer.setCreateUser(user);
        customer.setCreateTime(DateUtil.stringToTimestamp(createTime));
        try {
            customerService.addCus(customer);
        } catch (Exception e) {
            e.printStackTrace();
            info = "客户信息添加失败！";
        }
        return info;
    }

    /*
     *查询所有省份下拉框
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @auther: huangx
     * @date: 2019/11/21 9:55
     */
    @RequestMapping("province")
    public @ResponseBody
    List<Province> queryProvinceList(){
        //@ResponseBody把List<Employee>转json字符串
        // 先到浏览器上访问验证
        return customerService.queryAllProvince();
    }
    /*
     *查询所有省份下拉框
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @auther: huangx
     * @date: 2019/11/21 9:55
     */
    @RequestMapping("loadcity")
    public @ResponseBody List<City> queryCity(){
        return customerService.queryAllCity();
    }
    /*
     *查询所有城市下拉框
     * @param: [provinceId]
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @auther: huangx
     * @date: 2019/11/21 18:38
     */
    @RequestMapping("city")
    public @ResponseBody List<City> queryCityList(int provinceId){
        return customerService.queryAllCityBypId(provinceId);
    }

    /**
     * @description: 修改客户信息
     * @param: [storage, createTime, session]
     * @return: java.lang.String
     * @auther: YDM
     * @date: 2019/11/22 16:38
     */
    @RequestMapping("upCus")
    public @ResponseBody String updateCus(Customer customer){
        String info = "";
        try{
            customerService.updateCus(customer);
            info = "客户信息修改成功！";
        }catch (Exception e){
            e.printStackTrace();
            info = "客户信息修改失败";
        }
        return info;
    }

    /**
     *查询可用客户
     * @param: []
     * @return: java.util.List<com.ztkj.market.model.Customer>
     * @author: liss
     * @date: 2019/11/25 14:24
     */

    @RequestMapping("findNice")
    public @ResponseBody List<Customer> queryNice(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        return customerService.queryNiceCustomer(user);
    }

    /*恢复 + 注销 +分配*/
    @RequestMapping("querypositionlist")
    public @ResponseBody List<Position> queryPositionList(){
        return customerService.queryPosition();
    }

    @RequestMapping("queryfenpeiuser")
    public @ResponseBody List<User> queryFenpeiUser(String positionno){
        return customerService.queryCheckUser(positionno);
    }

    @RequestMapping("statusup")
    public @ResponseBody String updateStatusUp(int id){
        String info = "";
        try {
            customerService.updateStatusUp(id);
            info = "恢复成功！！！";
        } catch (Exception e) {
            info = "恢复失败！！！";
            e.printStackTrace();
        }
        return info;
    }

    @RequestMapping("statusdown")
    public @ResponseBody String updateStatusDown(int id){
        String info = "";
        try {
            customerService.updateStatusDown(id);
            info = "注销成功！！！";
        } catch (Exception e) {
            info = "注销失败！！！";
            e.printStackTrace();
        }
        return info;
    }

    @RequestMapping("fenpeiuser")
    public @ResponseBody String updateFenpeiUser(int cusid, User userid){
        String info = "";
        try {
            customerService.updateUser(cusid,userid);
            info = "分配成功！！！";
        } catch (Exception e) {
            info = "分配失败！！！";
            e.printStackTrace();
        }
        return info;
    }
}

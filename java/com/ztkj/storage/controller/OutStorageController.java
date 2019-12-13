package com.ztkj.storage.controller;

import com.ztkj.market.model.Order;
import com.ztkj.market.model.OrderDetail;
import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.storage.dao.StorageDao;
import com.ztkj.storage.model.InStorage;
import com.ztkj.storage.model.Inventory;
import com.ztkj.storage.model.OutStorage;
import com.ztkj.storage.model.Storage;
import com.ztkj.storage.param.InStorageParam;
import com.ztkj.storage.param.OutStorageParam;
import com.ztkj.storage.service.OutStorageService;
import com.ztkj.sys.model.User;
import com.ztkj.utils.DateUtil;
import com.ztkj.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:出库表控制层
 * @Auther: huangx
 * @Date: 2019/11/25 17:18
 */
@Controller
@RequestMapping("storage/outstorage")
public class OutStorageController {
    @Resource
    private StorageDao storageDao;
    @Resource
    private OutStorageService outStorageService;
    /*
     *出库分页+模糊
     * @param: [pageNo, inStorageParam]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/25 17:22
     */
    @RequestMapping("outstoragepage")
    public @ResponseBody Page queryPage(Integer pageNo, OutStorageParam outStorageParam,HttpSession session){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        Page page = new Page(pageNo,4);//当前页，每页2条
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
        page = outStorageService.queryByPage(page, outStorageParam,principalId);
        return page;
    }
    /*
     *根据订单号获取订单
     * @param: [orderNo]
     * @return: com.ztkj.market.model.Order
     * @auther: huangx
     * @date: 2019/11/25 19:21
     */
    @RequestMapping("getOrder")
    public @ResponseBody Order queryOrder(String orderNo){
        return outStorageService.queryOrderById(orderNo);
    }
    /*
     *根据订单号获取出库表
     * @param: [orderNo]
     * @return: com.ztkj.storage.model.OutStorage
     * @auther: huangx
     * @date: 2019/11/25 19:22
     */
    @RequestMapping("getOutStorage")
    public @ResponseBody OutStorage queryOutStorage(String orderNo){
        return outStorageService.queryOutStorageById(orderNo);
    }
    /*
     *根据订单号获取采购明细
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.market.model.OrderDetail>
     * @auther: huangx
     * @date: 2019/11/25 19:24
     */
    @RequestMapping("getOrderDetail")
    public @ResponseBody List<OrderDetail> queryOrderDetail(String orderNo){
        return outStorageService.queryOrderDetailById(orderNo);
    }
    @RequestMapping("getorderstatus2")
    public @ResponseBody List<Order> queryOrderNoStatus2(){
        return outStorageService.queryOrderStatus2();
    }
    @RequestMapping("addoutstorage")
    public @ResponseBody String addOutStorage(OutStorage outStorage, @RequestParam("createTime") String createTime){
        outStorage.setStatus("1");
        outStorage.setCreateTime(DateUtil.stringToTimestamp(createTime));//把页面的时间转换成Date
        Storage storage = storageDao.findStorageById(outStorage.getStorage().getId());
        User user = new User();
        outStorage.setStorage(storage);
        user.setId(storage.getPrincipalPerson().getId());
        outStorage.setOutPerson(user);
        String info = "";
        try{
            OutStorage outStorage1 = outStorageService.queryOutstorageByorderNoAndStorageId(outStorage.getOrder().getOrderNo(),outStorage.getStorage().getId());
            if(outStorage1 == null){
                outStorageService.addOutStorage(outStorage);
                info = "添加出库成功！";
            }else {
                info = "添加出库失败，不能重复提交同一订单！";
            }

        }catch (Exception e){
            e.printStackTrace();
            info = "添加出库失败";
        }
        return info;
    }
    /*
     *根据订购单找到出库仓库  再找到该仓库库存
     * @param: [id]
     * @return: java.util.List<com.ztkj.storage.model.Inventory>
     * @auther: huangx
     * @date: 2019/11/26 13:06
     */
    @RequestMapping("getinventoryById")
    public @ResponseBody List<Inventory> queryInventory(String id){
        return outStorageService.queryInventoryById(id);
    }
    /*
     *发货
     * @param: [id]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/26 22:51
     */
    @RequestMapping("fahuo")
    public @ResponseBody String updatesubinventory(@Param("id")String id){
        String info = "";
        try{
            outStorageService.updatefahuoStatus(outStorageService.queryOutStorageById(id));
            if (outStorageService.queryOutStorageById(id).getStatus().equals("1")){
                info="0";
            }
            if (outStorageService.queryOutStorageById(id).getStatus().equals("2")){
                info = "1";
            }
        }catch (Exception e){
            e.printStackTrace();
            info = "0";
        }
        return info;
    }
    /*
     *未发货状态取消订单
     * @param: [id]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/27 11:15
     */
    @RequestMapping("weifahuoquxiaodingdan")
    public @ResponseBody String updateweifastatustoquxiao(@Param("id")String id){
        String info = "";
        try{
            outStorageService.updateweifahuototuihuo(outStorageService.queryOutStorageById(id));
            if (outStorageService.queryOutStorageById(id).getStatus().equals("1")){
                info="0";
            }
            if (outStorageService.queryOutStorageById(id).getStatus().equals("5")){
                info = "1";
            }
        }catch (Exception e){
            e.printStackTrace();
            info = "0";
        }
        return info;
    }
    /*
     *已发货状态下取消订单
     * @param: [id]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/27 11:15
     */
    @RequestMapping("yifahuoquxiaodingdan")
    public @ResponseBody String updateyifahuoquxiaodingdan(@Param("id")String id){
        String info = "";
        try{
            outStorageService.updateyifahuotoquxiaodingdan(outStorageService.queryOutStorageById(id));
            if (outStorageService.queryOutStorageById(id).getStatus().equals("2")){
                info="0";
            }
            if (outStorageService.queryOutStorageById(id).getStatus().equals("4")){
                info = "1";
            }
        }catch (Exception e){
            e.printStackTrace();
            info = "0";
        }
        return info;
    }
    /*
     *确认已退货，加上库存
     * @param: [id]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/27 11:25
     */
    @RequestMapping("qurenyituihuo")
    public @ResponseBody String updatequrentuihuo(@Param("id")String id){
        String info = "";
        try{
            outStorageService.updatequerentuihuoStatus(outStorageService.queryOutStorageById(id));
            if (outStorageService.queryOutStorageById(id).getStatus().equals("4")){
                info="0";
            }
            if (outStorageService.queryOutStorageById(id).getStatus().equals("5")){
                info = "1";
            }
        }catch (Exception e){
            e.printStackTrace();
            info = "0";
        }
        return info;
    }
    @RequestMapping("yifahuotoquerenhuikuan")
    public @ResponseBody String updateyifahuotoquerenhuikuan(@Param("id")String id){
        String info = "";
        try{
            outStorageService.updateyifahuotoyihuikuan(outStorageService.queryOutStorageById(id));
            if (outStorageService.queryOutStorageById(id).getStatus().equals("2")){
                info="0";
            }
            if (outStorageService.queryOutStorageById(id).getStatus().equals("3")){
                info = "1";
            }
        }catch (Exception e){
            e.printStackTrace();
            info = "0";
        }
        return info;
    }
}

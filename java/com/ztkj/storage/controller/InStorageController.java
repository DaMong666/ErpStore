package com.ztkj.storage.controller;

import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.purchase.model.Vendor;
import com.ztkj.storage.dao.StorageDao;
import com.ztkj.storage.model.InStorage;
import com.ztkj.storage.model.Inventory;
import com.ztkj.storage.model.Storage;
import com.ztkj.storage.param.InStorageParam;
import com.ztkj.storage.service.InStorageService;
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
 * @Description:入库表控制层
 * @Auther: huangx
 * @Date: 2019/11/24 10:54
 */
@Controller
@RequestMapping("storage/instorage")
public class InStorageController {
    @Resource
    private StorageDao storageDao;
    @Resource
    private InStorageService inStorageService;
    /*
     *入库模糊+分页
     * @param: [pageNo, inStorageParam]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/24 10:58
     */
    @RequestMapping("instoragepage")
    public @ResponseBody Page queryPage(Integer pageNo, InStorageParam inStorageParam,HttpSession session){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        //获取当前登录对象
        User user = (User) session.getAttribute("loginUser");
        //获取到登录对象的id主键（负责人）
        int principalId = user.getId();
        Integer.valueOf(principalId);
        System.out.println("==========Integer.valueOf(principalId);============"+Integer.valueOf(principalId));
        Page page = new Page(pageNo,2);//当前页，每页2条
        if(user.getNo().substring(0, 7).equals("0400500")){//一组组员04005001
            principalId = 101;//老马 一组主管
        }
        if(user.getNo().substring(0, 7).equals("0400700")){//二组组员04007001
            principalId = 6;//吕布 二组主管
        }
        page = inStorageService.queryByPage(page, inStorageParam,principalId);
        return page;
    }
    /*
     *根据采购单编号获取采购单
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.Purchase
     * @auther: huangx
     * @date: 2019/11/24 15:25
     */
    @RequestMapping("getPurchase")
    public @ResponseBody Purchase queryPurchase(String purchaseNo){
        return inStorageService.queryPurchaseById(purchaseNo);
    }
    /*
     *根据采购单编号获取入库单
     * @param: [purchaseNo]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/11/24 15:27
     */
    @RequestMapping("getInStorage")
    public @ResponseBody InStorage queryInStorage(String purchaseNo){
        return inStorageService.queryInStorageById(purchaseNo);
    }
    /*
     *根据采购单编号获取采购明细单
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.PurchaseDetail
     * @auther: huangx
     * @date: 2019/11/24 15:27
     */
    @RequestMapping("getPurchaseDetail")
    public @ResponseBody List<PurchaseDetail> queryPurchaseDetail(String purchaseNo){
        return inStorageService.queryPurchaseDetailById(purchaseNo);
    }
    /*
     *实时查询采购单号
     * @param: [purchaseNo]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/25 10:01
     */
    @RequestMapping("getPurchaseNo")
    public @ResponseBody List<String> queryPurchaseNo(String purchaseNo){
        return inStorageService.queryPurchaseNo(purchaseNo);
    }
    /*
     *查询已通过审核的采购单
     * @param: [purchaseNo]
     * @return: java.util.List<java.lang.String>
     * @auther: huangx
     * @date: 2019/12/1 14:55
     */
    @RequestMapping("getpurchasestatus2")
    public @ResponseBody List<Purchase> queryPurchaseNoStatus2(){
        return inStorageService.queryAllPurchase2();
    }
    /*
     *查出所有状态可用得仓库
     * @param: []
     * @return: java.util.List<com.ztkj.storage.model.Storage>
     * @auther: huangx
     * @date: 2019/11/25 11:28
     */
    @RequestMapping("getallstorage")
    public @ResponseBody List<Storage> queryAllStorage(HttpSession session){
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
        return inStorageService.queryAllStorage(principalId);
    }

    @RequestMapping("addinstorage")
    public @ResponseBody String addInStorage(InStorage inStorage, @RequestParam("createTime") String createTime){
        inStorage.setStatus("1");
        inStorage.setCreateTime(DateUtil.stringToTimestamp(createTime));//把页面的时间转换成Date
        Storage storage = storageDao.findStorageById(inStorage.getStorage().getId());
        User user = new User();
        inStorage.setStorage(storage);
        user.setId(storage.getPrincipalPerson().getId());
        inStorage.setInPerson(user);
        String info = "";
        try{
            InStorage inStorage1 = inStorageService.queryInstorageBypurchaseNoAndStorageId(inStorage.getPurchase().getPurchaseNo(), inStorage.getStorage().getId());
            if(inStorage1 == null){
                inStorageService.addInStorage(inStorage);
                info = "添加入库成功！";
            }else{
                info = "添加入库失败，不能重复提交同一订单！";
            }
        }catch (Exception e){
            e.printStackTrace();
            info = "添加入库失败";
        }
        return info;
    }

    /*
     *根据采购单找到入库仓库  再找到该仓库库存
     * @param: [id]
     * @return: java.util.List<com.ztkj.storage.model.Inventory>
     * @auther: huangx
     * @date: 2019/11/26 13:06
     */
    @RequestMapping("getinventoryById")
    public @ResponseBody List<Inventory> queryInventory(String id){
        return inStorageService.queryInventoryById(id);
    }
    /*
     *入库
     * @param: []
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/26 9:34
     */
    @RequestMapping("insto")
    public @ResponseBody String addinventory(@Param("id")String id){
        String info = "";
        try{
            inStorageService.updateinsoStatus(inStorageService.queryInStorageById(id));
            info = "1";
        }catch (Exception e){
            e.printStackTrace();
            info = "0";
        }
        return info;
    }
    /*
     *取消入库
     * @param: [id]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/26 22:50
     */
    @RequestMapping("outsto")
    public @ResponseBody String deloutinventory(@Param("id")String id){
        String info = "";
        try{
            inStorageService.updateinsoStatus(inStorageService.queryInStorageById(id));
            info = "1";
        }catch (Exception e){
            e.printStackTrace();
            info = "0";
        }
        return info;
    }
}

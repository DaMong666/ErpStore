package com.ztkj.storage.service.impl;

import com.ztkj.market.model.Order;
import com.ztkj.market.model.OrderDetail;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.storage.dao.OutStorageDao;
import com.ztkj.storage.model.InStorage;
import com.ztkj.storage.model.Inventory;
import com.ztkj.storage.model.OutStorage;
import com.ztkj.storage.model.Storage;
import com.ztkj.storage.param.OutStorageParam;
import com.ztkj.storage.service.OutStorageService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: huangx
 * @Date: 2019/11/23 21:24
 */
@Service("outStorageService")
public class OutStorageServiceImpl implements OutStorageService {
    @Resource
    private OutStorageDao outStorageDao;
    /*
     *根据输入的信息实时查询出通过审核的订单
     * @param: [name]
     * @return: java.util.List<com.ztkj.market.model.Order>
     * @auther: huangx
     * @date: 2019/11/23 21:23
     */
    public List<Order> queryAllOrderNo(String name){
        return outStorageDao.findAllOrderNo(name);
    }
    /*
     *查询出所有状态为可用的仓库
     * @param: []
     * @return: java.util.List<com.ztkj.storage.model.Storage>
     * @auther: huangx
     * @date: 2019/11/23 21:22
     */
    public List<Storage> queryAllStorage(){
        return outStorageDao.findAllStorage();
    }
    /*
     *出库表模糊+分页
     * @param: [page, inStorageParam]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/25 17:16
     */
    public Page queryByPage(Page page, OutStorageParam outStorageParam,Integer principalId){
        List<OutStorage> outStorageList = outStorageDao.findAllByPage(outStorageParam, page.getStartIndex(), page.getEndIndex(),principalId);
        int rowCount = outStorageDao.countByPage(outStorageParam,principalId);
        page.setResult(outStorageList, rowCount);
        return page;
    }
    /*
     *根据订单号获取订单
     * @param: [orderNo]
     * @return: com.ztkj.market.model.Order
     * @auther: huangx
     * @date: 2019/11/25 19:15
     */
    public Order queryOrderById(String orderNo){
        return outStorageDao.findOrderById(orderNo);
    }
    /*
     *根据订单号获取出库表
     * @param: [orderNo]
     * @return: com.ztkj.storage.model.OutStorage
     * @auther: huangx
     * @date: 2019/11/25 19:16
     */
    public OutStorage queryOutStorageById(String orderNo){
        return outStorageDao.findOutStorageById(orderNo);
    }
    /*
     *根据订单号获取采购明细
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.market.model.OrderDetail>
     * @auther: huangx
     * @date: 2019/11/25 19:17
     */
    public List<OrderDetail> queryOrderDetailById(String orderNo){
        return outStorageDao.findOrderDetailById(orderNo);
    }
    /*
     *查询所有通过审核的订购单
     * @param: []
     * @return: java.util.List<com.ztkj.market.model.Order>
     * @auther: huangx
     * @date: 2019/12/1 15:59
     */
    public List<Order> queryOrderStatus2(){
        return outStorageDao.findAllOrderStatus2();
    }
    /*
     *添加库单
     * @para出m: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 20:21
     */
    @Transactional
    public void addOutStorage(OutStorage outStorage){
        outStorageDao.addOutStorage(outStorage);
    }
    /*
     *根据订购单找到出库仓库  再找到该仓库库存
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.storage.model.Inventory>
     * @auther: huangx
     * @date: 2019/11/26 22:47
     */
    public List<Inventory> queryInventoryById(String orderNo){
        return outStorageDao.findInventoryById(orderNo);
    }
    /*
     *发货改状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    @Transactional
    public boolean updatefahuoStatus(OutStorage outStorage){
        boolean result=true;
        //获取到出库的仓库
        Storage storage = outStorage.getStorage();
        //获取到订购单明细
        List<OrderDetail> orderDetails = outStorageDao.findOrderDetailById(outStorage.getOrder().getOrderNo());
        a: for (OrderDetail o:orderDetails) {
            Inventory inventory = new Inventory();
            inventory.setStorage(storage);
            inventory.setGoods(o.getGoods());
            inventory.setVendor(o.getVendor());
            //查询是否存在记录count
            int num = outStorageDao.count(inventory);
            if(num < o.getNum()){
                //存在库存不够的，不改状态
                result = false;
                break a;
            }else{
                Inventory inventory1 = new Inventory();
                inventory1.setStorage(storage);
                inventory1.setNum(o.getNum());
                inventory1.setGoods(o.getGoods());
                inventory1.setVendor(o.getVendor());
                //库存足够，减数量
                outStorageDao.updatesubInventoryNum(inventory1);
            }
        }
        if(result){
            //改状态
            outStorageDao.fahuo(outStorage);
        }
        return result;
    }
    /*
     *发货，减少库存数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 15:47
     */
    @Transactional
    public void updatesubInventoryNum(Inventory inventory){
        outStorageDao.updatesubInventoryNum(inventory);
    }
    /*
     *退货，增加库存数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 13:56
     */
    @Transactional
    public void updateaddInventoryNum(Inventory inventory){
        outStorageDao.updateaddInventoryNum(inventory);
    }
    /*
     *查询库存中商品数量
     * @param: [inventory]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/26 18:46
     */
    public int count(Inventory inventory){
        return outStorageDao.count(inventory);
    }
    /*
     *未发货发货改已退货状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    @Transactional
    public void updateweifahuototuihuo(OutStorage outStorage){
        outStorageDao.weifatotuihuo(outStorage);
    }
    /*
     *已发货改取消订单状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    @Transactional
    public void updateyifahuotoquxiaodingdan(OutStorage outStorage){
        outStorageDao.yifatoquxiaodingdan(outStorage);
    }
    /*
     *确认已退货改状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    @Transactional
    public void updatequerentuihuoStatus(OutStorage outStorage){
        //获取到出库的仓库
        Storage storage = outStorage.getStorage();
        //获取到订购单明细
        List<OrderDetail> orderDetails = outStorageDao.findOrderDetailById(outStorage.getOrder().getOrderNo());
        a: for (OrderDetail o:orderDetails) {
                Inventory inventory1 = new Inventory();
                inventory1.setStorage(storage);
                inventory1.setNum(o.getNum());
                inventory1.setGoods(o.getGoods());
                inventory1.setVendor(o.getVendor());
                //退货，加数量
                outStorageDao.updateaddInventoryNum(inventory1);
        }
        //改状态
        outStorageDao.querentuihuo(outStorage);
    }
    /*
     *已发货改已回款状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    @Transactional
    public void updateyifahuotoyihuikuan(OutStorage outStorage){
        outStorageDao.yifatoyihuikuan(outStorage);
    }
    /*
     *根据仓库id和订单号查询出库信息
     * @param: [purchaseNo, storageId]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/12/1 17:36
     */
    public OutStorage queryOutstorageByorderNoAndStorageId(String orderNo,int storageId){
        return outStorageDao.findOutStorageByorderNoAndStorageId(orderNo, storageId);
    }
}

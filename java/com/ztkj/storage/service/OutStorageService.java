package com.ztkj.storage.service;

import com.ztkj.market.model.Order;
import com.ztkj.market.model.OrderDetail;
import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.storage.model.InStorage;
import com.ztkj.storage.model.Inventory;
import com.ztkj.storage.model.OutStorage;
import com.ztkj.storage.model.Storage;
import com.ztkj.storage.param.InStorageParam;
import com.ztkj.storage.param.OutStorageParam;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:
 * @Auther: huangx
 * @Date: 2019/11/23 21:21
 */
public interface OutStorageService {
    /*
     *根据输入的信息实时查询出通过审核的订单
     * @param: [name]
     * @return: java.util.List<com.ztkj.market.model.Order>
     * @auther: huangx
     * @date: 2019/11/23 21:23
     */
    public List<Order> queryAllOrderNo(String name);
    /*
     *查询出所有状态为可用的仓库
     * @param: []
     * @return: java.util.List<com.ztkj.storage.model.Storage>
     * @auther: huangx
     * @date: 2019/11/23 21:22
     */
    public List<Storage> queryAllStorage();
    /*
     *出库表模糊+分页
     * @param: [page, inStorageParam]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/25 17:16
     */
    public Page queryByPage(Page page, OutStorageParam outStorageParam,Integer principalId);
    /*
     *根据采购单号获取订单
     * @param: [orderNo]
     * @return: com.ztkj.market.model.Order
     * @auther: huangx
     * @date: 2019/11/25 19:15
     */
    public Order queryOrderById(String orderNo);
    /*
     *根据采购单号获取出库表
     * @param: [orderNo]
     * @return: com.ztkj.storage.model.OutStorage
     * @auther: huangx
     * @date: 2019/11/25 19:16
     */
    public OutStorage queryOutStorageById(String orderNo);
    /*
     *根据采购单号获取采购明细
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.market.model.OrderDetail>
     * @auther: huangx
     * @date: 2019/11/25 19:17
     */
    public List<OrderDetail> queryOrderDetailById(String orderNo);
    /*
     *查询所有通过审核的订购单
     * @param: []
     * @return: java.util.List<com.ztkj.market.model.Order>
     * @auther: huangx
     * @date: 2019/12/1 15:59
     */
    public List<Order> queryOrderStatus2();
    /*
     *添加出库单
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 20:21
     */
    public void addOutStorage(OutStorage outStorage);
    /*
     *根据订购单找到出库仓库  再找到该仓库库存
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.storage.model.Inventory>
     * @auther: huangx
     * @date: 2019/11/26 22:47
     */
    public List<Inventory> queryInventoryById(String orderNo);
    /*
     *发货改状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    public boolean updatefahuoStatus(OutStorage outStorage);
    /*
     *发货，减少库存数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 15:47
     */
    public void updatesubInventoryNum(Inventory inventory);
    /*
     *退货，增加库存数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 13:56
     */
    public void updateaddInventoryNum(Inventory inventory);
    /*
     *查询库存中商品数量
     * @param: [inventory]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/26 18:46
     */
    public int count(Inventory inventory);
    /*
     *未发货发货改已退货状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    public void updateweifahuototuihuo(OutStorage outStorage);
    /*
     *已发货改取消订单状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    public void updateyifahuotoquxiaodingdan(OutStorage outStorage);
    /*
     *确认已退货改状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    public void updatequerentuihuoStatus(OutStorage outStorage);
    /*
     *已发货改已回款状态
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:16
     */
    public void updateyifahuotoyihuikuan(OutStorage outStorage);
    /*
     *根据仓库id和订单号查询出库信息
     * @param: [purchaseNo, storageId]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/12/1 17:36
     */
    public OutStorage queryOutstorageByorderNoAndStorageId(String orderNo,int storageId);
}

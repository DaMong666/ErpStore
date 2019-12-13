package com.ztkj.storage.dao;

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
import com.ztkj.sys.model.Province;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:出库管理数据访问层
 * @Auther: huangx
 * @Date: 2019/11/22 15:59
 */
public interface OutStorageDao {

    /*
     *根据输入的信息实时查询出通过审核的订单
     * @param: [name]
     * @return: java.util.List<com.ztkj.market.model.Order>
     * @auther: huangx
     * @date: 2019/11/23 21:11
     */
    public List<Order> findAllOrderNo(@Param("name") String name);
    /*
     *查询出所有状态为可用的仓库
     * @param: []
     * @return: java.util.List<com.ztkj.storage.model.Storage>
     * @auther: huangx
     * @date: 2019/11/22 16:01
     */
    public List<Storage> findAllStorage();

   /*
    *查询出所有的出库信息    分页+模糊
    * @param: [outStorageParam, startIndex, endIndex]
    * @return: java.util.List<com.ztkj.storage.model.OutStorage>
    * @auther: huangx
    * @date: 2019/11/25 17:14
    */
    public List<OutStorage> findAllByPage(@Param("outStorageParam") OutStorageParam outStorageParam,
                                          @Param("startIndex") int startIndex,
                                          @Param("endIndex")int endIndex,
                                          @Param("principalId") Integer principalId);
    /*
     *分页统计
     * @param: [outStorageParam]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/25 17:14
     */
    public int countByPage(@Param("outStorageParam")OutStorageParam outStorageParam,
                           @Param("principalId") Integer principalId);
    /*
     *根据出库表订单编号查询订购表信息
     * @param: [orderNo]
     * @return: com.ztkj.market.model.Order
     * @auther: huangx
     * @date: 2019/11/25 18:30
     */
    public Order findOrderById(@Param("orderNo")String orderNo);
    /*
     *根据出库表订单编号查询出库表信息
     * @param: [orderNo]
     * @return: com.ztkj.storage.model.OutStorage
     * @auther: huangx
     * @date: 2019/11/25 18:30
     */
    public OutStorage findOutStorageById(@Param("orderNo")String orderNo);
    /*
     *根据出库表订单编号查询订购明细表信息
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.market.model.OrderDetail>
     * @auther: huangx
     * @date: 2019/11/25 18:30
     */
    public List<OrderDetail> findOrderDetailById(@Param("orderNo")String orderNo);
    /*
     *查询所有审核通过的订单
     * @param: []
     * @return: java.util.List<com.ztkj.market.model.Order>
     * @auther: huangx
     * @date: 2019/12/1 15:56
     */
    public List<Order> findAllOrderStatus2();
    /*
     *添加出库
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 20:17
     */
    public void addOutStorage(@Param("outStorage") OutStorage outStorage);
    /*
     *根据订购单找到出库仓库  再找到该仓库库存
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.storage.model.Inventory>
     * @auther: huangx
     * @date: 2019/11/26 22:42
     */
    public List<Inventory> findInventoryById(@Param("orderNo") String orderNo);
    /*
     *修改出库状态--发货·1-2
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 22:55
     */
    public void fahuo(@Param("outStorage") OutStorage outStorage);
    /*
     *发货，减库存
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 23:19
     */
    public void updatesubInventoryNum(@Param("inventory") Inventory inventory);
    /*
     *退货，增加库存
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 13:56
     */
    public void updateaddInventoryNum(@Param("inventory") Inventory inventory);
    /*
     *查询库存中商品的数目
     * @param: [inventory]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/26 18:46
     */
    public int count(@Param("inventory") Inventory inventory);
    /*
     *修改状态--未发货改已退货  1-5
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/27 10:08
     */
    public void weifatotuihuo(@Param("outStorage") OutStorage outStorage);
    /*
     *修改状态--已发货改取消订单 2-4
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/27 10:08
     */
    public void yifatoquxiaodingdan(@Param("outStorage") OutStorage outStorage);
    /*
     *修改状态--取消订单改已退货 4-5
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/27 11:26
     */
    public void querentuihuo(@Param("outStorage") OutStorage outStorage);
    /*
     *修改状态--已发货改已回款  2-3
     * @param: [outStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/27 10:08
     */
    public void yifatoyihuikuan(@Param("outStorage") OutStorage outStorage);
    /*
     *根据仓库id和订单号查询出库信息
     * @param: [orderNo, storageId]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/12/1 17:40
     */
    public OutStorage findOutStorageByorderNoAndStorageId(@Param("orderNo")String orderNo,
                                                           @Param("storageId")int storageId);

}

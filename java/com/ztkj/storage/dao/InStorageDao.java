package com.ztkj.storage.dao;

import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.storage.model.InStorage;
import com.ztkj.storage.model.Inventory;
import com.ztkj.storage.model.Storage;
import com.ztkj.storage.param.InStorageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:入库管理数据层
 * @Auther: huangx
 * @Date: 2019/11/24 10:09
 */
public interface  InStorageDao {

   /*
    *查询出所有的入库信息    分页+模糊
    * @param: [inStorageParam, startIndex, endIndex]
    * @return: java.util.List<com.ztkj.storage.model.InStorage>
    * @auther: huangx
    * @date: 2019/11/24 10:16
    */
    public List<InStorage> findAllByPage(@Param("inStorageParam")InStorageParam inStorageParam,
                                            @Param("startIndex") int startIndex,
                                            @Param("endIndex")int endIndex,
                                         @Param("principalId") Integer principalId);
    /*
     *分页统计
     * @param: [inStorageParam]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/24 10:17
     */
    public int countByPage(@Param("inStorageParam")InStorageParam inStorageParam,
                           @Param("principalId") Integer principalId);

    /*
     *根据入库表id查询采购表信息
     * @param: [inStorageId]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/11/24 14:07
     */
    public Purchase findPurchaseById(@Param("purchaseNo")String purchaseNo);
    /*
     *根据入库表id查询入库表信息
     * @param: [inStorageId]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/11/24 14:07
     */
    public InStorage findInStorageById(@Param("purchaseNo")String purchaseNo);
    /*
     *根据入库表id查询采购明细表信息
     * @param: [inStorageId]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/11/24 14:07
     */
    public List<PurchaseDetail> findPurchaseDetailById(@Param("purchaseNo")String purchaseNo);
    /*
     *实时查询通过审核的采购单单号
     * @param: [purchaseNo]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/25 9:51
     */
    public List<String> selectPurchaseNo(@Param("purchaseNo") String purchaseNo);
    /*
     *查询出所有通过审核的采购单
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Purchase>
     * @auther: huangx
     * @date: 2019/12/1 14:57
     */
    public List<Purchase> findAllPurchase2();
    /*
     *查询所有状态可用得仓库--当前登录对象管理的仓库
     * @param: []
     * @return: java.util.List<com.ztkj.storage.model.Storage>
     * @auther: huangx
     * @date: 2019/11/25 11:14
     */
    public List<Storage> findAllStorage(@Param("principalId") Integer principalId);
    /*
     *添加入库
     * @param: [inStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/25 16:09
     */
    public void addInstorage(@Param("inStorage") InStorage inStorage);
    /*
     *修改入库状态
     * @param: [purchaseNo]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 13:10
     */
    public void updateinsoStatus(@Param("inStorage") InStorage inStorage);
    /*
     *根据采购单找到入库仓库  再找到该仓库库存
     * @param: [storageId]
     * @return: java.util.List<com.ztkj.storage.model.Inventory>
     * @auther: huangx
     * @date: 2019/11/26 10:43
     */
    public List<Inventory> findInventoryById(@Param("purchaseNo") String purchaseNo);
    /*
     *添加一条新纪录到库存中
     * @param: [inStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 9:29
     */
    public void addInventory(@Param("inventory") Inventory inventory);
    /*
     *库存表存在记录，增加数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 13:56
     */
    public void updateaddInventoryNum(@Param("inventory") Inventory inventory);
    /*
     *取消入库，减数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 15:45
     */
    public void updatesubInventoryNum(@Param("inventory") Inventory inventory);
    /*
     *查询库存中是否有记录
     * @param: [inventory]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/26 18:46
     */
    public int count(@Param("inventory") Inventory inventory);
    /*
     *根据仓库id和采购单号查询入库信息
     * @param: [purchaseNo, storageId]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/12/1 17:31
     */
    public InStorage findInStorageBypurchaseNoAndStorageId(@Param("purchaseNo")String purchaseNo,
                                                           @Param("storageId")int storageId);
}

package com.ztkj.storage.service;

import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.storage.model.InStorage;
import com.ztkj.storage.model.Inventory;
import com.ztkj.storage.model.Storage;
import com.ztkj.storage.param.InStorageParam;
import com.ztkj.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:入库管理服务层
 * @Auther: huangx
 * @Date: 2019/11/24 10:47
 */
public interface InStorageService {
    /*
     *入库表模糊+分页
     * @param: [page, inStorageParam]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/24 10:49
     */
    public Page queryByPage(Page page, InStorageParam inStorageParam,Integer principalId);
    /*
     *根据purchaseNo采购单编号查询采购单类
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.Purchase
     * @auther: huangx
     * @date: 2019/11/24 15:18
     */
    public Purchase queryPurchaseById(String purchaseNo);
    /*
     *根据purchaseNo采购单编号查询入库单类
     * @param: [purchaseNo]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/11/24 15:21
     */
    public InStorage queryInStorageById(String purchaseNo);
    /*
     *根据purchaseNo采购单编号查询出库明细表类
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.PurchaseDetail
     * @auther: huangx
     * @date: 2019/11/24 15:21
     */
    public List<PurchaseDetail> queryPurchaseDetailById(String purchaseNo);
    /*
     *实时查询采购单号
     * @param: [purchaseNo]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/25 9:58
     */
    public List<String> queryPurchaseNo(String purchaseNo);
    /*
     *查询出所有通过审核的采购单
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Purchase>
     * @auther: huangx
     * @date: 2019/12/1 15:01
     */
    public List<Purchase> queryAllPurchase2();
    /*
     *查询出所有状态可用得仓库当前登录对象管理的仓库
     * @param: []
     * @return: java.util.List<com.ztkj.storage.model.Storage>
     * @auther: huangx
     * @date: 2019/11/25 11:18
     */
    public List<Storage> queryAllStorage(Integer principalId);
    /*
     *添加入库
     * @param: [inStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/25 16:31
     */
    public void addInStorage(InStorage inStorage);
    /*
     *修改入库状态
     * @param: [inStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 13:21
     */
    public void updateinsoStatus(InStorage inStorage);
    /*
     *根据采购单找到入库仓库  再找到该仓库库存
     * @param: [purchaseNo]
     * @return: java.util.List<com.ztkj.storage.model.Inventory>
     * @auther: huangx
     * @date: 2019/11/26 11:02
     */
    public List<Inventory> queryInventoryById(String purchaseNo);
    /*
     *添加一条新纪录到库存中
     * @param: [inStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 9:29
     */
    public void addInventory(Inventory inventory);
    /*
     *库存表存在记录，改数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 13:56
     */
    public void updateaddInventoryNum(Inventory inventory);
    /*
     *取消入库，减少库存数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 15:47
     */
    public void updatesubInventoryNum(Inventory inventory);
    /*
     *查询库存中是否有记录
     * @param: [inventory]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/26 18:46
     */
    public int count(Inventory inventory);
    /*
     *根据仓库id和采购单号查询入库信息
     * @param: [purchaseNo, storageId]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/12/1 17:36
     */
    public InStorage queryInstorageBypurchaseNoAndStorageId(String purchaseNo,int storageId);
}

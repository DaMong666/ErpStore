package com.ztkj.storage.service.impl;

import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.storage.dao.InStorageDao;
import com.ztkj.storage.model.InStorage;
import com.ztkj.storage.model.Inventory;
import com.ztkj.storage.model.Storage;
import com.ztkj.storage.param.InStorageParam;
import com.ztkj.storage.service.InStorageService;
import com.ztkj.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:入库表服务层实现类
 * @Auther: huangx
 * @Date: 2019/11/24 10:50
 */
@Service("inStorageService")
public class InStorageServiceImpl implements InStorageService {
    @Resource
    private InStorageDao inStorageDao;
    /*
     *入库表模糊+分页
     * @param: [page, inStorageParam]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/24 10:49
     */
    public Page queryByPage(Page page, InStorageParam inStorageParam,Integer principalId){
        List<InStorage> inStorageList = inStorageDao.findAllByPage(inStorageParam, page.getStartIndex(),page.getEndIndex(),principalId);
        int rowCount = inStorageDao.countByPage(inStorageParam,principalId);
        page.setResult(inStorageList, rowCount);
        return page;
    }
    /*
     *根据purchaseNo采购单编号查询采购单类
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.Purchase
     * @auther: huangx
     * @date: 2019/11/24 15:18
     */
    public Purchase queryPurchaseById(String purchaseNo){
        return inStorageDao.findPurchaseById(purchaseNo);
    }
    /*
     *根据purchaseNo采购单编号查询入库单类
     * @param: [purchaseNo]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/11/24 15:21
     */
    public InStorage queryInStorageById(String purchaseNo){
        return inStorageDao.findInStorageById(purchaseNo);
    }
    /*
     *根据purchaseNo采购单编号查询出库明细表类
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.PurchaseDetail
     * @auther: huangx
     * @date: 2019/11/24 15:21
     */
    public List<PurchaseDetail> queryPurchaseDetailById(String purchaseNo){
        return inStorageDao.findPurchaseDetailById(purchaseNo);
    }
    /*
     *实时查询采购单号
     * @param: [purchaseNo]
     * @return: java.lang.String
     * @auther: huangx
     * @date: 2019/11/25 9:58
     */
    public List<String> queryPurchaseNo(String purchaseNo){
        return inStorageDao.selectPurchaseNo(purchaseNo);
    }
    /*
     *查询出所有通过审核的采购单
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Purchase>
     * @auther: huangx
     * @date: 2019/12/1 15:01
     */
    public List<Purchase> queryAllPurchase2(){
        return inStorageDao.findAllPurchase2();
    }
    /*
     *查询出所有状态可用得仓库
     * @param: []
     * @return: java.util.List<com.ztkj.storage.model.Storage>
     * @auther: huangx
     * @date: 2019/11/25 11:18
     */
    public List<Storage> queryAllStorage(Integer principalId){
        return inStorageDao.findAllStorage(principalId);
    }
    /*
     *添加入库
     * @param: [inStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/25 16:31
     */
    @Transactional
    public void addInStorage(InStorage inStorage){
        inStorageDao.addInstorage(inStorage);
    }
    /*
     *修改入库状态
     * @param: [inStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 13:21
     */
    @Transactional
    public void updateinsoStatus(InStorage inStorage){
        System.out.println("========================"+inStorage.getStatus());
        if(inStorage.getStatus().equals("1")){
            //获取到入库的仓库
            Storage storage = inStorage.getStorage();
            //获取到采购单明细
            List<PurchaseDetail> purchaseDetails = inStorageDao.findPurchaseDetailById(inStorage.getPurchase().getPurchaseNo());
                for (PurchaseDetail p:purchaseDetails) {
                    Inventory inventory = new Inventory();
                    inventory.setStorage(storage);
                    inventory.setGoods(p.getGoods());
                    inventory.setVendor(p.getVendor());
                    //查询是否存在记录count
                    int count = inStorageDao.count(inventory);
                    if(count == 0){
                        Inventory inventory1 = new Inventory();
                        inventory1.setStorage(storage);
                        inventory1.setNum(p.getNum());
                        inventory1.setGoods(p.getGoods());
                        inventory1.setVendor(p.getVendor());
                        inStorageDao.addInventory(inventory1);
                        //改状态
                        inStorageDao.updateinsoStatus(inStorage);
                    }else{
                        Inventory inventory1 = new Inventory();
                        inventory1.setStorage(storage);
                        inventory1.setNum(p.getNum());
                        inventory1.setGoods(p.getGoods());
                        inventory1.setVendor(p.getVendor());
                        inStorageDao.updateaddInventoryNum(inventory1);
                        //改状态
                        inStorageDao.updateinsoStatus(inStorage);
                    }
            }
        }
        if(inStorage.getStatus().equals("2")){
            System.out.println("==================================");
            //获取到入库的仓库
            Storage storage1 = inStorage.getStorage();
            //获取到采购单明细
            List<PurchaseDetail> purchaseDetails1 = inStorageDao.findPurchaseDetailById(inStorage.getPurchase().getPurchaseNo());
            for (PurchaseDetail p:purchaseDetails1) {
                Inventory inventory1 = new Inventory();
                inventory1.setStorage(storage1);
                inventory1.setNum(p.getNum());
                inventory1.setGoods(p.getGoods());
                inventory1.setVendor(p.getVendor());
                //减库存数量
                inStorageDao.updatesubInventoryNum(inventory1);
                //改状态
                inStorageDao.updateinsoStatus(inStorage);
            }
        }
    }
    /*
     *根据采购单找到入库仓库  再找到该仓库库存
     * @param: [purchaseNo]
     * @return: java.util.List<com.ztkj.storage.model.Inventory>
     * @auther: huangx
     * @date: 2019/11/26 11:02
     */
    public List<Inventory> queryInventoryById(String purchaseNo){
        return inStorageDao.findInventoryById(purchaseNo);
    }
    /*
     *添加一条新纪录到库存中
     * @param: [inStorage]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 9:29
     */
    @Transactional
    public void addInventory(Inventory inventory){
        inStorageDao.addInventory(inventory);
    }
    /*
     *库存表存在记录，改数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 13:56
     */
    @Transactional
    public void updateaddInventoryNum(Inventory inventory){
        inStorageDao.updateaddInventoryNum(inventory);
    }
    /*
     *取消入库，减少库存数量
     * @param: [inventory]
     * @return: void
     * @auther: huangx
     * @date: 2019/11/26 15:47
     */
    @Transactional
    public void updatesubInventoryNum(Inventory inventory){
        inStorageDao.updatesubInventoryNum(inventory);
    }
    /*
     *查询库存中是否有记录
     * @param: [inventory]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/26 18:46
     */
    public int count(Inventory inventory){
        return inStorageDao.count(inventory);
    }
    /*
     *根据仓库id和采购单号查询入库信息
     * @param: [purchaseNo, storageId]
     * @return: com.ztkj.storage.model.InStorage
     * @auther: huangx
     * @date: 2019/12/1 17:36
     */
    public InStorage queryInstorageBypurchaseNoAndStorageId(String purchaseNo,int storageId){
        return inStorageDao.findInStorageBypurchaseNoAndStorageId(purchaseNo, storageId);
    }
}

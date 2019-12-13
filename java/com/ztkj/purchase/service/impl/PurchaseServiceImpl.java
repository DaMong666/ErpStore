package com.ztkj.purchase.service.impl;

import com.ztkj.purchase.dao.PurchaseDao;
import com.ztkj.purchase.dao.PurchaseDetailDao;
import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.purchase.param.PurchaseParam;
import com.ztkj.purchase.service.PurchaseService;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: nieyf
 * @Date: 2019/11/24 15:21
 */
@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
    @Resource
    private PurchaseDao purchaseDao;
    @Resource
    private PurchaseDetailDao purchaseDetailDao;
    @Override
    public Page queryByPage(Page page, PurchaseParam purchaseParam,User user) {

        List<Purchase> purchaseList=purchaseDao.findByPage(purchaseParam,page.getStartIndex(),page.getEndIndex(),user);
        int rowCount=purchaseDao.countByPage(purchaseParam,user);
        page.setResult(purchaseList,rowCount);
        return page;
    }
    /**
     *查询所有
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Purchase>
     * @auther: nyf
     * @date: 2019/11/27 11:06
     */
    @Override
    public List<Purchase> queryAll() {
        return purchaseDao.findAll();
    }
    /**
     *自动生成采购单编号
     * @param: []
     * @return: java.lang.String
     * @auther: nyf
     * @date: 2019/11/27 11:06
     */
    @Override
    public String queryPurchaseNo() {
        return purchaseDao.getPurchaseNo();
    }
    /**
     *添加采购单，添加明细
     * @param: [purchase]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/27 9:58
     */
    @Override
    @Transactional
    public void add(Purchase purchase,List<PurchaseDetail> purchaseDetailList) {
        purchaseDao.add(purchase);
        for (PurchaseDetail pd:purchaseDetailList){
            pd.setPurchase(purchase);
            purchaseDetailDao.add(pd);
        }
    }

    /**
     *通过采购单编号获取采购单
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.Purchase
     * @auther: nyf
     * @date: 2019/11/27 9:58
     */
    @Override
    public Purchase queryByPurchaseNo(String purchaseNo) {
        return purchaseDao.findByPurchaseNo(purchaseNo);
    }
    /**
     *修改采购单，先根据采购单号删除明细单在添加明细单
     * @param: [purchase, purchaseDetailList]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/28 19:32
     */
    @Override
    public void update(Purchase purchase,List<PurchaseDetail> purchaseDetailList) {
        purchaseDetailDao.deleteDetail(purchase.getPurchaseNo());
        purchaseDao.update(purchase);
        for (PurchaseDetail pd:purchaseDetailList){
            pd.setPurchase(purchase);
            purchaseDetailDao.add(pd);
        }
    }

    @Override
    public void delete(String purchaseNo) {
        purchaseDetailDao.deleteDetail(purchaseNo);
        purchaseDao.delete(purchaseNo);
    }

    /**
     *通过采购单编号获取明细单
     * @param: [purchaseNo]
     * @return: java.util.List<com.ztkj.purchase.model.PurchaseDetail>
     * @auther: nyf
     * @date: 2019/11/27 10:10
     */
    @Override
    public List<PurchaseDetail> queryPurchaseDetailByPurchaseNo(String purchaseNo) {
        return purchaseDetailDao.getPurchaseDetailByPurchaseNo(purchaseNo);
    }
    /**
     *通过商品类型id获取全部可用商品型号
     * @param: [goodsTypeId]
     * @return: java.util.List<com.ztkj.purchase.model.Goods>
     * @auther: nyf
     * @date: 2019/11/27 10:10
     */
    @Override
    public List<Goods> queryAllGoodsByGoodsTypeId(int goodsTypeId) {
        return purchaseDetailDao.findAllGoodsByGoodsTypeId(goodsTypeId);
    }

    @Override
    public List<User> queryAllMgr(User user) {
        return purchaseDao.findAllMgr(user);
    }

    @Override
    public List<User> queryNameByMgrNo(String No) {
        return purchaseDao.findNameByMgrNo(No);
    }

    @Override
    public int queryCount(String purchaseNo) {
        return purchaseDetailDao.countDetail(purchaseNo);
    }

    @Override
    public void updateExamine(Purchase purchase) {
        purchaseDao.examine(purchase);
    }

}

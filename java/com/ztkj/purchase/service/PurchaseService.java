package com.ztkj.purchase.service;

import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.purchase.param.PurchaseParam;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:采购单
 * @Auther: nieyf
 * @Date: 2019/11/24 15:17
 */
public interface PurchaseService {
    public Page queryByPage(Page page, PurchaseParam purchaseParam,User user);
    public List<Purchase> queryAll();
    /**
     *生成14位采购单
     * @param: []
     * @return: java.lang.String
     * @auther: nyf
     * @date: 2019/11/25 13:49
     */
    public String queryPurchaseNo();
    /**
     *添加采购单和采购明细单
     * @param: [purchase, purchaseDetailList]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/27 15:13
     */
    public void add(Purchase purchase,List<PurchaseDetail> purchaseDetailList);

    /**
     *通过采购单编号获取采购单
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.Purchase
     * @auther: nyf
     * @date: 2019/11/27 19:05
     */
    public Purchase queryByPurchaseNo(String purchaseNo);

    /**
     *修改采购单，先根据采购单号删除明细单在添加明细单
     * @param: [purchase, purchaseDetailList]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/28 19:30
     */
    public void update(Purchase purchase,List<PurchaseDetail> purchaseDetailList);
    /**
     *删除未审核的采购单和它的明细单
     * @param: [purchaseNo]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/24 13:46
     */
    public void delete(String purchaseNo);

    /**
     *通过采购编号获取明细单
     * @param: [purchaseNo]
     * @return: java.util.List<com.ztkj.purchase.model.PurchaseDetail>
     * @auther: nyf
     * @date: 2019/11/27 10:00
     */
    public List<PurchaseDetail> queryPurchaseDetailByPurchaseNo(String purchaseNo);
    /**
     *根据商品类型id查找商品
     * @param: [goodsTypeId]
     * @return: java.util.List<com.ztkj.purchase.model.Goods>
     * @auther: nyf
     * @date: 2019/11/25 16:42
     */
    public List<Goods> queryAllGoodsByGoodsTypeId(int goodsTypeId);
    /**
     *数据权限，根据登陆用户判断上级
     * @param: [user]
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: nyf
     * @date: 2019/11/29 9:24
     */
    public List<User> queryAllMgr(User user);
    public List<User> queryNameByMgrNo(String No);

    public int queryCount(String purchaseNo);
    /**
     *提交审核
     * @param: [purchase]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/29 9:23
     */
    public void updateExamine(Purchase purchase);

}

package com.ztkj.purchase.dao;

import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.PurchaseDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:采购单明细表
 * @Auther: nieyf
 * @Date: 2019/11/25 15:20
 */
public interface PurchaseDetailDao {
    /**
     *添加采购明细单
     * @param: [purchaseDetail]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/27 10:09
     */
    public void add(@Param("purchaseDetail") PurchaseDetail purchaseDetail);
    /**
     *通过采购单编号获取明细单
     * @param: [purchaseNo]
     * @return: java.util.List<com.ztkj.purchase.model.PurchaseDetail>
     * @auther: nyf
     * @date: 2019/11/27 10:09
     */
    public List<PurchaseDetail> getPurchaseDetailByPurchaseNo(@Param("purchaseNo") String purchaseNo);
    public int countDetail(@Param("purchaseNo") String purchaseNo);
    /**
     *根据商品类型id查找商品
     * @param: [brandId]
     * @return: java.util.List<com.ztkj.purchase.model.Goods>
     * @auther: nyf
     * @date: 2019/11/25 16:39
     */
    public List<Goods> findAllGoodsByGoodsTypeId(@Param("goodsTypeId") int goodsTypeId);
    /**
     *根据采购单编号删除采购明细
     * @param: [purchaseNo]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/27 12:43
     */
    public void deleteDetail(@Param("purchaseNo") String purchaseNo);
}

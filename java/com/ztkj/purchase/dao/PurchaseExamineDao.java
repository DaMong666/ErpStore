package com.ztkj.purchase.dao;

import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.param.PurchaseParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:采购审核
 * @Auther: nieyf
 * @Date: 2019/11/26 15:47
 */
public interface PurchaseExamineDao {
    public List<Purchase> findByPage(@Param("purchaseParam") PurchaseParam PurchaseParam,
                                     @Param("startIndex") int startIndex,
                                     @Param("endIndex") int endIndex,@Param("checkId") int checkId);
    public int countByPage(@Param("purchaseParam") PurchaseParam PurchaseParam,@Param("checkId") int checkId);
    /**
     *根据采购单编号获取采购单
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.Purchase
     * @auther: nyf
     * @date: 2019/11/25 16:38
     */
    public Purchase findByPurchaseNo(@Param("purchaseNo") String purchaseNo);
    /**
     *审核采购单
     * @param: [purchase]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/26 16:06
     */
    public void update(@Param("purchase") Purchase purchase);

}

package com.ztkj.purchase.service;

import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.param.PurchaseParam;
import com.ztkj.utils.Page;

/**
 * @Description:
 * @Auther: nieyf
 * @Date: 2019/11/26 16:04
 */
public interface PurchaseExamineService {
    public Page queryByPage(Page page, PurchaseParam purchaseParam,int checkId);
    /**
     *根据采购单编号获取采购单
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.Purchase
     * @auther: nyf
     * @date: 2019/11/26 16:07
     */
    public Purchase queryByPurchaseNo(String purchaseNo);
    /**
     *审核采购单
     * @param: [purchase]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/26 16:07
     */
    public void update(Purchase purchase);

}

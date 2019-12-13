package com.ztkj.purchase.vo;

import com.ztkj.purchase.model.PurchaseDetail;

import java.util.List;

/**
 * @Description:采购单明细的list模型类
 * @Auther: nieyf
 * @Date: 2019/11/26 19:54
 */
public class DetailModel {

    public List<PurchaseDetail> purchaseDetailList;

    public List<PurchaseDetail> getPurchaseDetailList() {
        return purchaseDetailList;
    }

    public void setPurchaseDetailList(List<PurchaseDetail> purchaseDetailList) {
        this.purchaseDetailList = purchaseDetailList;
    }
}

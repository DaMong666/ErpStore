package com.ztkj.storage.param;

/**
 * @Description:入库表参数类
 * @Auther: huangx
 * @Date: 2019/11/24 10:11
 */
public class InStorageParam {
    private String storageName;
    private String purchaseNo;
    private String status;

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

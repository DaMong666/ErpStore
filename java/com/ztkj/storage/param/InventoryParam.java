package com.ztkj.storage.param;

/**
 * @Description:库存参数类
 * @Auther: huangx
 * @Date: 2019/11/22 16:57
 */
public class InventoryParam {
    private String storageName;
    private String brandName;
    private String goodType;
    private String goodName;

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
}

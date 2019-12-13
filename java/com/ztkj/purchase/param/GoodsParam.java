package com.ztkj.purchase.param;

/**
 * @Description:商品的参数类
 * @Author:nieyf
 * @Date:2019/11/22 20:32
 */
public class GoodsParam {
    private int brandId;//品牌
    private String goodsTypeId;//商品类型
    private String model;   //型号名称
    private String status;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

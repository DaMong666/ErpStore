package com.ztkj.purchase.param;

/**
 * @Description:商品类型的参数类
 * @Author:nieyf
 * @Date:2019/11/20 20:57
 */
public class GoodsTypeParam {
    private int brandId;
    private String name;
    private String status;

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

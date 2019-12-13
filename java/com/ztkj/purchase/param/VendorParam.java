package com.ztkj.purchase.param;

/**
 * @Description:厂商的参数类
 * @Author:nieyf
 * @Date:2019/11/21 17:55
 */
public class VendorParam {
    private String firmName;//公司名
    private String personName;//负责人名
    private String provinceId;
    private String cityId;
    private String status;

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

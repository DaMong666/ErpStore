package com.ztkj.purchase.service;

import com.ztkj.purchase.model.Vendor;
import com.ztkj.purchase.param.VendorParam;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Province;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:厂商的service层
 * @Author:nieyf
 * @Date:2019/11/21 18:07
 */
public interface VendorService {
    public Page queryByPage(Page page, VendorParam vendorParam);
    public List<Vendor> queryAll();
    public Vendor queryById(int id);
    /**
     *查询所有的省份
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @author: nyf
     * @date: 2019/11/21 21:43
     */
    public List<Province> queryAllProvince();
    /**
     *根据省份id查询所有的城市
     * @param: [provinceId]
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @author: nyf
     * @date: 2019/11/21 21:43
     */
    public List<City> queryAllCityByProId(int provinceId);
    /**
     *添加厂商
     * @param: [vendor]
     * @return: void
     * @author: nyf
     * @date: 2019/11/21 21:42
     */
    public void add(Vendor vendor);
    public void update(Vendor vendor);
    public void cancelVendor(int id);
    public void recoverVendor(int id);
}

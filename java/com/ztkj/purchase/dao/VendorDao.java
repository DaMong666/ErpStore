package com.ztkj.purchase.dao;

import com.ztkj.purchase.model.Vendor;
import com.ztkj.purchase.param.VendorParam;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Province;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:厂商的数据层接口
 * @Author:nieyf
 * @Date:2019/11/21 17:52
 */
public interface VendorDao {
    /**
     *分页查询
     * @param: [vendorParam, startIndex, endIndex]
     * @return: java.util.List<com.ztkj.purchase.model.Vendor>
     * @author: nyf
     * @date: 2019/11/21 18:01
     */
    public List<Vendor> findByPage(@Param("vendorParam") VendorParam vendorParam,
                                   @Param("startIndex") int startIndex,
                                   @Param("endIndex") int endIndex);
    public int countByPage(@Param("vendorParam") VendorParam vendorParam);

    /**
     *查询所有
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Vendor>
     * @author: nyf
     * @date: 2019/11/21 18:03
     */
    public List<Vendor> findAll();
    /**
     *查询所有的省份
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Province>
     * @author: nyf
     * @date: 2019/11/21 21:38
     */
    public List<Province> findAllProvince();
    /**
     *根据省份id查询城市
     * @param: [provinceId]
     * @return: java.util.List<com.ztkj.sys.model.City>
     * @author: nyf
     * @date: 2019/11/21 21:41
     */
    public List<City> findAllCityByProId(@Param("provinceId") int provinceId);
    /**
     *添加
     * @param: [vendor]
     * @return: void
     * @author: nyf
     * @date: 2019/11/21 18:04
     */
    public void add(@Param("vendor") Vendor vendor);
    /**
     *根据id查厂商
     * @param: [id]
     * @return: com.ztkj.purchase.model.Vendor
     * @author: nyf
     * @date: 2019/11/21 18:04
     */
    public Vendor findById(@Param("id") int id);
    /**
     *修改
     * @param: [vendor]
     * @return: void
     * @author: nyf
     * @date: 2019/11/21 18:04
     */
    public void update(@Param("vendor") Vendor vendor);
    /**
     *注销
     * @param: [vendor]
     * @return: void
     * @author: nyf
     * @date: 2019/11/21 18:04
     */
    public void cancel(@Param("id") int id);
    /**
     *恢复
     * @param: [vendor]
     * @return: void
     * @author: nyf
     * @date: 2019/11/21 18:04
     */
    public void recover(@Param("id") int id);
}

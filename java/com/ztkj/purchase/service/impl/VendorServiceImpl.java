package com.ztkj.purchase.service.impl;

import com.ztkj.purchase.dao.VendorDao;
import com.ztkj.purchase.model.Vendor;
import com.ztkj.purchase.param.VendorParam;
import com.ztkj.purchase.service.VendorService;
import com.ztkj.sys.model.City;
import com.ztkj.sys.model.Province;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:厂商的service层的实现类
 * @Author:nieyf
 * @Date:2019/11/21 18:09
 */
@Service("vendorService")
public class VendorServiceImpl implements VendorService {
    @Resource
    private VendorDao vendorDao;

    @Override
    public Page queryByPage(Page page, VendorParam vendorParam) {
        List<Vendor> vendorList=vendorDao.findByPage(vendorParam,page.getStartIndex(),page.getEndIndex());
        int rowCount=vendorDao.countByPage(vendorParam);
        page.setResult(vendorList,rowCount);
        return page;
    }

    @Override
    public List<Vendor> queryAll() {
        return vendorDao.findAll();
    }

    @Override
    public Vendor queryById(int id) {
        return vendorDao.findById(id);
    }

    @Override
    public List<Province> queryAllProvince() {
        return vendorDao.findAllProvince();
    }

    @Override
    public List<City> queryAllCityByProId(int provinceId) {
        return vendorDao.findAllCityByProId(provinceId);
    }

    @Override
    @Transactional
    public void add(Vendor vendor) {
        vendorDao.add(vendor);
    }

    @Override
    @Transactional
    public void update(Vendor vendor) {
        vendorDao.update(vendor);
    }

    @Override
    @Transactional
    public void cancelVendor(int id) {
        vendorDao.cancel(id);
    }

    @Transactional
    public void recoverVendor(int id) {
        vendorDao.recover(id);
    }
}

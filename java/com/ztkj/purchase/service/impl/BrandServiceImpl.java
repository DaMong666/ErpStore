package com.ztkj.purchase.service.impl;

import com.ztkj.purchase.dao.BrandDao;
import com.ztkj.purchase.model.Brand;
import com.ztkj.purchase.param.BrandParam;
import com.ztkj.purchase.service.BrandService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:品牌的实现类
 * @Author:nieyf
 * @Date:2019/11/20 19:35
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandDao brandDao;

    /**
     *分页查询
     * @param: [page, brandParam]
     * @return: com.ztkj.utils.Page
     * @author: nyf
     * @date: 2019/11/20 19:53
     */
    @Override
    public Page queryByPage(Page page, BrandParam brandParam) {
        List<Brand> brandList=brandDao.findByPage(brandParam,page.getStartIndex(),page.getEndIndex());
        int rowCount=brandDao.countByPage(brandParam);
        page.setResult(brandList,rowCount);
        return page;
    }

    /**
     *查询所有
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Brand>
     * @author: nyf
     * @date: 2019/11/20 19:53
     */
    @Override
    public List<Brand> queryAll() {
        return brandDao.findAll();
    }
    /**
     *根据id查品牌
     * @param: [id]
     * @return: com.ztkj.purchase.model.Brand
     * @author: nyf
     * @date: 2019/11/20 20:41
     */
    @Override
    public Brand queryById(int id) {
        return brandDao.findById(id);
    }
    /**
     *添加品牌
     * @param: [brand]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 20:41
     */
    @Override
    @Transactional
    public void add(Brand brand) {
        brandDao.add(brand);
    }
    /**
     *修改品牌
     * @param: [brand]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 20:41
     */
    @Override
    @Transactional
    public void update(Brand brand) {
        brandDao.update(brand);
    }
    /**
     *注销品牌
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/22 13:21
     */
    @Override
    @Transactional
    public void cancelBrand(int id) {
        brandDao.cancelBrand(id);
    }
/**
 *恢复
 * @param: [id]
 * @return: void
 * @author: nyf
 * @date: 2019/11/21 16:58
 */
    @Override
    @Transactional
    public void recoverBrand(int id) {
        brandDao.recoverBrand(id);
    }
}

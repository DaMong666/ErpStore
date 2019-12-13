package com.ztkj.purchase.service;

import com.ztkj.purchase.model.Brand;
import com.ztkj.purchase.param.BrandParam;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:品牌
 * @Author:nieyf
 * @Date:2019/11/20 19:34
 */
public interface BrandService {
    /**
     *分页查询
     * @param: [page, brandParam]
     * @return: com.ztkj.utils.Page
     * @author: nyf
     * @date: 2019/11/20 19:51
     */
    public Page queryByPage(Page page, BrandParam brandParam);

    /**
     *查询所有
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Brand>
     * @author: nyf
     * @date: 2019/11/20 19:52
     */
    public List<Brand> queryAll();
    /**
     *根据id查品牌
     * @param: [id]
     * @return: com.ztkj.purchase.model.Brand
     * @author: nyf
     * @date: 2019/11/20 20:39
     */
    public Brand queryById(int id);
    /**
     *添加
     * @param: [brand]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 20:39
     */
    public void add(Brand brand);
    /**
     *修改
     * @param: [brand]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 20:40
     */
    public void update(Brand brand);
    /**
     *注销
     * @param: [brand]
     * @return: void
     * @author: nyf
     * @date: 2019/11/21 17:05
     */
    public void cancelBrand(int id);
    /**
     *恢复
     * @param: [brand]
     * @return: void
     * @author: nyf
     * @date: 2019/11/21 16:57
     */
    public void recoverBrand(int id);
}

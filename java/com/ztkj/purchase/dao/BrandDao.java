package com.ztkj.purchase.dao;

import com.ztkj.purchase.model.Brand;
import com.ztkj.purchase.param.BrandParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:品牌的数据层接口
 * @Author:nieyf
 * @Date:2019/11/20 17:55
 */
public interface BrandDao {
    /**
     *分页查询
     * @param: [brandParam, startIndex, endIndex]
     * @return: java.util.List<com.ztkj.purchase.model.Brand>
     * @author: nyf
     * @date: 2019/11/20 19:53
     */
    public List<Brand> findByPage(@Param("brandParam") BrandParam brandParam,
                                  @Param("startIndex") int startIndex,
                                  @Param("endIndex") int endIndex);

    /**
     *分页统计
     * @param: [brandParam]
     * @return: int
     * @author: nyf
     * @date: 2019/11/20 19:52
     */
    public int countByPage(@Param("brandParam") BrandParam brandParam);

    /**
     *查询所有
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Brand>
     * @author: nyf
     * @date: 2019/11/20 19:52
     */
    public List<Brand> findAll();
    /**
     *添加品牌
     * @param: [brand]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 20:34
     */
    public void add(@Param("brand") Brand brand);
    /**
     *根据id查品牌
     * @param: [id]
     * @return: com.ztkj.purchase.model.Brand
     * @author: nyf
     * @date: 2019/11/20 20:37
     */
    public Brand findById(@Param("id") int id);
    /**
     *修改品牌
     * @param: [brand]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 20:37
     */
    public void update(@Param("brand") Brand brand);
    /**
     *注销品牌
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 20:37
     */
    public void cancelBrand(@Param("id") int id);
    /**
     *恢复
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/22 13:21
     */
    public void recoverBrand(@Param("id") int id);
}

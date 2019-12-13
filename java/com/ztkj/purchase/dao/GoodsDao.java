package com.ztkj.purchase.dao;

import com.ztkj.purchase.model.Brand;
import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.GoodsType;
import com.ztkj.purchase.param.GoodsParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:商品的数据接口层
 * @Author:nieyf
 * @Date:2019/11/22 20:30
 */
public interface GoodsDao {
    /**
     *分页查询
     * @param: [goodsParam, startIndex, endIndex]
     * @return: java.util.List<com.ztkj.purchase.model.Goods>
     * @author: nyf
     * @date: 2019/11/22 20:38
     */
    public List<Goods> findByPage(@Param("goodsParam") GoodsParam goodsParam,
                                  @Param("startIndex") int startIndex,
                                  @Param("endIndex") int endIndex);
    /**
     *分页统计
     * @param: [goodsParam]
     * @return: int
     * @author: nyf
     * @date: 2019/11/20 20:59
     */
    public int countByPage(@Param("goodsParam") GoodsParam goodsParam);
    /**
     *查询所有
     * @param: []
     * @return: java.util.List<Goods>
     * @author: nyf
     * @date: 2019/11/20 21:00
     */
    public List<Goods> findAll();
    /**
     *查询所有的品牌
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Brand>
     * @auther: nyf
     * @date: 2019/11/24 10:52
     */
    public List<Brand> findAllBrand();
    /**
     *根据品牌id查询商品类型
     * @param: [brandId]
     * @return: java.util.List<com.ztkj.purchase.model.GoodsType>
     * @auther: nyf
     * @date: 2019/11/24 10:53
     */
    public List<GoodsType> findAllGoodsTypeByBrandId(@Param("brandId") int brandId);
    /**
     *添加商品
     * @param: [goods]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:00
     */
    public void add(@Param("goods") Goods goods);
    /**
     *根据id查商品
     * @param: [id]
     * @return: com.ztkj.purchase.model.Goods
     * @author: nyf
     * @date: 2019/11/20 21:01
     */
    public Goods findById(@Param("id") int id);
    /**
     *修改商品
     * @param: [goods]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:02
     */
    public void update(@Param("goods") Goods goods);
    /**
     *注销商品
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:02
     */
    public void cancel(@Param("id") int id);
    /**
     *
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/22 15:15
     */
    public void recover(@Param("id") int id);
}

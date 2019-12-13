package com.ztkj.purchase.dao;

import com.ztkj.purchase.model.GoodsType;
import com.ztkj.purchase.param.GoodsTypeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:商品类型的数据层接口
 * @Author:nieyf
 * @Date:2019/11/20 20:56
 */
public interface GoodsTypeDao {
    /**
     *分页查询
     * @param: [goodsTypeParam, startIndex, endIndex]
     * @return: java.util.List<com.ztkj.purchase.model.GoodsType>
     * @author: nyf
     * @date: 2019/11/20 20:58
     */
    public List<GoodsType> findByPage(@Param("goodsTypeParam") GoodsTypeParam goodsTypeParam,
                                      @Param("startIndex") int startIndex,
                                      @Param("endIndex") int endIndex);
    /**
     *分页统计
     * @param: [goodsTypeParam]
     * @return: int
     * @author: nyf
     * @date: 2019/11/20 20:59
     */
    public int countByPage(@Param("goodsTypeParam") GoodsTypeParam goodsTypeParam);
   /**
    *查询所有
    * @param: []
    * @return: java.util.List<Brand>
    * @author: nyf
    * @date: 2019/11/20 21:00
    */
    public List<GoodsType> findAll();
    /**
     *添加商品类型
     * @param: [goodsType]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:00
     */
    public void add(@Param("goodsType") GoodsType goodsType);
    /**
     *根据id查商品类型
     * @param: [id]
     * @return: com.ztkj.purchase.model.GoodsType
     * @author: nyf
     * @date: 2019/11/20 21:01
     */
    public GoodsType findById(@Param("id") int id);
    /**
     *修改商品类型
     * @param: [goodsType]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:02
     */
    public void update(@Param("goodsType") GoodsType goodsType);
    /**
     *注销商品类型
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:02
     */
    public void cancel(@Param("id") int id);
    /**
     *恢复商品类型
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/22 15:15
     */
    public void recover(@Param("id") int id);
}

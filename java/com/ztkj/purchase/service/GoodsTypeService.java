package com.ztkj.purchase.service;

import com.ztkj.purchase.model.GoodsType;
import com.ztkj.purchase.param.GoodsTypeParam;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:商品类型
 * @Author:nieyf
 * @Date:2019/11/20 21:03
 */
public interface GoodsTypeService {
    /**
     *分页查询
     * @param: [page, goodsTypeParam]
     * @return: com.ztkj.utils.Page
     * @author: nyf
     * @date: 2019/11/20 21:05
     */
    public Page queryByPage(Page page, GoodsTypeParam goodsTypeParam);
    /**
     *查询所有
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.GoodsType>
     * @author: nyf
     * @date: 2019/11/20 21:05
     */
    public List<GoodsType> queryAll();
    /**
     *根据id查商品类型
     * @param: [id]
     * @return: com.ztkj.purchase.model.GoodsType
     * @author: nyf
     * @date: 2019/11/20 21:05
     */
    public GoodsType queryById(int id);
    /**
     *添加商品类型
     * @param: [goodsType]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:05
     */
    public void add(GoodsType goodsType);
    /**
     *修改商品类型
     * @param: [goodsType]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:06
     */
    public void update(GoodsType goodsType);
    /**
     *注销商品类型
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:06
     */
    public void cancelGoodsType(int id);
    /**
     *恢复商品类型
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/22 15:17
     */
    public void recoverGoodsType(int id);
}

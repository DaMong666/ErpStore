package com.ztkj.purchase.service;

import com.ztkj.purchase.model.Brand;
import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.GoodsType;
import com.ztkj.purchase.param.GoodsParam;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:商品的service层
 * @Author:nieyf
 * @Date:2019/11/22 20:40
 */
public interface GoodsService {
    /**
     *分页查询
     * @param: [page, goodsParam]
     * @return: com.ztkj.utils.Page
     * @author: nyf
     * @date: 2019/11/22 20:43
     */
    public Page queryByPage(Page page, GoodsParam goodsParam);
    /**
     *查询所有
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Goods>
     * @author: nyf
     * @date: 2019/11/20 21:05
     */
    public List<Goods> queryAll();

    /**
     *查询所有的品牌
     * @param: []
     * @return: java.util.List<com.ztkj.purchase.model.Brand>
     * @auther: nyf
     * @date: 2019/11/24 10:56
     */

    public List<Brand> queryAllBrand();

    /**
     *根据品牌id查询商品类型
     * @param: [brandId]
     * @return: java.util.List<com.ztkj.purchase.model.GoodsType>
     * @auther: nyf
     * @date: 2019/11/24 10:56
     */

    public List<GoodsType> queryAllGoodsTypeByBrandId(int brandId);
    /**
     *根据id查商品
     * @param: [id]
     * @return: com.ztkj.purchase.model.Goods
     * @author: nyf
     * @date: 2019/11/20 21:05
     */
    public Goods queryById(int id);
    /**
     *添加商品
     * @param: [goods]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:05
     */
    public void add(Goods goods);
    /**
     *修改商品
     * @param: [goods]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:06
     */
    public void update(Goods goods);
    /**
     *注销商品
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/20 21:06
     */
    public void cancelGoods(int id);
    /**
     *恢复商品
     * @param: [id]
     * @return: void
     * @author: nyf
     * @date: 2019/11/22 15:17
     */
    public void recoverGoods(int id);
}

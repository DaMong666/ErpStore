package com.ztkj.purchase.service.impl;

import com.ztkj.purchase.dao.GoodsDao;
import com.ztkj.purchase.model.Brand;
import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.GoodsType;
import com.ztkj.purchase.param.GoodsParam;
import com.ztkj.purchase.service.GoodsService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:商品的实现类
 * @Author:nieyf
 * @Date:2019/11/22 20:44
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;
    @Override
    public Page queryByPage(Page page, GoodsParam goodsParam) {
        List<Goods> goodsList=goodsDao.findByPage(goodsParam,page.getStartIndex(),page.getEndIndex());
        int rowCount=goodsDao.countByPage(goodsParam);
        page.setResult(goodsList,rowCount);
        return page;
    }

    @Override
    public List<Goods> queryAll() {
        return goodsDao.findAll();
    }

    @Override
    public List<Brand> queryAllBrand() {
        return goodsDao.findAllBrand();
    }

    @Override
    public List<GoodsType> queryAllGoodsTypeByBrandId(int brandId) {
        return goodsDao.findAllGoodsTypeByBrandId(brandId);
    }

    @Override
    public Goods queryById(int id) {
        return goodsDao.findById(id);
    }

    @Override
    public void add(Goods goods) {
        goodsDao.add(goods);
    }

    @Override
    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public void cancelGoods(int id) {
        goodsDao.cancel(id);
    }

    @Override
    public void recoverGoods(int id) {
        goodsDao.recover(id);
    }
}

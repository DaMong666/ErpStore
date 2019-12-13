package com.ztkj.purchase.service.impl;

import com.ztkj.purchase.dao.GoodsTypeDao;
import com.ztkj.purchase.model.GoodsType;
import com.ztkj.purchase.param.GoodsTypeParam;
import com.ztkj.purchase.service.GoodsTypeService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:商品类型的实现类
 * @Author:nieyf
 * @Date:2019/11/20 21:06
 */
@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Resource
    private GoodsTypeDao goodsTypeDao;
    @Override
    public Page queryByPage(Page page, GoodsTypeParam goodsTypeParam) {
        List<GoodsType> goodsTypeList=goodsTypeDao.findByPage(goodsTypeParam,page.getStartIndex(),page.getEndIndex());
        int rowCount=goodsTypeDao.countByPage(goodsTypeParam);
        page.setResult(goodsTypeList,rowCount);
        return page;
    }

    @Override
    public List<GoodsType> queryAll() {
        return goodsTypeDao.findAll();
    }

    @Override
    public GoodsType queryById(int id) {
        return goodsTypeDao.findById(id);
    }

    @Override
    public void add(GoodsType goodsType) {
        goodsTypeDao.add(goodsType);
    }

    @Override
    public void update(GoodsType goodsType) {
        goodsTypeDao.update(goodsType);
    }

    @Override
    public void cancelGoodsType(int id) {
        goodsTypeDao.cancel(id);
    }

    @Override
    public void recoverGoodsType(int id) {
        goodsTypeDao.recover(id);
    }

}

package com.ztkj.purchase.service.impl;

import com.ztkj.purchase.dao.PurchaseExamineDao;
import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.param.PurchaseParam;
import com.ztkj.purchase.service.PurchaseExamineService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: nieyf
 * @Date: 2019/11/26 16:07
 */
@Service("purchaseExamineService")
public class PurchaseExamineServiceImpl implements PurchaseExamineService {
    @Resource
    private PurchaseExamineDao purchaseExamineDao;
    @Override
    public Page queryByPage(Page page, PurchaseParam purchaseParam,int checkId) {
        List<Purchase> purchaseList=purchaseExamineDao.findByPage(purchaseParam,page.getStartIndex(),page.getEndIndex(),checkId);
        int rowCount=purchaseExamineDao.countByPage(purchaseParam,checkId);
        page.setResult(purchaseList,rowCount);
        return page;
    }
    /**
     *根据采购单编号获取采购单
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.Purchase
     * @auther: nyf
     * @date: 2019/11/27 10:37
     */
    @Override
    public Purchase queryByPurchaseNo(String purchaseNo) {
        return purchaseExamineDao.findByPurchaseNo(purchaseNo);
    }
    /**
     *审核采购单
     * @param: [purchase]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/27 10:34
     */
    @Override
    public void update(Purchase purchase) {
        purchaseExamineDao.update(purchase);
    }
}

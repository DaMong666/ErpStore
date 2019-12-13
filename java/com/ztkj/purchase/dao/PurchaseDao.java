package com.ztkj.purchase.dao;

import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.param.PurchaseParam;
import com.ztkj.sys.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:采购单的数据接口层
 * @Auther: nieyf
 * @Date: 2019/11/24 13:22
 */
public interface PurchaseDao {
    public List<Purchase> findByPage(@Param("purchaseParam") PurchaseParam PurchaseParam,
                                     @Param("startIndex") int startIndex,
                                     @Param("endIndex") int endIndex,@Param("user") User user);
    public int countByPage(@Param("purchaseParam") PurchaseParam PurchaseParam,@Param("user") User user);
    public List<Purchase> findAll();
    /**
     *生成14位采购单
     * @param: []
     * @return: java.lang.String
     * @auther: nyf
     * @date: 2019/11/25 13:47
     */
    public String getPurchaseNo();
    /**
     *
     * @param: [purchase]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/25 16:41
     */
    public void add(@Param("purchase") Purchase purchase);
    /**
     *根据采购单编号获取采购单
     * @param: [purchaseNo]
     * @return: com.ztkj.purchase.model.Purchase
     * @auther: nyf
     * @date: 2019/11/25 16:38
     */
    public Purchase findByPurchaseNo(@Param("purchaseNo") String purchaseNo);
    /**
     *
     * @param: [purchase]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/25 16:41
     */
    public void update(@Param("purchase") Purchase purchase);
    /**
     *删除未审核的采购单
     * @param: [purchaseNo]
     * @return: void
     * @auther: nyf
     * @date: 2019/11/24 13:46
     */
    public void delete(@Param("purchaseNo") String purchaseNo);
    /**
     *查找所有的上级
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: nyf
     * @date: 2019/11/27 17:12
     */
    public List<User> findAllMgr(@Param("user") User user);
    /**
     *通过职位no查找名字
     * @param: [No]
     * @return: java.util.List<com.ztkj.sys.model.User>
     * @auther: nyf
     * @date: 2019/11/28 10:48
     */
    public List<User> findNameByMgrNo(@Param("No") String No);
    public void examine(@Param("purchase") Purchase purchase);
    
}

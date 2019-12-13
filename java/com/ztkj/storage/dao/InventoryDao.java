package com.ztkj.storage.dao;

import com.ztkj.storage.model.Inventory;
import com.ztkj.storage.param.InventoryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:库存管理数据层
 * @Auther: huangx
 * @Date: 2019/11/22 16:53
 */
public interface InventoryDao {

    /*
     *查询所有的库存
     * @param: [inventoryParam, startIndex, endIndex]
     * @return: java.util.List<com.ztkj.storage.model.Inventory>
     * @auther: huangx
     * @date: 2019/11/22 17:00
     */
    public List<Inventory> findAllByPage(@Param("inventoryParam")InventoryParam inventoryParam,
                                         @Param("startIndex") int startIndex,
                                         @Param("endIndex")int endIndex,
                                         @Param("principalId") Integer principalId);
    /*
     *分页统计
     * @param: [inventoryParam]
     * @return: int
     * @auther: huangx
     * @date: 2019/11/22 17:01
     */
    public int countByPage(@Param("inventoryParam")InventoryParam inventoryParam,
                           @Param("principalId") Integer principalId);
}

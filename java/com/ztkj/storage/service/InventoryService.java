package com.ztkj.storage.service;

import com.ztkj.storage.param.InventoryParam;
import com.ztkj.utils.Page;

/**
 * @Description:库存类服务层接口
 * @Auther: huangx
 * @Date: 2019/11/22 17:40
 */
public interface InventoryService {
    /*
     *模糊查询+分页
     * @param: [page, storageName, provinceId, cityId]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/20 19:37
     */
    public Page queryByPage(Page page, InventoryParam inventoryParam,Integer principalId);
}

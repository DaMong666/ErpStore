package com.ztkj.storage.service.impl;

import com.ztkj.storage.dao.InventoryDao;
import com.ztkj.storage.model.Inventory;
import com.ztkj.storage.model.Storage;
import com.ztkj.storage.param.InventoryParam;
import com.ztkj.storage.service.InventoryService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: huangx
 * @Date: 2019/11/22 17:41
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
    @Resource
    private InventoryDao inventoryDao;
    /*
     *模糊查询+分页
     * @param: [page, inventoryParam]
     * @return: com.ztkj.utils.Page
     * @auther: huangx
     * @date: 2019/11/22 17:46
     */
    public Page queryByPage(Page page,InventoryParam inventoryParam,Integer principalId){
        List<Inventory> inventoryList = inventoryDao.findAllByPage(inventoryParam, page.getStartIndex(), page.getEndIndex(),principalId);
        int rowCount = inventoryDao.countByPage(inventoryParam,principalId);
        page.setResult(inventoryList, rowCount);
        return page;
    }
}

package com.ztkj.statis.service.impl;

import com.ztkj.market.model.Customer;
import com.ztkj.market.model.Order;
import com.ztkj.statis.dao.SalesDao;
import com.ztkj.statis.param.SalesParam;
import com.ztkj.statis.service.SalesService;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/26 15:36
 */
@Service("salesService")
public class SalesServiceImpl implements SalesService {

    @Resource
    private SalesDao salesDao;
    /**
     *模糊+分页统计
     * @param: [page, salesParam]
     * @return: com.ztkj.utils.Page
     * @author: liss
     * @date: 2019/11/26 15:35
     */

    public Page queryByPage(Page page, SalesParam salesParam){
        List<Customer> customerList = salesDao.findByPage(page.getStartIndex(),
                page.getEndIndex(),salesParam);
        int row = salesDao.countByPage(salesParam);
        page.setResult(customerList,row);
        return page;
    }

    /**
     *数据统计之查看详情
     * @param: [id, page]
     * @return: com.ztkj.utils.Page
     * @author: liss
     * @date: 2019/11/26 20:23
     */

    public Page queryOrderByCustomer(int id,Page page){
        List<Order> orderList = salesDao.findOrderByCustomer(id,page.getStartIndex(),
                page.getEndIndex());
        int row = salesDao.countOrderByCustomer(id);
        page.setResult(orderList,row);
        return page;
    }

}

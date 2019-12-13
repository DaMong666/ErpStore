package com.ztkj.statis.service;

import com.ztkj.market.model.Order;
import com.ztkj.statis.param.SalesParam;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/26 15:34
 */
public interface SalesService {

    /**
     *模糊+分页统计
     * @param: [page, salesParam]
     * @return: com.ztkj.utils.Page
     * @author: liss
     * @date: 2019/11/26 15:35
     */

    public Page queryByPage(Page page, SalesParam salesParam);

    /**
     *数据统计之查看详情
     * @param: [id, page]
     * @return: com.ztkj.utils.Page
     * @author: liss
     * @date: 2019/11/26 20:23
     */

    public Page queryOrderByCustomer(int id,Page page);

}

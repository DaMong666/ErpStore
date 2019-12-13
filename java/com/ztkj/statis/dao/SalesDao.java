package com.ztkj.statis.dao;

import com.ztkj.market.model.Customer;
import com.ztkj.market.model.Order;
import com.ztkj.statis.param.SalesParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/26 15:20
 */
public interface SalesDao {

    /**
     *分页+模糊查询
     * @param: [startIndex, endIndex, salesParam]
     * @return: java.util.List<com.ztkj.market.model.Customer>
     * @author: liss
     * @date: 2019/11/26 15:29
     */

    public List<Customer> findByPage(@Param("startIndex") int startIndex,
                                     @Param("endIndex") int endIndex,
                                     @Param("salesParam") SalesParam salesParam);

    /**
     *分页+模糊统计
     * @param: [salesParam]
     * @return: int
     * @author: liss
     * @date: 2019/11/26 15:29
     */

    public int countByPage(@Param("salesParam") SalesParam salesParam);

    /**
     *数据统计之查看详情
     * @param: [id, startIndex, endIndex]
     * @return: java.util.List<com.ztkj.market.model.Order>
     * @author: liss
     * @date: 2019/11/26 20:21
     */

    public List<Order> findOrderByCustomer(@Param("id") int id,@Param("startIndex") int startIndex,
                                           @Param("endIndex") int endIndex);

    /**
     *统计
     * @param: [id]
     * @return: int
     * @author: liss
     * @date: 2019/11/26 20:24
     */

    public int countOrderByCustomer(@Param("id") int id);
}

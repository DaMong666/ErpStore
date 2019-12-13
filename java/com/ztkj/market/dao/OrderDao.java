package com.ztkj.market.dao;

import com.ztkj.market.model.Order;
import com.ztkj.market.param.OrderParam;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 订单Dao
 * @Auther: YDM
 * @Date: 2019/11/20 17:55
 */
public interface OrderDao {

    /**
     *模糊查询+分页
     * @param: [startIndex, endIndex, orderParam]
     * @return: java.util.List<com.ztkj.market.model.Order>
     * @author: liss
     * @date: 2019/11/24 11:43
     */

    public List<Order> findByPage(@Param("startIndex") int startIndex,
                                  @Param("endIndex") int endIndex,
                                  @Param("orderParam") OrderParam orderParam,
                                  @Param("user") User user);

    /**
     *模糊+分页统计
     * @param: [orderParam]
     * @return: int
     * @author: liss
     * @date: 2019/11/24 11:43
     */

    public int countByPage(@Param("orderParam") OrderParam orderParam,@Param("user") User user);

    /**
     *查询单个
     * @param: [orderNo]
     * @return: com.ztkj.market.model.Order
     * @author: liss
     * @date: 2019/11/24 21:17
     */

    public Order findByNo(@Param("orderNo") String orderNo);

    /**
     *添加订购单
     * @param: [order]
     * @return: void
     * @author: liss
     * @date: 2019/11/24 21:33
     */

    public void addOrder(@Param("order") Order order);

    /**
     *删除订购单
     * @param: [orderNo]
     * @return: void
     * @author: liss
     * @date: 2019/11/24 21:33
     */

    public void delOrder(@Param("orderNo") String orderNo);

    /**
     *修改订购单
     * @param: [order]
     * @return: void
     * @author: liss
     * @date: 2019/11/24 21:35
     */

    public void updateOrder(@Param("order") Order order);

    /**
     *生成订单编号
     * @param: []
     * @return: java.lang.String
     * @author: liss
     * @date: 2019/11/25 13:36
     */

    public String createOrderNo();

    /**
     *审核订购单
     * @param: [orderNo, userId, status, opinion]
     * @return: void
     * @author: liss
     * @date: 2019/11/26 9:59
     */

    public void examineOrder(@Param("orderNo") String orderNo,
                             @Param("userId") User userId,
                             @Param("status") String status,
                             @Param("opinion") String opinion);

    /*查询审核人员*/
    public List<Position> findPosition();

    public List<User> findCheckUser(@Param("positionno") String positionno);

    public void grantCheck(@Param("orderNo") String orderNo,@Param("user") User user);

}

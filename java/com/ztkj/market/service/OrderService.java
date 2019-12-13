package com.ztkj.market.service;

import com.ztkj.market.model.Order;
import com.ztkj.market.param.OrderParam;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;

import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/24 11:56
 */
public interface OrderService {
    /**
     *分页
     * @param: [page, orderParam]
     * @return: com.ztkj.utils.Page
     * @author: liss
     * @date: 2019/11/24 12:01
     */

    public Page queryByPage(Page page, OrderParam orderParam, User user);

    /**
     *查询单个
     * @param: [orderNo]
     * @return: com.ztkj.market.model.Order
     * @author: liss
     * @date: 2019/11/24 21:37
     */

    public Order queryByNo(String orderNo);

    /**
     *添加订购单
     * @param: [order]
     * @return: void
     * @author: liss
     * @date: 2019/11/24 21:33
     */

    public void addOrder(Order order);

    /**
     *删除订购单
     * @param: [orderNo]
     * @return: void
     * @author: liss
     * @date: 2019/11/24 21:33
     */

    public void delOrder(String orderNo);

    /**
     *修改订购单
     * @param: [order]
     * @return: void
     * @author: liss
     * @date: 2019/11/24 21:35
     */

    public void updateOrder(Order order);

    /**
     *生成订单编号
     * @param: []
     * @return: java.lang.String
     * @author: liss
     * @date: 2019/11/25 13:38
     */

    public String createOrderNo();

    /**
     *订购单审核
     * @param: [orderNo, userId, status, opinion]
     * @return: void
     * @author: liss
     * @date: 2019/11/26 10:34
     */

    public void updateExamine(String orderNo, User userId, String status, String opinion);

    /*订购单提交审核*/
    public List<Position> queryPosition();

    public List<User> queryCheckUser(String positionno);

    public void updateChecking(String orderNo,User user);
}

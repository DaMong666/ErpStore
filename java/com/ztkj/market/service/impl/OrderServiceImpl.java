package com.ztkj.market.service.impl;

import com.ztkj.market.dao.OrderDao;
import com.ztkj.market.model.Order;
import com.ztkj.market.param.OrderParam;
import com.ztkj.market.service.OrderService;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/24 13:42
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    /**
     *分页
     * @param: [page, orderParam]
     * @return: com.ztkj.utils.Page
     * @author: liss
     * @date: 2019/11/24 12:01
     */

    public Page queryByPage(Page page, OrderParam orderParam, User user){
        List<Order> orderList=orderDao.findByPage(
                page.getStartIndex(),page.getEndIndex(),orderParam,user);
        int row=orderDao.countByPage(orderParam,user);
        page.setResult(orderList,row);
        return page;
    }

    @Override
    public Order queryByNo(String orderNo) {
        return orderDao.findByNo(orderNo);
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    @Transactional
    public void delOrder(String orderNo) {
        orderDao.delOrder(orderNo);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }

    /**
     *生成订单编号
     * @param: []
     * @return: java.lang.String
     * @author: liss
     * @date: 2019/11/25 13:38
     */

    public String createOrderNo(){
        return orderDao.createOrderNo();
    }

    /**
     *订购单审核
     * @param: [orderNo, userId, status, opinion]
     * @return: void
     * @author: liss
     * @date: 2019/11/26 10:34
     */
    @Transactional
    public void updateExamine(String orderNo, User userId, String status, String opinion){
        orderDao.examineOrder(orderNo,userId,status,opinion);
    }

    /**
     *订购单提交审核，查询审核人员
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @author: liss
     * @date: 2019/11/27 16:26
     */

    public List<Position> queryPosition(){
        return orderDao.findPosition();
    }

    public List<User> queryCheckUser(String positionno){
        return orderDao.findCheckUser(positionno);
    }
    @Transactional
    public void updateChecking(String orderNo,User user){
        orderDao.grantCheck(orderNo,user);
    }
}

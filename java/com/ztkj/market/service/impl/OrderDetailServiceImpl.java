package com.ztkj.market.service.impl;

import com.ztkj.market.dao.OrderDetailDao;
import com.ztkj.market.model.OrderDetail;
import com.ztkj.market.param.OrderDetailParam;
import com.ztkj.market.service.OrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/25 15:55
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource
    private OrderDetailDao orderDetailDao;
    /**
     *查询订购单所对应的商品明细
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.market.model.OrderDetail>
     * @author: liss
     * @date: 2019/11/25 15:52
     */

    public List<OrderDetail> queryOrderDetail(String orderNo){
        return orderDetailDao.findByOrderNo(orderNo);
    }

    /**
     *删除订购单对应的商品
     * @param: [orderNo]
     * @return: void
     * @author: liss
     * @date: 2019/11/25 19:56
     */
    @Transactional
    public void delByNo(String orderNo){
        orderDetailDao.delByOrderNo(orderNo);
    }

    /**
     *添加订购单明细
     * @param: [orderDetail]
     * @return: void
     * @author: liss
     * @date: 2019/11/27 20:15
     */
    @Transactional
    public void addOrderDetail(String orderNo,OrderDetailParam orderDetailParam){

        for (OrderDetail odp:orderDetailParam.getOrderDetailList()){
            orderDetailDao.addOrderDetail(odp,orderNo);
        }
    }
}

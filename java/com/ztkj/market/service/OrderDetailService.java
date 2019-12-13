package com.ztkj.market.service;

import com.ztkj.market.model.OrderDetail;
import com.ztkj.market.param.OrderDetailParam;

import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/25 15:50
 */
public interface OrderDetailService {

    /**
     *查询订购单所对应的商品明细
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.market.model.OrderDetail>
     * @author: liss
     * @date: 2019/11/25 15:52
     */

    public List<OrderDetail> queryOrderDetail(String orderNo);

    /**
     *删除订购单对应的商品
     * @param: [orderNo]
     * @return: void
     * @author: liss
     * @date: 2019/11/25 19:56
     */

    public void delByNo(String orderNo);

    /**
     *添加订购单明细
     * @param: [orderDetail]
     * @return: void
     * @author: liss
     * @date: 2019/11/27 20:15
     */

    public void addOrderDetail(String orderNo,OrderDetailParam orderDetailParam);
}

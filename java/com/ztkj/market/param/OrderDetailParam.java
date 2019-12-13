package com.ztkj.market.param;

import com.ztkj.market.model.OrderDetail;
import com.ztkj.market.model.Order;

import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/27 20:25
 */
public class OrderDetailParam {
    private Order order;
    private List<OrderDetail> orderDetailList;

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

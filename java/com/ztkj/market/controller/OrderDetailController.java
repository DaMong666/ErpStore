package com.ztkj.market.controller;

import com.ztkj.market.model.OrderDetail;
import com.ztkj.market.service.OrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/25 18:42
 */
@RestController
@RequestMapping("market/orderDetail")
public class OrderDetailController {

    @Resource
    private OrderDetailService orderDetailService;

    /**
     *查询订购单编号对应的商品
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.market.model.OrderDetail>
     * @author: liss
     * @date: 2019/11/25 18:46
     */
    @RequestMapping("asyncbyno")
    public List<OrderDetail> queryByOrderNo(String orderNo){
        return orderDetailService.queryOrderDetail(orderNo);
    }
}

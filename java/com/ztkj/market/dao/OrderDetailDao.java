package com.ztkj.market.dao;

import com.ztkj.market.model.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Auther: YDM
 * @Date: 2019/11/20 18:35
 */
public interface OrderDetailDao {

    /**
     *查询订购单所对应的商品明细
     * @param: [orderNo]
     * @return: java.util.List<com.ztkj.market.model.OrderDetail>
     * @author: liss
     * @date: 2019/11/25 15:44
     */

    public List<OrderDetail> findByOrderNo(@Param("orderNo") String orderNo);

    /**
     *删除订购单对应的商品详细
     * @param: [orderNo]
     * @return: void
     * @author: liss
     * @date: 2019/11/25 19:53
     */

    public void delByOrderNo(@Param("orderNo") String orderNo);

    /**
     *添加订购单明细
     * @param: [orderDetail]
     * @return: void
     * @author: liss
     * @date: 2019/11/27 20:14
     */

    public void addOrderDetail(@Param("orderDetail") OrderDetail orderDetail,@Param("orderNo") String orderNo);

}

package com.ztkj.market.controller;

import com.ztkj.market.model.Order;
import com.ztkj.market.param.OrderDetailParam;
import com.ztkj.market.param.OrderParam;
import com.ztkj.market.service.OrderDetailService;
import com.ztkj.market.service.OrderService;
import com.ztkj.sys.model.Position;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Auther: liss
 * @Date: 2019/11/24 13:48
 */
@RestController
@RequestMapping("market/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderDetailService orderDetailService;

    /**
     *分页+模糊查询
     * @param: [pageNo, orderParam]
     * @return: com.ztkj.utils.Page
     * @author: liss
     * @date: 2019/11/24 13:55
     */

    @RequestMapping("asyncpage")
    public Page queryOrderByPage(Integer pageNo, OrderParam orderParam,HttpSession session){
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        Page page=new Page(pageNo,4);
        User user = (User) session.getAttribute("loginUser");
        page=orderService.queryByPage(page,orderParam,user);
        return page;
    }

    /**
     *生成订单号
     * @param: []
     * @return: java.lang.String
     * @author: liss
     * @date: 2019/11/25 14:08
     */

    @RequestMapping("asyncgetno")
    public String queryOrderNo(){
        return orderService.createOrderNo();
    }

    /**
     *添加订购单
     * @param: [order]
     * @return: java.lang.String
     * @author: liss
     * @date: 2019/11/25 14:03
     */

    @RequestMapping("asyncadd")
    public String addOrder(OrderDetailParam orderDetailParam, HttpSession session){
        String info = "";
        try {
            User createUser=(User) session.getAttribute("loginUser");
            orderDetailParam.getOrder().setCreateUser(createUser);
            orderService.addOrder(orderDetailParam.getOrder());
            orderDetailService.addOrderDetail(orderDetailParam.getOrder().getOrderNo(),orderDetailParam);
            info = "恭喜添加成功！！";
        } catch (Exception e) {
            e.printStackTrace();
            info = "添加失败！！";
        }
        return info;
    }

    /**
     *修改订购单信息
     * @param: [orderDetailParam]
     * @return: java.lang.String
     * @author: liss
     * @date: 2019/11/29 16:42
     */
    @RequestMapping("asyncupdate")
    public String updateOrder(OrderDetailParam orderDetailParam){
        String info = "";
        try {
            //修该订购单
            orderService.updateOrder(orderDetailParam.getOrder());
            //删除订购单对应的商品详情
            orderDetailService.delByNo(orderDetailParam.getOrder().getOrderNo());
            //添加修改之后的商品
            orderDetailService.addOrderDetail(orderDetailParam.getOrder().getOrderNo(),orderDetailParam);
            info = "修改成功！！";
        } catch (Exception e) {
            e.printStackTrace();
            info = "修改失败！！";
        }
        return info;
    }

    /**
     *查看订购单详情
     * @param: [orderNo]
     * @return: com.ztkj.market.model.Order
     * @author: liss
     * @date: 2019/11/25 16:59
     */

    @RequestMapping("asynctoview")
    public Order queryByOrderNo(String orderNo){
        return orderService.queryByNo(orderNo);
    }

    /**
     *删除订单以及订单对应的商品详细
     * @param: [orderNo]
     * @return: java.lang.String
     * @author: liss
     * @date: 2019/11/25 20:06
     */
    @RequestMapping("asyncdel")
    public String delByOrderNo(String orderNo){
        String info = "";
        try {
            orderDetailService.delByNo(orderNo);
            orderService.delOrder(orderNo);
            info = "恭喜删除成功！！";
        } catch (Exception e) {
            e.printStackTrace();
            info = "删除失败！！";
        }
        return info;
    }

    /**
     *订购单审核
     * @param: [orderNo, status, opinion, session]
     * @return: java.lang.String
     * @author: liss
     * @date: 2019/11/26 10:56
     */
    @RequestMapping("asyncexamine")
    public String updateExamineOrder(String orderNo, String status, String opinion, HttpSession session){
        String info = "";
        try {
            if("1".equals(status)){
                return info = "审核失败，审核状态未修改";
            }
            User userId = (User)session.getAttribute("loginUser");
            orderService.updateExamine(orderNo,userId,status,opinion);
            info = "审核成功！！";
        } catch (Exception e) {
            e.printStackTrace();
            info = "审核失败！！";
        }
        return info;
    }

    /**
     *查询订购单审核人员
     * @param: []
     * @return: java.util.List<com.ztkj.sys.model.Position>
     * @author: liss
     * @date: 2019/11/27 16:31
     */
    @RequestMapping("asyncposition")
    public List<Position> queryPositionList(){
        return orderService.queryPosition();
    }
    @RequestMapping("asynccheckuser")
    public List<User> queryCheckUserList(String positionno){
        return orderService.queryCheckUser(positionno);
    }
    @RequestMapping("asyncupcheck")
    public String updateChecking(String checkOrderNo,User user){
        String info = "";
        try {
            orderService.updateChecking(checkOrderNo,user);
            info = "提交审核成功！！";
        } catch (Exception e) {
            e.printStackTrace();
            info = "提交审核失败！！";
        }
        return info;
    }



}

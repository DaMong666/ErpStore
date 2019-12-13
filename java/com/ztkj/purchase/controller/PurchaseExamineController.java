package com.ztkj.purchase.controller;

import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.param.PurchaseParam;
import com.ztkj.purchase.service.PurchaseExamineService;
import com.ztkj.sys.model.User;
import com.ztkj.utils.DateUtil;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Description:采购单审核
 * @Auther: nieyf
 * @Date: 2019/11/26 16:10
 */
@Controller
@RequestMapping("purchase/purchaseExamine")
public class PurchaseExamineController {
    @Resource
    private PurchaseExamineService purchaseExamineService;
    @RequestMapping("pepage")
    public @ResponseBody
    Page queryPage(Integer pageNo, PurchaseParam purchaseParam, HttpSession session){
        if (pageNo==null||pageNo<1){
            pageNo=1;
        }
        Page page=new Page(pageNo,5);
        User user = (User) session.getAttribute("loginUser");
        int checkId=user.getId();
        page=purchaseExamineService.queryByPage(page,purchaseParam,checkId);
        return page;
    }
    @RequestMapping("petupdate")
    public @ResponseBody Purchase queryToPurchase(@RequestParam("purchaseNo") String purchaseNo){
        return  purchaseExamineService.queryByPurchaseNo(purchaseNo);
    }
    @RequestMapping("peupdate")
    public @ResponseBody String update(Purchase purchase){
        String info="采购单审核成功！";
        try {
            purchase.setCheckTime(DateUtil.stringToTimestamp(DateUtil.currentDate(DateUtil.TIMESTAMP_LONG)));
            purchase.setPurchaseNo(purchase.getPurchaseNo());
            purchaseExamineService.update(purchase);
        } catch (Exception e) {
            e.printStackTrace();
            info="采购单审核失败！";
        }
        return info;
    }
}

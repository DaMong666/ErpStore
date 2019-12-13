package com.ztkj.purchase.controller;

import com.ztkj.purchase.model.Goods;
import com.ztkj.purchase.model.Purchase;
import com.ztkj.purchase.model.PurchaseDetail;
import com.ztkj.purchase.param.PurchaseParam;
import com.ztkj.purchase.service.PurchaseService;
import com.ztkj.purchase.vo.DetailModel;
import com.ztkj.sys.model.User;
import com.ztkj.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:采购单的控制层
 * @Auther: nieyf
 * @Date: 2019/11/24 15:03
 */
@Controller
@RequestMapping("purchase/purchase")
public class PurchaseController {
    @Resource
    private PurchaseService purchaseService;
    @RequestMapping("ppage")
    public @ResponseBody Page queryPage(Integer pageNo, PurchaseParam purchaseParam,HttpSession session){
        if (pageNo==null||pageNo<1){
            pageNo=1;
        }
        Page page=new Page(pageNo,5);
        User user = (User) session.getAttribute("loginUser");
        page=purchaseService.queryByPage(page,purchaseParam,user);
        return page;
    }
    @RequestMapping("getno")
    public @ResponseBody String queryPurchaseNo(){
        return purchaseService.queryPurchaseNo();
    }
    /**
     *新建采购单
     * @param: [purchase, session]
     * @return: java.lang.String
     * @auther: nyf
     * @date: 2019/11/26 15:03
     */
    @RequestMapping("add")
    public @ResponseBody String add(Purchase purchase, HttpSession session,DetailModel detailModel){
        String info="采购单添加成功！";
        try {
            User user = (User) session.getAttribute("loginUser");
            purchase.setCreateUser(user);
            purchaseService.add(purchase,detailModel.getPurchaseDetailList());
        } catch (Exception e) {
            e.printStackTrace();
            info="采购单添加失败！";
        }
        return info;
    }
    @RequestMapping("update")
    public @ResponseBody String update(Purchase purchase, HttpSession session,DetailModel detailModel){
        String info="采购单修改成功！";
        try {
            User user = (User) session.getAttribute("loginUser");
            purchase.setCreateUser(user);
            purchaseService.update(purchase,detailModel.getPurchaseDetailList());
        } catch (Exception e) {
            e.printStackTrace();
            info="采购单修改失败！";
        }
        return info;
    }
    /**
     *根据商品类型id查询商品型号
     * @param: [goodsTypeId]
     * @return: java.util.List<com.ztkj.purchase.model.Goods>
     * @auther: nyf
     * @date: 2019/11/25 16:46
     */
    @RequestMapping("goods")
    public @ResponseBody
    List<Goods> queryAllGoods(int goodsTypeId){
        return purchaseService.queryAllGoodsByGoodsTypeId(goodsTypeId);
    }

    @RequestMapping("getPurchaseDetail")
    public @ResponseBody List<PurchaseDetail> queryDetailListByNo(String purchaseNo){
        return purchaseService.queryPurchaseDetailByPurchaseNo(purchaseNo);
    }
    @RequestMapping("delete")
    public @ResponseBody String delete(String purchaseNo){
        String info="采购单删除成功！";
        try {
            purchaseService.delete(purchaseNo);
        } catch (Exception e) {
            e.printStackTrace();
            info="采购单删除失败！";
        }
        return info;
    }
    @RequestMapping("mgr")
    public @ResponseBody List<User> queryAllMgr(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        return purchaseService.queryAllMgr(user);
    }
    @RequestMapping("name")
    public @ResponseBody List<User> queryAllName(String No){
        return purchaseService.queryNameByMgrNo(No);
    }
    @RequestMapping("tupdate")
    public @ResponseBody Purchase queryByPurchaseNo(String purchaseNo){
        return purchaseService.queryByPurchaseNo(purchaseNo);
    }
    @RequestMapping("count")
    public @ResponseBody int queryCountDetail(String purchaseNo){
        return purchaseService.queryCount(purchaseNo);
    }
    @RequestMapping("updateExamine")
    public @ResponseBody String updateExamine(Purchase purchase){
        String info="采购单提交审核成功！";
        try {
            purchaseService.updateExamine(purchase);
        } catch (Exception e) {
            e.printStackTrace();
            info="采购单提交审核失败！";
        }
        return info;
    }
}

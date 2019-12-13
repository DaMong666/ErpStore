$(function () {
    toView();
    getPurchaseDetail();
});

/**
 * 到达查看详情页面
 */
function toView() {
    var purchaseNo=getParameter("purchaseNo");
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchaseExamine/petupdate",
        data:"purchaseNo="+purchaseNo,
        success:function (data) {
            $(".purchaseNo").text(data.purchaseNo);
            $(".purchaseTime").text(data.createTime);
            $(".purchaseMoney").text(data.money);
            $(".createUser").text(data.createUser.name);
            if (data.opinion!=null){
                $(".opinion").text(data.opinion);
            }
            if (data.status==0){
                $(".status").text("未审核");
            }
            if (data.status==1){
                $(".status").text("审核中");
            }
            if (data.status==2){
                $(".status").text("已审核通过");
            }
            if (data.checkTime!=null) {
                $(".checkTime").text(data.checkTime);
            }
            if (data.checkUser!=null) {
                $(".checkUser").text(data.checkUser.name);
            }
        },
        dataType:"json"
    });
}

//获取采购明细表信息
function getPurchaseDetail() {
    var purchaseNo = getParameter("purchaseNo");
    $.ajax({
        type:"post",
        url:basePath +"/purchase/purchase/getPurchaseDetail",
        data:"purchaseNo="+purchaseNo,
        /*async:false,//改成同步 在mgr和dept显示后执行*/
        success:function (data) {
            var purchaseDetailList = data;//page对象
            var tbody = "";
            for (var i = 0; i < purchaseDetailList.length; i++) {
                var brandName = purchaseDetailList[i].goods.goodsType.brand.name;
                var typeName = purchaseDetailList[i].goods.goodsType.name;
                var goodName = purchaseDetailList[i].goods.model;
                var vendorName = purchaseDetailList[i].vendor.name;
                var goodNum = purchaseDetailList[i].num;
                var unitName = purchaseDetailList[i].goods.unit;
                var price = purchaseDetailList[i].price;
                var money = purchaseDetailList[i].money;
                tbody += "<tr>" +
                    "<td>"+ (i + 1)+"</td>" +
                    "<td>" + brandName + "</td>" +
                    "<td>" + typeName + "</td>" +
                    "<td>" + goodName + "</td>" +
                    "<td>" + vendorName + "</td>" +
                    "<td>" + goodNum + "</td>" +
                    "<td>" + unitName + "</td>" +
                    "<td>" + price + "</td>" +
                    "<td>" + money + "</td>" +
                    "</tr>"
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
        },
        dataType:"json"
    });
}
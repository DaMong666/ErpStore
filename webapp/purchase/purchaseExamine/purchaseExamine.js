$(function () {
    toExamine();
    getPurchaseDetail();
});

/**
 * 到达审核页面
 */
function toExamine() {
    var purchaseNo=getParameter("purchaseNo");
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchaseExamine/petupdate",
        data:"purchaseNo="+purchaseNo,
        success:function (data) {
            $(".purchaseNo").text(data.purchaseNo);
            $(".purchaseTime").text(data.createTime);
            $("input[name=purchaseNo]").val(data.purchaseNo);
            $(".purchaseMoney").text(data.money);
            $(".createUser").text(data.createUser.name);
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

/**
 * 审核
 */
function update() {
    var  param=$("#form").serializeArray();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchaseExamine/peupdate",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href=basePath+'/purchase/purchaseExamine/purchaseExamineList.html';
        }
    });
}
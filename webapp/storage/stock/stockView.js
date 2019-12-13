$(function () {
    //获取采购表信息
    getPurchase();
    //获取入库表信息
    getInStorage();
    //获取采购明细表信息
    getPurchaseDetail();
});
//获取采购表信息
function getPurchase() {
    var purchaseNo = getParameter("id");
    $.ajax({
        type:"post",
        url:basePath +"/storage/instorage/getPurchase",
        data:"purchaseNo="+purchaseNo,
        /*async:false,//改成同步 在mgr和dept显示后执行*/
        success:function (data) {
            $(".purchaseNo").text(data.purchaseNo);
            $(".purchaseTime").text(data.createTime);
            $(".purchaseMoney").text(data.money);
            $(".createPerson").text(data.createUser.name);
            if(data.status == 2){
                $(".status").text("审核通过");
            }
            $(".opinion").text(data.opinion);
            $(".checkPerson").text(data.checkUser.name);
            $(".checkTime").text(data.checkTime);
        },
        dataType:"json"
    });
}
//获取入库表信息
function getInStorage() {
    var purchaseNo = getParameter("id");
    $.ajax({
        type:"post",
        url:basePath +"/storage/instorage/getInStorage",
        data:"purchaseNo="+purchaseNo,
        /*async:false,//改成同步 在mgr和dept显示后执行*/
        success:function (data) {
            $(".storageName").text(data.storage.name);
            if(data.createTime == null){
                $(".inStoTime").text("");
            }else{
                $(".inStoTime").text(data.createTime);
            }
            $(".instoPerson").text(data.storage.principalPerson.name);
            if(data.status == 1){
                $(".instoStatus").text("未入库");
            }
            if(data.status == 2){
                $(".instoStatus").text("已入库");
            }
        },
        dataType:"json"
    });
}
//获取采购明细表信息
function getPurchaseDetail() {
    var purchaseNo = getParameter("id");
    $.ajax({
        type:"post",
        url:basePath +"/storage/instorage/getPurchaseDetail",
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
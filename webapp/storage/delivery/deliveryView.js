$(function () {
    //获取订购表信息
    getOreder();
    //获取出库表信息
    getOutStorage();
    //获取订购明细表信息
    getOrderDetail();
});
//获取订购表信息
function getOreder() {
    var orderNo = getParameter("id");
    $.ajax({
        type:"post",
        url:basePath +"/storage/outstorage/getOrder",
        data:"orderNo="+orderNo,
        /*async:false,//改成同步 在mgr和dept显示后执行*/
        success:function (data) {
            $(".orderNo").text(data.orderNo);
            $(".customerName").text(data.customer.name);
            $(".tel").text(data.customer.tel);
            $(".orderTime").text(data.createTime);
            $(".orderMoney").text(data.money);
            $(".createPerson").text(data.createUser.name);
            if(data.status == 2){
                $(".status").text("审核通过");
            }
            if(data.opinion == null){
                $(".opinion").text("");
            }else{
                $(".opinion").text(data.opinion);
            }
            $(".checkPerson").text(data.user.name);
            $(".checkTime").text(data.checkTime);
        },
        dataType:"json"
    });
}
//获取入库表信息
function getOutStorage() {
    var orderNo = getParameter("id");
    $.ajax({
        type:"post",
        url:basePath +"/storage/outstorage/getOutStorage",
        data:"orderNo="+orderNo,
        /*async:false,//改成同步 在mgr和dept显示后执行*/
        success:function (data) {
            $(".storageName").text(data.storage.name);
            if(data.createTime == null){
                $(".outStoTime").text("");
            }else{
                $(".outStoTime").text(data.createTime);
            }
            $(".outstoPerson").text(data.storage.principalPerson.name);
            if(data.status == 1){
                $(".outstoStatus").text("未发货");
            }
            if(data.status == 2){
                $(".outstoStatus").text("已发货");
            }
            if(data.status == 3){
                $(".outstoStatus").text("已回款");
            }
            if(data.status == 4){
                $(".outstoStatus").text("取消订单");
            }
            if(data.status == 5){
                $(".outstoStatus").text("已退货");
            }
        },
        dataType:"json"
    });
}
//获取采购明细表信息
function getOrderDetail() {
    var orderNo = getParameter("id");
    $.ajax({
        type:"post",
        url:basePath +"/storage/outstorage/getOrderDetail",
        data:"orderNo="+orderNo,
        /*async:false,//改成同步 在mgr和dept显示后执行*/
        success:function (data) {
            var orderDetailList = data;//page对象
            var tbody = "";
            for (var i = 0; i < orderDetailList.length; i++) {
                var brandName = orderDetailList[i].goods.goodsType.brand.name;
                var typeName = orderDetailList[i].goods.goodsType.name;
                var goodName = orderDetailList[i].goods.model;
                var vendorName = orderDetailList[i].vendor.name;
                var goodNum = orderDetailList[i].num;
                var unitName = orderDetailList[i].goods.unit;
                var price = orderDetailList[i].price;
                var money = orderDetailList[i].money;
                tbody += "<tr>" +
                    "<td>"+ (i + 1)+"</td>" +
                    "<td>" + brandName + "</td>" +
                    "<td>" + typeName + "</td>" +
                    "<td>" + goodName + "</td>" +
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
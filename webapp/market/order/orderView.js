$(function () {
    orderView();

    orderDetailView();
});

//查看订购单详情
function orderView() {
    var orderNo = getParameter("orderNo");
    $.ajax({
        type:"post",
        url:basePath + "/market/order/asynctoview",
        data:"orderNo="+orderNo,
        success:function (data) {
            $("#orderNo").html(data.orderNo);
            $("#cusname").html("<a href=\"../../market/customer/customerView.html?id="+data.customer.id+"\" title=\"点击查看客户详细信息\" class=\"tablelink\">"+data.customer.name+"</a>");
            $("#tel").html(data.customer.tel);
            $("#createTime").html(data.createTime);
            $("#money").html("￥"+data.money);
            $("#createUser").html(data.createUser.name);
            var html1 = "";
            if(data.status == '0'){
                html1 += "未审核";
            }else if (data.status == '1') {
                html1 += "审核中";
            }else if (data.status == '2') {
                html1 += "审核通过";
            }else if (data.status == '3') {
                html1 += "审核不通过";
            }
            $("#status").html(html1);
            $("#opinion").html(data.opinion);
            $("#checkUser").html(data.user.name);
            $("#checkTime").html(data.checkTime);
        },
        dataType:"json"
    });
}

function orderDetailView() {
    var orderNo = getParameter("orderNo");
    $.ajax({
        type:"post",
        url:basePath + "/market/orderDetail/asyncbyno",
        data:"orderNo="+orderNo,
        success:function (data) {
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<tr><td>"+(i+1)+"</td>"
                    +"<td>"+data[i].goods.goodsType.brand.name+"</td>"
                    +"<td>"+data[i].goods.goodsType.name+"</td>"
                    +"<td>"+data[i].goods.model+"</td>"
                    +"<td>"+data[i].vendor.name+"</td>"
                    +"<td>"+data[i].num+"</td>"
                    +"<td>"+data[i].goods.unit+"</td>"
                    +"<td>"+data[i].price+"</td>"
                    +"<td>"+data[i].money+"</td>"
                    +"</tr>";
            }
            $(".tablelist tbody").html(html);
        },
        dataType:"json"
    });
}
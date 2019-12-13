//分页对象
var page;
var loginUserPositionNo = 0;
$(function () {

    $.ajax({
        type: "post",
        url: basePath + "/login/curr",
        async:false,
        success: function (data) {
            loginUserPositionNo = data.positionNo.no;
        },
        dataType: "json"
    });

    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            orderPage(currPage);
        }
    });

    orderPage(1);


});

//订购单管理分页
function orderPage(pno) {
    $("input[name=pageNo]").val(pno);
    var param=$("#orderForm").serialize();
    $.ajax({
        type:"post",
        url:basePath + "/market/order/asyncpage",
        data:param,
        async: false,
        success:function (data) {
            page = data;
            var orderList = page.resultList;    //获取对象结果集
            var html = "";
            for (var i = 0; i < orderList.length; i++) {
                html += "<tr><td>"+ parseInt((page.pageNo-1) * page.pageSize + i + 1) +"</td>"
                    +"<td>"+orderList[i].orderNo+"</td>";
                if(orderList[i].customer != null){
                    html += "<td>"+orderList[i].customer.name+"</td>"
                    +"<td>"+orderList[i].customer.tel+"</td>";
                }
                if(orderList[i].createTime !=null){
                    html += "<td>"+orderList[i].createTime+"</td>";
                }else{
                    html += "<td></td>";
                }
                    html += "<td>"+orderList[i].money+"</td>"
                    +"<td>"+orderList[i].createUser.name+"</td>";
                if(orderList[i].status == '0'){
                    html += "<td>未审核</td>";
                }else if(orderList[i].status == '1'){
                    html += "<td>审核中</td>";
                }else if(orderList[i].status == '2'){
                    html += "<td>审核通过</td>";
                }else if(orderList[i].status == '3'){
                    html += "<td>审核未通过</td>";
                }

                if(orderList[i].user != null){
                    html += "<td>"+orderList[i].user.name+"</td>";
                }else{
                    html += "<td></td>";
                }
                if(orderList[i].checkTime != null){
                    html += "<td>"+orderList[i].checkTime+"</td>";
                }else{
                    html += "<td></td>";
                }
                if(loginUserPositionNo == '01001'){
                    if(orderList[i].status == '2') {
                        html += "<td>" +
                            "<a href='orderView.html?orderNo=" + orderList[i].orderNo + "' class=\"tablelink\">查看详情</a>"
                            + "&nbsp;<a href='" + basePath + "/storage/delivery/deliveryView.html?id=" + orderList[i].orderNo + "' class=\"tablelink\">出库详情</a> "
                            + "</td></tr>";
                    }else{
                        html += "<td>"+
                            "<a href='orderView.html?orderNo="+orderList[i].orderNo+"' class=\"tablelink\">查看详情</a>"
                            +"</td></tr>";
                    }
                }else{
                    if(orderList[i].status == '0'){
                        html += "<td>" +
                            "<a href='orderView.html?orderNo="+orderList[i].orderNo+"' class=\"tablelink\">查看详情</a>"
                            +"&nbsp;<a href='orderUpdate.html?orderNo="+orderList[i].orderNo+"' class=\"tablelink\">修改</a>"
                            +"&nbsp;<a href='javascript:void(0);' class=\"tablelink\" onclick='deleteOrder(\""+orderList[i].orderNo+"\")'>删除</a>"
                            +"&nbsp;<a href='javascript:void(0);' class=\"tablelink\" onclick='examinetipOpen(\""+orderList[i].orderNo+"\")'>提交审核</a>"
                            +"</td>";
                    }else if(orderList[i].status == '1'){
                        html += "<td>"+
                            "<a href='orderView.html?orderNo="+orderList[i].orderNo+"' class=\"tablelink\">查看详情</a>"
                            +"</td>";
                    }else if(orderList[i].status == '2'){
                        html += "<td>"+
                            "<a href='orderView.html?orderNo="+orderList[i].orderNo+"' class=\"tablelink\">查看详情</a>"
                            +"&nbsp;<a href='"+basePath+"/storage/delivery/deliveryView.html?id="+orderList[i].orderNo+"' class=\"tablelink\">出库详情</a> "
                            +"</td>";
                    }else if(orderList[i].status == '3'){
                        html += "<td>"
                            +"<a href='orderUpdate.html?orderNo="+orderList[i].orderNo+"' class=\"tablelink\">修改</a>"
                            +"&nbsp;<a href='javascript:void(0);' class=\"tablelink\" onclick='examinetipOpen(\""+orderList[i].orderNo+"\")'>提交审核</a>"
                            +"</td>";
                    }
                    html += "</tr>";
                }

            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType:"json"
    });
}

//删除订购单
function deleteOrder(orderNo) {
    var flag = confirm("是否确认删除该订购单？");
    if(flag){
        $.ajax({
            type:"post",
            url:basePath + "/market/order/asyncdel",
            data:"orderNo="+orderNo,
            success:function (data) {
                alert(data);
                orderPage();
            }
        });
    }
}

//提交审核
function examinetipOpen(orderNo) {
    $("#examinetip").fadeIn(200);
    $("input[name=checkOrderNo]").val(orderNo);
    $.ajax({
        type:"post",
        url:basePath + "/market/order/asyncposition",
        success:function (data) {
            var html = "<option value=''>请选择</option>"
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].no+"'>"+data[i].name+"</option>";
            }
            $("#positionOrder").html(html);
        },
        dataType:"json"
    });
}

function check() {
    var param = $("#positionOrder").val();
    $.ajax({
        type:"post",
        url:basePath + "/market/order/asynccheckuser",
        data:"positionno="+param,
        success:function (data) {
            var html = "<option>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
            }
            $("#checkOrder").html(html);
        },
        dataType:"json"
    });
}

function grantCheck() {
    var param = $("#checking").serialize();
    $.ajax({
        type:"post",
        url:basePath + "/market/order/asyncupcheck",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href = basePath + "/market/order/orderList.html";
        }
    });
}


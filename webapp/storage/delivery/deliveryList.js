/*初始化函数  $();*/
var page;//分页改成全局使用便捷  初始化函数后被赋值
$(function () {
    //分页查询
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            outstoragePage(currPage);
        }
    });
    outstoragePage(1);
});

//分页模糊查询员工信息
function outstoragePage(pno) {
    //获取当前登录对象
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/getcurruser",
        async:false,
        success:function (data) {
            $("input[name=currLoginUserName]").val(data.name);
            if(data.name == "Hx"){
                $("#addstock").css('display','none');
            }
        },
        dataType:"json"
    });

    $("input[name='pageNo']").val(pno);
    var param = $("#form").serialize();//jquery获取表单参数成字符串
    $.ajax({
        type:"post",
        url:basePath+"/storage/outstorage/outstoragepage",//basePath获取根路径
        async: false,
        data:param,
        success:function (data) {
            page = data;//page对象
            var outstorageList = page.resultList;//获取结果集
            var tbody = "";
            for (var i = 0; i < outstorageList.length; i++) {
                var orderNo = outstorageList[i].order.orderNo;
                var money = outstorageList[i].order.money;
                var storageName = outstorageList[i].storage.name;
                var createTime = "";
                if(outstorageList[i].createTime != null){
                    createTime = outstorageList[i].createTime;
                }
                var stoManName = outstorageList[i].storage.principalPerson.name;
                var status = "";
                var info1 = "";
                var info2 = "";
                if(outstorageList[i].status == 1){
                    status = "未发货";
                    info1 = "是否确认发货?";
                    info2 = "是否确认取消订单"
                }
                if(outstorageList[i].status == 2){
                    status = "已发货";
                    info1 = "是否确认取消订单?";
                    info2 = "是否确认回款?";
                }
                if(outstorageList[i].status == 3){
                    status = "已回款";
                    info1 = "是否确认取消订单?";

                }
                if(outstorageList[i].status == 4){
                    status = "取消订单";
                    info1 = "是否确认此订单已经退货?";
                }
                if(outstorageList[i].status == 5){
                    status = "已退货";
                }
                tbody += "<tr>" +
                    "<td>"+ parseInt((page.pageNo-1) * page.pageSize + i + 1)+ "</td>" +
                    "<td>" + orderNo + "</td>" +
                    "<td>" + money + "</td>" +
                    "<td>" + storageName + "</td>" +
                    "<td>" + createTime + "</td>" +
                    "<td>" + stoManName + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td>" +
                    "<a href='deliveryView.html?id="+outstorageList[i].order.orderNo+"'>查看详情</a>&nbsp;&nbsp;";
                var loginUserName = $("input[name=currLoginUserName]").val();
                if(loginUserName != "Hx"){
                    if(outstorageList[i].status == 1){
                        tbody +="<a href='javascript:void(0)' class='tablelink' " +
                            "onclick='tipOpen(\"" + info1 + "\");setTipValue(\"" + outstorageList[i].order.orderNo + "\"," + outstorageList[i].status + "," + 2 + ") '>发货</a>&nbsp;&nbsp;";
                        tbody +="<a href='javascript:void(0)' class='tablelink' " +
                            "onclick='tipOpen(\"" + info2 + "\");setTipValue(\"" + outstorageList[i].order.orderNo + "\"," + outstorageList[i].status + "," + 5 + ") '>取消订单</a>";
                    }
                    if(outstorageList[i].status == 2){
                        tbody +="<a href='javascript:void(0)' class='tablelink' " +
                            "onclick='tipOpen(\"" + info1 + "\");setTipValue(\"" + outstorageList[i].order.orderNo + "\"," + outstorageList[i].status + "," + 4 +  ") '>取消订单</a>&nbsp;&nbsp;";
                        tbody +="<a href='javascript:void(0)' class='tablelink' " +
                            "onclick='tipOpen(\"" + info2 + "\");setTipValue(\"" + outstorageList[i].order.orderNo + "\"," + outstorageList[i].status + "," + 3 +  ") '>确认回款</a>";
                    }
                    if(outstorageList[i].status == 4){
                        tbody +="<a href='javascript:void(0)' class='tablelink' " +
                            "onclick='tipOpen(\"" + info1 + "\");setTipValue(\"" + outstorageList[i].order.orderNo + "\"," + outstorageList[i].status + "," + 5 +  ") '>确认已退货</a>&nbsp;&nbsp;";
                    }
                }
                tbody += "</td>" +
                    "</tr>"
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        } ,
        dataType:"json"
    });

}

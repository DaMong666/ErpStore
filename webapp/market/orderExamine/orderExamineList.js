var page;
var loginUserPositionNo = 0;
$(function () {

    $.ajax({
        type: "post",
        url: basePath + "/login/curr",
        success: function (data) {
            loginUserPositionNo = data.positionNo.no;
        },
        dataType: "json"
    });


    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            orderExaminePage(currPage);
        }
    });

    orderExaminePage(1);

});

//订购单管理分页
function orderExaminePage(pno) {
    $("input[name=pageNo]").val(pno);
    var param=$("#orderExamineForm").serialize();
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
                if(orderList[i].status == '1'){
                    html += "<td>审核中</td>";
                }
                if(loginUserPositionNo == '01001'){
                    if(true){
                        html += "<td></td>";
                    }
                }else{
                    if(true){
                        html += "<td><a href='orderExamine.html?orderNo="+orderList[i].orderNo+"' class=\"tablelink\">审核</a> </td>";
                    }
                }
                html += "</tr>";
            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType:"json"
    });
}


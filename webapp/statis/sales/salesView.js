var page;

$(function () {

    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            salesDetailByPge(currPage);
        }
    });

    salesDetailByPge(1);
});

function salesDetailByPge(pno) {
    $("input[name='pageNo']").val(pno);
    var id = getParameter("id");
    $.ajax({
        type:"post",
        url:basePath + "/statis/sales/asyncsalesdetail",
        data:"id="+id,
        success:function (data) {
            page = data;
            var detailList = page.resultList;
            var html = "";
            for (var i = 0; i < detailList.length; i++) {
                html += "<tr>" +
                    "<td>"+parseInt((page.pageNo-1) * page.pageSize + i + 1)+"</td>" +
                    "<td>"+detailList[i].cusname.name+"</td>"+
                    "<td>"+detailList[i].order.orderNo+"</td>"+
                    "<td>"+detailList[i].count+"</td>"+
                    "<td>"+detailList[i].order.money+"</td>"+
                    "<td>"+detailList[i].order.createTime+"</td>"+
                    "<td>"+detailList[i].order.createUser.name+"</td>"+
                    "<td><a href=\"../../market/order/orderView.html?orderNo="+detailList[i].order.orderNo+"\" class=\"tablelink\">查看详情</a></td>"
                    "</tr>";
            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType:"json"
    });
}
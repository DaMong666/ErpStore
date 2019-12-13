var page;//分页对象
var loginUserId=0;
$(function () {
    $("#pagination_5").whjPaging({
        totalPage: $("input[name='pageCount']").val(),
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            purchasePage(currPage);
        }
    });
    $.ajax({
        type:"post",
        url:basePath + "/purchase/brand/curruser",
        async:false,
        success:function (data) {
            loginUserId=data.id;
        },
        dataType:"json"
    });
    purchasePage(null);
});

//分页查询
function purchasePage(pno) {
    $("input[name='pageNo']").val(pno);
    var param=$("#form").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchase/ppage",
        async:false,
        data:param,
        success:function (data) {
            page=data;
            var purchaseList=page.resultList;
            var html="";
            for (var i = 0; i < purchaseList.length; i++) {
                var view="purchaseView.html?purchaseNo="+purchaseList[i].purchaseNo+"";
                var update="purchaseUpdate.html?purchaseNo="+purchaseList[i].purchaseNo+"";

                html+="<tr>"
                    +"<td>"+parseInt((page.pageNo-1) * page.pageSize + i + 1)+"</td>"
                    +"<td>"+purchaseList[i].purchaseNo+"</td>";
                if(purchaseList[i].createTime !=null){
                    html += "<td>"+purchaseList[i].createTime+"</td>";
                }else {
                    html += "<td></td>";
                }
                html+="<td>"+purchaseList[i].money+"</td>"
                if(purchaseList[i].createUser !=null){
                    html += "<td>"+purchaseList[i].createUser.name+"</td>";
                }else {
                    html += "<td></td>";
                }
                if(purchaseList[i].status == 0){
                    html += "<td>未审核</td>";
                }else if(purchaseList[i].status == 1){
                    html += "<td>审核中</td>";
                }else if(purchaseList[i].status == 2){
                    html += "<td>审核通过</td>";
                }else if(purchaseList[i].status == 3){
                    html += "<td>审核不通过</td>";
                }
                if(purchaseList[i].checkUser !=null){
                    html += "<td>"+purchaseList[i].checkUser.name+"</td>";
                }else {
                    html += "<td></td>";
                }
                if(purchaseList[i].checkTime !=null){
                    html += "<td>"+purchaseList[i].checkTime+"</td>";
                }else {
                    html += "<td></td>";
                }


                if (purchaseList[i].status=='0') {
                    if (purchaseList[i].createUser.id==loginUserId){
                        html+="<td><a href='"+view+"' class='tablelink'>查看详情</a>"
                            +"  <a href='"+update+"' class='tablelink'>修改</a>"
                            +"  <a href='javascript:void(0);' class='tablelink' onclick='deltipOpen();setValue(\"" + purchaseList[i].purchaseNo + "\",\"" + purchaseList[i].status + "\") '>删除</a>"
                            +"  <a href='javascript:void(0);' class='tablelink' onclick='examinetipOpen();setValue(\"" + purchaseList[i].purchaseNo + "\",\"" + purchaseList[i].status + "\")'>提交审核</a>";
                    }else {
                        html+="<td><a href='"+view+"' class='tablelink'>查看详情</a>";
                    }
                }
                if (purchaseList[i].status=='1') {
                    html+="<td><a href='"+view+"' class='tablelink'>查看详情</a>";
                }
                if (purchaseList[i].status=='2') {
                    html+="<td><a href='"+view+"' class='tablelink'>查看详情</a>"
                        +"  <a href='../../storage/stock/stockView.html?id="+purchaseList[i].purchaseNo+"' class='tablelink'>入库详情</a>";
                }
                if (purchaseList[i].status=='3') {
                    if (purchaseList[i].createUser.id==loginUserId) {
                        html += "<td><a href='" + update + "' class='tablelink'>修改</a>"
                            + "  <a href='javascript:void(0);' class='tablelink' onclick='examinetipOpen();setValue(\"" + purchaseList[i].purchaseNo + "\",\"" + purchaseList[i].status + "\")'>提交审核</a>";
                    }else{
                        html += "<td>"
                            + "  <a href='javascript:void(0);' class='tablelink' onclick='examinetipOpen();setValue(\"" + purchaseList[i].purchaseNo + "\",\"" + purchaseList[i].status + "\")'>提交审核</a>";
                    }
                }

                html +="</td></tr>";

            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
            $('.tablelist tbody tr:odd').addClass('odd');
        },
        dataType:"json"
    });
}
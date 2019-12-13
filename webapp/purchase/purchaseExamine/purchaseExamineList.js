var page;//分页对象

$(function () {
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            purchaseExaminePage(currPage);
        }
    });
    purchaseExaminePage(null);
});
//分页查询
function purchaseExaminePage(pno) {
    $("input[name='pageNo']").val(pno);
    var param=$("#form").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchaseExamine/pepage",
        async:false,
        data:param,
        success:function (data) {
            page=data;
            var purchaseExamine=page.resultList;
            var html="";
            for (var i = 0; i < purchaseExamine.length; i++) {
                var view="purchaseExamine.html?purchaseNo="+purchaseExamine[i].purchaseNo+"";

                html+="<tr>"
                    +"<td>"+parseInt((page.pageNo-1) * page.pageSize + i + 1)+"</td>"
                    +"<td>"+purchaseExamine[i].purchaseNo+"</td>";
                if(purchaseExamine[i].createTime !=null){
                    html += "<td>"+purchaseExamine[i].createTime+"</td>";
                }else {
                    html += "<td></td>";
                }
                html+="<td>"+purchaseExamine[i].money+"</td>"
                if(purchaseExamine[i].createUser !=null){
                    html += "<td>"+purchaseExamine[i].createUser.name+"</td>";
                }else {
                    html += "<td></td>";
                }
                if(purchaseExamine[i].status == 1){
                    html += "<td>审核中</td>";
                }

                if (purchaseExamine[i].status=='1') {
                    html+="<td><a href='"+view+"' class='tablelink'>审核</a>";
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
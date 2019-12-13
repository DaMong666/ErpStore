var page;//分页对象

$(function () {
    /*jumpPage();*/
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            brandPage(currPage);
        }
    });
    brandPage(null);
});

//分页查询
function brandPage(pno) {
    $("input[name='pageNo']").val(pno);
    var param=$("#brandForm").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/brand/bpage",
        async:false,
        data:param,
        success:function (data) {
            page=data;
            var brandList=page.resultList;
            var html="";
            for (var i = 0; i < brandList.length; i++) {
                var status = brandList[i].status;
                var info = "";
                var deStatus = "";
                if (status == 1) {
                    status = "恢复";
                    info = "是否确认注销此品牌？";
                    deStatus = "注销";
                } else {
                    status = "注销";
                    info = "是否确认恢复此品牌？";
                    deStatus = "恢复";
                }

                html+="<tr>"
                    +"<td>"+parseInt((page.pageNo-1) * page.pageSize + i + 1)+"</td>"
                    +"<td>"+brandList[i].name+"</td>";
                if(brandList[i].status == 1){
                    html += "<td>可用</td>";
                }else {
                    html += "<td>不可用</td>";
                }
                if(brandList[i].createTime !=null){
                    html += "<td>"+brandList[i].createTime+"</td>";
                }else {
                    html += "<td></td>";
                }
                if(brandList[i].createUser.name !=null){
                    html += "<td>"+brandList[i].createUser.name+"</td>";
                }else {
                    html += "<td></td>";
                }
                html+=  "<td><a href='brandUpdate.html?id="+brandList[i].id+"' class='tablelink'>修改</a>"
                    +"    <a href='javascript:void(0);' class='tablelink' onclick='tipOpen(\"" + info + "\");setTipValue(" + brandList[i].id + "," + brandList[i].status + ") '>" + deStatus + "</a>";
                html +="</td></tr>";

            }
            $(".tablelist tbody").html(html);
            /*$(".pageNo").val(page.pageNo);//当前页
            $("#rowCount").html(page.rowCount);
            $("#pageCount").html(page.pageCount);//总页面数
            $("#pageNo").html(page.pageNo);*/
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
            $('.tablelist tbody tr:odd').addClass('odd');
        },
        dataType:"json"
    });
}
/*
//条件搜索
function search() {
    $(".pageNo").val(1);
    brandPage();
}

function jumpPage() {//.click单击事件
    $(".firstPage").click(function () {
        $(".pageNo").val(1);
        brandPage();
    })
    $(".prePage").click(function () {
        $(".pageNo").val(page.prePage);
        brandPage();
    })
    $(".nextPage").click(function () {
        $(".pageNo").val(page.nextPage);
        brandPage();
    })
    $(".lastPage").click(function () {
        $(".pageNo").val(page.lastPage);
        brandPage();
    })
}*/

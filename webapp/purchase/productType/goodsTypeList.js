var page;//分页对象
$(function () {
    brand();
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            goodsTypePage(currPage);
        }
    });
    goodsTypePage(null);
});
//获取全部品牌
function brand() {
    $.ajax({
        type:"post",
        url:basePath+"/purchase/brand/brandList",
        success:function (data) {
            var html="<option value='0'>请选择</option>";
            for (var i = 0; i <data.length; i++) {
                html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>"
            }
            $("select[name=brandId]").html(html);
        },
        dataType:"json"
    });
}
function goodsTypePage(pno) {
    $("input[name='pageNo']").val(pno);
    var param=$("#goodsTypeForm").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/productType/gtpage",
        async:false,
        data:param,
        success:function (data) {
            page=data;
            var goodsTypeList=page.resultList;
            var html="";
            for (var i = 0; i < goodsTypeList.length; i++) {
                var status = goodsTypeList[i].status;
                var info = "";
                var deStatus = "";
                if (status == 1) {
                    status = "恢复";
                    info = "是否确认注销此品牌类型？";
                    deStatus = "注销";
                } else {
                    status = "注销";
                    info = "是否确认恢复此品牌类型？";
                    deStatus = "恢复";
                }

                html+="<tr>"
                    +"<td>"+parseInt((page.pageNo-1) * page.pageSize + i + 1)+"</td>"
                    +"<td>"+goodsTypeList[i].brand.name+"</td>"
                    +"<td>"+goodsTypeList[i].name+"</td>";
                if(goodsTypeList[i].status == 1){
                    html += "<td>可用</td>";
                }else {
                    html += "<td>不可用</td>";
                }
                if(goodsTypeList[i].createTime !=null){
                    html += "<td>"+goodsTypeList[i].createTime+"</td>";
                }else {
                    html += "<td></td>";
                }
                if(goodsTypeList[i].createUser.name !=null){
                    html += "<td>"+goodsTypeList[i].createUser.name+"</td>";
                }else {
                    html += "<td></td>";
                }
                html+=  "<td><a href='productTypeUpdate.html?id="+goodsTypeList[i].id+"' class='tablelink'>修改</a>"
                    +"    <a href='javascript:void(0);' class='tablelink' onclick='tipOpen(\"" + info + "\");setTipValue(" + goodsTypeList[i].id + "," + goodsTypeList[i].status + ") '>" + deStatus + "</a>";
                html +="</td></tr>";

            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
            $('.tablelist tbody tr:odd').addClass('odd');
        },
        dataType:"json"
    });
}
//条件搜索
function search() {
    $(".pageNo").val(1);
    goodsTypePage();
}
function jumpPage() {//.click单击事件
    $(".firstPage").click(function () {
        $(".pageNo").val(1);
        goodsTypePage();
    })
    $(".prePage").click(function () {
        $(".pageNo").val(page.prePage);
        goodsTypePage();
    })
    $(".nextPage").click(function () {
        $(".pageNo").val(page.nextPage);
        goodsTypePage();
    })
    $(".lastPage").click(function () {
        $(".pageNo").val(page.lastPage);
        goodsTypePage();
    })
}

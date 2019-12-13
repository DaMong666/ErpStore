var page;//分页对象

$(function () {
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            goodsPage(currPage);
        }
    });
    goodsPage(null);
    brand();
});

//分页查询
function goodsPage(pno) {
    $("input[name='pageNo']").val(pno);
    var param=$("#form").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/product/gpage",
        async:false,
        data:param,
        success:function (data) {
            page=data;
            var goodsList=page.resultList;
            var html="";
            for (var i = 0; i < goodsList.length; i++) {
                var status = goodsList[i].status;
                var info = "";
                var deStatus = "";
                if (status == 1) {
                    status = "恢复";
                    info = "是否确认注销此商品？";
                    deStatus = "注销";
                } else {
                    status = "注销";
                    info = "是否确认恢复此商品？";
                    deStatus = "恢复";
                }

                html+="<tr>"
                    +"<td>"+parseInt((page.pageNo-1) * page.pageSize + i + 1)+"</td>"
                    +"<td>"+goodsList[i].goodsType.brand.name+"</td>"
                    +"<td>"+goodsList[i].goodsType.name+"</td>"
                    +"<td>"+goodsList[i].model+"</td>"
                    +"<td>"+goodsList[i].unit+"</td>";
                if(goodsList[i].status == 1){
                    html += "<td>可用</td>";
                }else {
                    html += "<td>不可用</td>";
                }
                if(goodsList[i].createTime !=null){
                    html += "<td>"+goodsList[i].createTime+"</td>";
                }else {
                    html += "<td></td>";
                }
                if(goodsList[i].createUser.name !=null){
                    html += "<td>"+goodsList[i].createUser.name+"</td>";
                }else {
                    html += "<td></td>";
                }
                html+=  "<td><a href='productUpdate.html?id="+goodsList[i].id+"' class='tablelink'>修改</a>"
                    +"    <a href='javascript:void(0);' class='tablelink' onclick='tipOpen(\"" + info + "\");setTipValue(" + goodsList[i].id + "," + goodsList[i].status + ") '>" + deStatus + "</a>";
                html +="</td></tr>";

            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
            $('.tablelist tbody tr:odd').addClass('odd');
        },
        dataType:"json"
    });
}


//品牌下拉框
function brand() {
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/brand",
        success:function (data) {
            console.log(data);
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
            }
            $("#brandId").html(html);
        },
        dataType:"json"
    });
}
//商品类型下拉框
function loadGoodsType() {
    var brandId=$("#brandId").val();
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/goodsType",
        data:"brandId="+brandId,
        success:function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
            }
            $("#goodsTypeId").html(html);
        },
        dataType:"json"
    });
}
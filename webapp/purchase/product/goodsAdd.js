$(function () {
    $.ajax({
        type:"post",
        url:basePath + "/purchase/brand/curruser",
        success:function (data) {
            $("#createUser").val(data.name);
        },
        dataType:"json"
    });

    $.ajax({
        type:"post",
        url:basePath + "/purchase/brand/currdate",
        success:function (data) {
            $("#createTime").val(data);
        }
    });
    brand();
});
//添加
function add() {
    var  param=$("#form").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/product/add",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href=basePath+'/purchase/product/productAdd.html';
        }
    });
}
//品牌下拉框
function brand() {
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/brand",
        success:function (data) {
            var html="<option value='0'>请选择</option>";
            for (var i = 0; i <data.length; i++) {
                if(data[i].status=='1'){
                    html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>"
                }
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
                if(data[i].status=='1') {
                    html += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                }
            }
            $("#goodsTypeId").html(html);
        },
        dataType:"json"
    });
}

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
//获取可用的品牌
function brand() {
    $.ajax({
        type:"post",
        url:basePath+"/purchase/brand/brandList",
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
//添加
function add() {
    var  param=$("#form").serializeArray();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/productType/add",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href=basePath+'/purchase/productType/productTypeAdd.html';
        }
    });
}
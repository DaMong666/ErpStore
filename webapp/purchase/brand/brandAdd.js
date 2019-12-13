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
});
//添加
function add() {
    var  param=$("#brandAddForm").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/brand/add",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href=basePath+'/purchase/brand/brandAdd.html';
        }
    });
}
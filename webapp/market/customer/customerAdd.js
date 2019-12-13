$(function () {
    //获取当前登录用户姓名
    $.ajax({
        type: "post",
        url: basePath + "/login/curr",
        success: function (data) {
            $("#createUser").val(data.name);
        },
        dataType: "json"
    });

    //获取当前时间
    $.ajax({
        type: "post",
        url: basePath + "/purchase/brand/currdate",
        success: function (data) {
            $("#createTime").val(data);
        }
    });
});

//添加
function add() {
    var param = $("#cusAddForm").serialize();
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/addCus",
        data: param,
        success: function (data) {
            alert(data);
            window.location.href=basePath+'/market/customer/customerAdd.html';
        }
    });
}
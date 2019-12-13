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
    province();
});
//添加
function add() {
    var  param=$("#form").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/manufacturer/vadd",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href=basePath+'/purchase/manufacturer/manufacturerAdd.html';
        }
    });
}

//省份下拉框
function province() {
    $.ajax({
        type:"post",
        url:basePath +"/purchase/manufacturer/province",
        success:function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].proId+"'>"+data[i].name+"</option>";
            }
            $("#provinceId").html(html);
        },
        dataType:"json"
    });
}
//城市下拉框
function loadCity() {
    var provinceId=$("#provinceId").val();
    $.ajax({
        type:"post",
        url:basePath +"/purchase/manufacturer/city",
        data:"provinceId="+provinceId,
        success:function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].cityId+"'>"+data[i].name+"</option>";
            }
            $("#cityId").html(html);
        },
        dataType:"json"
    });
}
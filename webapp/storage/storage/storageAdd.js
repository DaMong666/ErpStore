$(function () {
    //省份下拉框
    province();//下面写，调用
    //负责人下拉框
    principalPerson();
    //获取当前对象
    getCurrUser();
    //获取当前时间
    getCurrTime();

});

//省份下拉框
function province() {
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/province",
        success:function (data) {
            var html = "<option value='0'>请选择</option>";
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
        url:basePath +"/storage/storage/city",
        data:"provinceId="+provinceId,
        success:function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].cityId+"'>"+data[i].name+"</option>";
            }
            $("#cityId").html(html);
        },
        dataType:"json"
    });
}
//获取仓库负责人下拉框
function principalPerson() {
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/principal",
        success:function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
            }
            $("#principalId").html(html);
        },
        dataType:"json"
    });
}
//获取当前登录对象
function getCurrUser() {
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/getcurruser",
        success:function (data) {
            $("#createName").val(data.name);

        },
        dataType:"json"
    });
}
//获取当前时间
function getCurrTime() {
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/getcurrtime",
        success:function (data) {
            $("input[name=createTime]").val(data);
        },
    });
}
//添加
function addStorage() {
    var param = $("#form").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/storage/storage/addStorage",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href=basePath + "/storage/storage/storageAdd.html";
        },
    });
}
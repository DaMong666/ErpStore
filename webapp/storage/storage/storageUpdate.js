$(function () {
    //省份下拉框
    province();//下面写，调用
    //城市下拉框
    city();
    //负责人下拉框
    principalPerson();
    //仓库信息回显
    getStorageInfo();
});
//省份下拉框
function province() {
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/province",
        async:false,//改成同步 在mgr和dept显示后执行
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
function city() {
    //根据仓库id获取到省份id
    var storageId = getParameter("id");
    var proId = 0;
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/toview",
        data:"storageId="+storageId,
        async:false,//改成同步
        success:function (data) {
            if(data.city != null){
                proId = data.city.proId.proId;
            }
        },
        dataType:"json"
    });
    //通过省份id查询该省城市
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/city",
        async:false,//改成同步
        data:"provinceId="+proId,
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
        async:false,//改成同步 在mgr和dept显示后执行
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
//仓库信息回显
function getStorageInfo() {
    var storageId = getParameter("id");
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/toview",
        data:"storageId="+storageId,
        success:function (data) {
            $("input[name=name]").val(data.name);
            $("input[name=address]").val(data.address);
            if(data.city != null){
                $("#provinceId").val(data.city.proId.proId);
                $("#cityId").val(data.city.cityId);
            }else{
                $("#cityId").val(0);
                $("#provinceId").val(0);
            }
            if(data.principalPerson != null){
                $("#principalId").val(data.principalPerson.id);
            }else{
                $("#principalId").val(0);
            }
            $("input[name=tel]").val(data.tel);
            $("textarea[name=description]").val(data.description);
            if(data.status == 0){
                $("#status").val("0");
            }else{
                $("#status").val("1");
            }
            if(data.createPerson != null){
                $("#createName").val(data.createPerson.name);
            }else{
                $("#createName").val(0);
            }
            $("input[name=createTime]").val(data.createTime);
            $("input[name=id]").val(data.id);
            $("#createUserId").val(data.createPerson.id);
        },
        dataType:"json"
    });
}
/*省份改变，城市改变*/
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

/**
 * 修改仓库按钮
 */
function updateStorage() {
    var param = $("#form").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/storage/storage/updateStorage",
        data:param,
        success:function (data) {
            alert(data);
            //window.location.href=basePath + "/emp/empUpdate.html";
            window.location.reload();//本页面重新加载
        }
    });
}
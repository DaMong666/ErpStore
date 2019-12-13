$(function () {
    province();
    city();
    toUpdate();
});
function toUpdate() {
    var id=getParameter("id");
    $.ajax({
        type:"post",
        url:basePath+"/purchase/manufacturer/vtupdate",
        data:"id="+id,
        success:function (data) {
            $("input[name=name]").val(data.name);
            $("input[name=principal]").val(data.principal);
            $("input[name=tel]").val(data.tel);
            if(data.city != null) {
                $("#provinceId").val(data.city.proId.proId);
                $("#cityId").val(data.city.cityId);
            }
            $("input[name=address]").val(data.address);
            $("textarea[name=description]").val(data.description);
            $("select[name=status]").val(data.status);
            $("#createUser").val(data.createUser.name);
            $("input[name=createTime]").val(data.createTime);
        },
        dataType:"json"
    });
}
function update() {
    var  param=$("#form").serialize();
    var id=getParameter("id");
    param+="&id="+id;
    $.ajax({
        type:"post",
        url:basePath+"/purchase/manufacturer/vupdate",
        data:param,
        success:function (data) {
            alert(data);
            window.location.reload();
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

//城市下拉框
function city() {
    //根据厂商id获取到省份id
    var vendorId = getParameter("id");
    var proId = "";
    $.ajax({
        type:"post",
        url:basePath +"/purchase/manufacturer/vtupdate",
        data:"id="+vendorId,
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
        url:basePath +"/purchase/manufacturer/city",
        async:false,//改成同步
        data:"provinceId="+proId,
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
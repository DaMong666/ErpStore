var id = getParameter("id");
$(function () {
    //省份下拉框
    province();//下面写，调用
    //城市下拉框
    city();
    //获取需要修改的客户信息
    cusUpView();
});

//省份下拉框
function province() {
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/province",
        async: false,//改成同步 在mgr和dept显示后执行
        success: function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].proId + "'>" + data[i].name + "</option>";
            }
            $("#provinceId").html(html);
        },
        dataType: "json"
    });
}

//城市下拉框
function city() {
    //根据id获取到省份id
    var proId = 0;
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/cusById",
        data: "id=" + id,
        async: false,//改成同步
        success: function (data) {
            if (data.city != null) {
                proId = data.city.proId.proId;
            }
        },
        dataType: "json"
    });
    //通过省份id查询该省城市
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/city",
        async: false,//改成同步
        data: "provinceId=" + proId,
        success: function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].cityId + "'>" + data[i].name + "</option>";
            }
            $("#cityId").html(html);
        },
        dataType: "json"
    });
}

/*省份改变，城市改变*/
function loadCity() {
    var provinceId = $("#provinceId").val();
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/city",
        data: "provinceId=" + provinceId,
        success: function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].cityId + "'>" + data[i].name + "</option>";
            }
            $("#cityId").html(html);
        },
        dataType: "json"
    });
}

//修改前呈现原来信息
function cusUpView() {
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/cusById",
        data: "id=" + id,
        success: function (data) {
            $("#id").val(data.id);
            $("#name").val(data.name);
            $("#company").val(data.company);
            $("#tel").val(data.tel);
            $("#address").val(data.address);
            $("#description").val(data.description);
            $("#createTime").val(data.createTime);
            $("#createUser").val(data.createUser.name);
            $("#cityH").val(data.city.cityId);
            $("#provinceH").val(data.city.proId.proId);
            if (data.sex == '1') {
                if ($("#sex1").val() == '1') {
                    $("#sex1").attr("checked", "checked");
                }
            } else if (data.sex == '0') {
                if ($("#sex0").val() == '0') {
                    $("#sex0").attr("checked", "checked");
                }
            }
            if (data.city != null) {
                $("#provinceId").val(data.city.proId.proId);
                $("#cityId").val(data.city.cityId);
            } else {
                $("#provinceId").val(0);
                $("#cityId").val(0);
            }
        },
        dataType: "json"
    });
}

/**
 * 修改客户信息
 */
function upBtn() {
    var param = $("#upForm").serialize();
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/upCus",
        data: param,
        success: function (data) {
            alert(data);
            window.location.href=basePath + "/market/customer/customerList.html";
        }
    });
}
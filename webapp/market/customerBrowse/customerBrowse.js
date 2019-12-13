//分页对象
var page;

$(function () {
    //省份下拉框
    province();

    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            cusBroPage(currPage);
        }
    });
    //客户管理分页
    cusBroPage(1);

});

//省份下拉框
function province() {
    $.ajax({
        type: "post",
        url: basePath + "/storage/storage/province",
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
function loadCity() {
    var provinceId = $("#provinceId").val();
    $.ajax({
        type: "post",
        url: basePath + "/storage/storage/city",
        data: "provinceId=" + provinceId,
        success: function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].cityId + "'>" + data[i].name + "</option>";
            }
            $("#cityId").html(html);
        },
        dataType: "json"
    });
}

//客户管理分页
function cusBroPage(pno) {
    $("input[name='pageNo']").val(pno);
    var param = $("#cusBroForm").serialize();//jquery获取表单参数成字符串
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/cusPage",//basePath获取根路径
        data: param,
        async: false,
        success: function (data) {
            page = data;//page对象
            var customerList = page.resultList;//获取结果集
            var html = "";
            for (var i = 0; i < customerList.length; i++) {
                html += "<tr>" +
                    "<td>" + parseInt((page.pageNo-1) * page.pageSize + i + 1) + "</td>" +
                    "<td>" + customerList[i].name + "</td>";
                if (customerList[i].sex == '1') {
                    html += "<td>男</td>";
                } else {
                    html += "<td>女</td>";
                }
                html += "<td>" + customerList[i].tel + "</td>" +
                    "<td>" + customerList[i].company + "</td>" +
                    "<td>" + customerList[i].city.proId.name + customerList[i].city.name + "</td>";
                if (customerList[i].allocateTime != null) {
                    html += "<td>" + customerList[i].allocateTime + "</td>";
                } else {
                    html += "<td></td>";
                }
                if (customerList[i].user != null) {
                    html += "<td>" + customerList[i].user.name + "</td>";
                } else {
                    html += "<td></td>";
                }
                html += "<td><a href='customerView.html?id=" + customerList[i].id + "' " +
                    "class='tablelink'>查看详情</a></td>";
            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType: "json"
    });
}

//查询按钮
function search() {
    $(".pageNo").val(1);
    cusBroPage();
}

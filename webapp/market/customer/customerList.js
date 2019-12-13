//分页对象
var page;
var loginUserPositionNo = 0;
$(function () {

    $.ajax({
        type: "post",
        url: basePath + "/login/curr",
        async:false,
        success: function (data) {
            loginUserPositionNo = data.positionNo.no;
        },
        dataType: "json"
    });
    //省份下拉框
    province();

    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            customerPage(currPage);
        }
    });

    customerPage(1);

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
function customerPage(pno) {
    $("input[name='pageNo']").val(pno);
    var param = $("#customerForm").serialize();//jquery获取表单参数成字符串
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/cusPage",//basePath获取根路径
        data: param,
        async: false,
        success: function (data) {
            page = data;
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
                if (customerList[i].status == '1') {
                    html += "<td>可用</td>";
                } else {
                    html += "<td>不可用</td>";
                }
                html += "<td>" + customerList[i].createTime + "</td>" +
                    "<td>" + customerList[i].createUser.name + "</td>";
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
                html += "<td>" +
                    "<a href='customerView.html?id=" + customerList[i].id + "' class='tablelink'>查看详情</a>&nbsp;";
                if(loginUserPositionNo == '01001'){
                    if(true){
                        html += "</td></tr>";
                    }
                }if(loginUserPositionNo != '01001'){
                    if (customerList[i].status == '1') {
                        html += "<a href='customerUpdate.html?id=" + customerList[i].id + "' class='tablelink'>修改</a>&nbsp;";
                        html += "<a href=\"javascript:void(0)\" class=\"tablelink\" onclick=\"updateStatusDown(" + customerList[i].id + ")\">注销</a>&nbsp;";
                        if (customerList[i].user == null) {
                            html += "<a href=\"javascript:void(0);\" class=\"tablelink\" onclick=\"allottipOpen(" + customerList[i].id + ")\">分配</a>";
                        }
                        html += "</td></tr>";
                    } else {
                        html += "<a href=\"javascript:void(0)\" class=\"tablelink\" onclick=\"updateStatusUp(" + customerList[i].id + ")\">恢复</a>" +
                            "</td></tr>";
                    }
                }

            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType: "json"
    });
}

//恢复
function updateStatusUp(id) {
    var flag = confirm("是否恢复该客户！！！");
    if(flag){
        $.ajax({
            type:"post",
            url:basePath + "/market/customer/statusup",
            data:"id="+id,
            success:function (data) {
                alert(data);
                window.location.href = basePath + "/market/customer/customerList.html";
            }
        });
    }
}

//注销
function updateStatusDown(id) {
    var flag = confirm("是否注销该客户！！！");
    if(flag){
        $.ajax({
            type:"post",
            url:basePath + "/market/customer/statusdown",
            data:"id="+id,
            success:function (data) {
                alert(data);
                window.location.href = basePath + "/market/customer/customerList.html";
            }
        });
    }
}

//分配

function check() {
    var param = $("#positionOrder").val();
    $.ajax({
        type:"post",
        url:basePath + "/market/customer/queryfenpeiuser",
        data:"positionno="+param,
        success:function (data) {
            var html = "<option>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
            }
            $("#checkOrder").html(html);
        },
        dataType:"json"
    });
}

function allottipOpen(id) {
    $("#allottip").fadeIn(200);
    $("input[name=cusid]").val(id);
    $.ajax({
        type:"post",
        url:basePath + "/market/customer/querypositionlist",
        success:function (data) {
            var html = "<option value=''>请选择</option>"
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].no+"'>"+data[i].name+"</option>";
            }
            $("#positionOrder").html(html);
        },
        dataType:"json"
    });
}

function fenpei() {
    var param = $("#fenpeiUser").serialize();
    $.ajax({
        type:"post",
        url:basePath + "/market/customer/fenpeiuser",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href = basePath + "/market/customer/customerList.html";
        }
    });
}





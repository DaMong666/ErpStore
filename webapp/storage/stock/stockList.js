/*初始化函数  $();*/
var page;//分页改成全局使用便捷  初始化函数后被赋值
$(function () {
    //分页查询
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            instoragePage(currPage);
        }
    });
    instoragePage(1);
});
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
//分页模糊查询员工信息
function instoragePage(pno) {
    //获取当前登录对象
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/getcurruser",
        async:false,
        success:function (data) {
            $("input[name=currLoginUserName]").val(data.name);
            if(data.name == "Hx"){
                $("#addstock").css('display','none');
            }
        },
        dataType:"json"
    });
    $("input[name='pageNo']").val(pno);
    var param = $("#form").serialize();//jquery获取表单参数成字符串
    $.ajax({
        type:"post",
        url:basePath+"/storage/instorage/instoragepage",//basePath获取根路径
        async: false,
        data:param,
        success:function (data) {
            page = data;//page对象
            var instorageList = page.resultList;//获取结果集
            var tbody = "";
            for (var i = 0; i < instorageList.length; i++) {
                var purchaseNo = instorageList[i].purchase.purchaseNo;
                var money = instorageList[i].purchase.money;
                var storageName = instorageList[i].storage.name;
                var createTime = "";
                if(instorageList[i].createTime != null){
                    createTime = instorageList[i].createTime;
                }
                var stoManName = instorageList[i].storage.principalPerson.name;
                var status = "";
                var info = "";
                if(instorageList[i].status == 1){
                    status = "未入库";
                    info = "是否确认入库?";
                }
                if(instorageList[i].status == 2){
                    status = "已入库";
                    info = "是否确认取消入库?";
                }
                tbody += "<tr>" +
                    "<td>"+ parseInt((page.pageNo-1) * page.pageSize + i + 1)+ "</td>" +
                    "<td>" + purchaseNo + "</td>" +
                    "<td>" + money + "</td>" +
                    "<td>" + storageName + "</td>" +
                    "<td>" + createTime + "</td>" +
                    "<td>" + stoManName + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td>" +
                    "<a href='stockView.html?id="+instorageList[i].purchase.purchaseNo+"'>查看详情</a>&nbsp;&nbsp;";
                    var loginUserName = $("input[name=currLoginUserName]").val();
                    if(loginUserName != "Hx"){
                        if(instorageList[i].status == 1){
                            tbody +="<a href='javascript:void(0)' class='tablelink' " +
                                "onclick='tipOpen(\"" + info + "\");setTipValue(\""+instorageList[i].purchase.purchaseNo + "\"," + instorageList[i].status + ") '>入库</a>";
                        }
                        if(instorageList[i].status == 2) {
                            tbody += "<a href='javascript:void(0)' class='tablelink' " +
                                "onclick='tipOpen(\"" + info + "\");setTipValue(\""+instorageList[i].purchase.purchaseNo + "\"," + instorageList[i].status + ") '>取消入库</a>";
                        }
                    }
                    tbody += "</td>" +
                    "</tr>"
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        } ,
        dataType:"json"
    });
}


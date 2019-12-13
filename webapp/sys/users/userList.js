var page;   //分页对象
$(function () {
    getDept();
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            userListPage(currPage);
        }
    });
    userListPage(1);
});

function userListPage(pno) {
    $("input[name='pageNo']").val(pno);
    $.ajax({
        type:"post",
        url:basePath+"/sys/user/gPa",
        async: false,
        data:$("#userListForm").serializeArray(),
        success:function (data) {
            page = data;
            var userList = page.resultList;
            var tbody = "";
            for (var i = 0; i < userList.length; i++) {
                var sex = userList[i].sex;
                if (sex == 1) {
                    sex = "男";
                } else {
                    sex = "女";
                }
                var status = userList[i].status;
                var info = "";
                var deStatus = "";
                if (status == 1) {
                    status = "在职";
                    info = "您确定此员工离职吗？";
                    deStatus = "离职";
                } else {
                    status = "离职";
                    info = "您确定此员工在职吗？";
                    deStatus = "在职";
                }
                var leaveDate = userList[i].leaveDate;
                if (leaveDate == null) {
                    leaveDate = " ";
                }
                tbody += "<tr>" +
                    "<td>"+ parseInt((page.pageNo-1) * page.pageSize + i + 1)+ "</td>" +
                    "<td>" + userList[i].no + "</td>" +
                    "<td>" + userList[i].name + "</td>" +
                    "<td>" + userList[i].tel + "</td>" +
                    "<td>" + userList[i].positionNo.deptNo.name + "</td>" +
                    "<td>" + userList[i].positionNo.name + "</td>" +
                    "<td>" + sex + "</td>" +
                    "<td>" + userList[i].age + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td>" + userList[i].hireDate + "</td>" +
                    "<td>" + leaveDate + "</td>" +
                    "<td>" +
                    "    <a href='userUpdate.html?id=" + userList[i].id + "' class='tablelink'>修改</a>" +
                    "    <a href='javascript:void(0);' class='tablelink' " +
                    "       onclick='tipOpen(\"" + info + "\");setTipValue(" + userList[i].id + "," + userList[i].status + ") '>" + deStatus + "</a>" +
                    "    <a href='javascript:void(0);' class='tablelink' " +
                    "       onclick='tipOpen(\"您确定给此员工重置密码吗？(默认密码:123456)\");setTipValue(" + userList[i].id + ",-1) '>重置密码</a>" +
                    "</td>" +
                    "</tr>"
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType:"json"
    });

}



$(function () {
    getDept();
    getUpdateUser();
    getPositionForUpd();
});

/**
 * 获取职位for userUpdate.html
 */
function getPositionForUpd() {
    var deptNo = $("#dept").val();
    $.ajax({
        type: "post",
        async: false,
        url: basePath + "/sys/user/gPo",
        data: "deptNo=" + deptNo,
        success: function (data) {
            var positionList = data;
            $("#position").html("<option style=\"display:none;\" disabled selected>请选择职位</option>");
            for (var i = 0; i < positionList.length; i++) {
                $("#position").append("<option value='" + positionList[i].no + "'>" + positionList[i].name + "</option>");
            }
            $("#position").val($("input[name='hiddenPositionNo']").val());
        },
        dataType: "json"
    });
}

/**
 * 根据Id获取User
 */
function getUpdateUser() {
    var id = getParameter("id");
    $.ajax({
        type: "post",
        async: false,
        url: basePath + "/sys/user/qUBid",
        data: "id=" + id,
        success: function (data) {
            $("#dept").val(data.positionNo.deptNo.no);
            $("input[name='hiddenPositionNo']").val(data.positionNo.no);
            $("input[name='no']").val(data.no);
            $("input[name='id']").val(data.id);
            $("input[name='name']").val(data.name);
            var ids = "input[name='sex'][value='" + data.sex + "']"
            $(ids).attr("checked",true);
            $("input[name='tel']").val(data.tel);
            $("input[name='birthDay']").val(data.birthDay);
            $("select[name='status']").val(data.status);

        },
        dataType: "json"
    });
}

function updateUser() {
    $.ajax({
        type: "post",
        url: basePath + "/sys/user/uUs",
        data: $("#userUpdateForm").serializeArray(),
        success: function (data) {
            if (data.code == 1) {
                alert(data.info);
                window.location.href = "userList.html";
            } else {
                alert(data.info);
                window.location.reload();
            }
        },
        dataType: "json"
    });
}
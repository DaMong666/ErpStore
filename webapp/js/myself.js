$(function () {
    getUpdateUser();

});
/**
 * 根据Id获取User
 */
function getUpdateUser() {
    $.ajax({
        type: "post",
        async: false,
        url: basePath + "/login/currMy",
        success: function (data) {
            $("#deptName").val(data.user.positionNo.deptNo.name);
            $("#positionName").val(data.user.positionNo.name);
            $("#no").val(data.user.no);
            $("input[name='id']").val(data.user.id);
            $("input[name='name']").val(data.user.name);
            var ids = "input[name='sex'][value='" + data.user.sex + "']"
            $(ids).attr("checked",true);
            $("input[name='pass']").val(data.pass);
            $("#rePass").val(data.pass);
            $("input[name='tel']").val(data.user.tel);
            $("input[name='birthDay']").val(data.user.birthDay);
        },
        dataType: "json"
    });
}

function updateMySelf() {
    $.ajax({
        type: "post",
        url: basePath + "/sys/user/my",
        data: $("#mySelfForm").serializeArray(),
        success: function (data) {
            if (data.code == 1) {
                alert(data.info);
                window.top.location.href = basePath + "/main.html";
            } else {
                alert(data.info);
                window.location.reload();
            }
        },
        dataType: "json"
    });
}
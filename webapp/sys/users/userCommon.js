/**
 * 获取部门
 */
function getDept() {
    $.ajax({
        type: "post",
        async: false,
        url: basePath + "/sys/user/gDe",
        success: function (data) {
            var deptList = data;
            for (var i = 0; i < deptList.length; i++) {
                $("#dept").append("<option value='" + deptList[i].no + "'>" + deptList[i].name + "</option>");
            }
        },
        dataType: "json"
    });
}

/**
 * 获取职位
 */
function getPosition() {
    var deptNo = $("#dept").val();
    $("input[name='no']").val("");
    $.ajax({
        type: "post",
        async: false,
        url: basePath + "/sys/user/gPo",
        data: "deptNo=" + deptNo,
        success: function (data) {
            var positionList = data;
            $("#position").html("<option style=\"display:none;\" disabled selected>请选择职位</option>");
            if (positionList.length == 0) {
                if (confirm("当前部门没有职位，是否前往职位添加？")) {
                    window.location.href = basePath + "/sys/position/positionAdd.html";
                }
            }
            for (var i = 0; i < positionList.length; i++) {
                    $("#position").append("<option value='" + positionList[i].no + "'>" + positionList[i].name + "</option>");
            }
        },
        dataType: "json"
    });
}

/**
 * 获取员工编号
 */
function getUserNo() {
    var positionNo = $("#position").val();
    $.ajax({
        type: "post",
        url: basePath + "/sys/user/gUNo",
        data: "positionNo=" + positionNo,
        success: function (data) {
            $("input[name='no']").val(data);
        },
    });
}

/**
 * 检查密码
 */
function checkPass() {
    var pass = $("input[name='pass']").val();
    var rePass = $("input[name='rePass']").val();
    if (rePass != null && rePass != "") {
        if (pass != rePass) {
            $("#rePass").removeClass("ok").addClass("err");
            $("#rePass").text("两次密码不一致，请重新输入");
        } else {
            $("#rePass").removeClass("err").addClass("ok");
            $("#rePass").text("两次密码一致");
        }
    }
}

/**
 * 检查是否可以提交
 */
function checkSubmit() {
    //TODO 第一个TODO
}
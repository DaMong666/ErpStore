function getDeptPo() {
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

function getMgr(no) {
    var deptNo = $("#dept").val();
    $.ajax({
        type: "post",
        async: false,
        url: basePath + "/sys/position/qMgr",
        data: "deptNo=" + deptNo,
        success: function (data) {
            var mgrList = data;
            $("#mgr").html("<option value=''>请选择上级职位</option>");
            for (var i = 0; i < mgrList.length; i++) {
                $("#mgr").append("<option value='" + mgrList[i].no + "'>" + mgrList[i].name + "</option>");
            }
            //TODO 选中上级
            // alert(no);
            $("#mgr").val(no);
        },
        dataType: "json"
    });
}

function getPositionNo() {
    var deptNo = $("#dept").val();
    $.ajax({
        type: "post",
        url: basePath + "/sys/position/qPNo",
        data: "deptNo=" + deptNo,
        success: function (data) {
            $("input[name='no']").val(data);
        },
    });
}
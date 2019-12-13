$(function () {
    getDeptPo();
    getPositionByNo();
});

function getPositionByNo() {
    var no = getParameter("no");
    $.ajax({
        type: "post",
        url: basePath + "/sys/position/qPBNo",
        async: false,
        data: "id=" + no,
        success: function (data) {
            $("input[name='no']").val(data.no);
            $("input[name='name']").val(data.name);
            $("#dept").val(data.deptNo.no);
            if (data.mgr != null) {
                getMgr(data.mgr.no);
            } else {
                getMgr();
            }
        },
        dataType: "json"
    });
}

function positionUpdate() {
    $.ajax({
        type: "post",
        url: basePath + "/sys/position/updPo",
        data: $("#positionUpdateForm").serializeArray(),
        success: function (data) {
            if (data.code == 1) {
                alert(data.info);
                window.location.href = "positionList.html";
            } else {
                alert(data.info);
                window.location.reload();
            }
        },
        dataType: "json"
    });
}
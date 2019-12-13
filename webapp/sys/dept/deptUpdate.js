$(function () {
    getDeptByNo();

});

function getDeptByNo() {
    var no = getParameter("no");
    $.ajax({
        type: "post",
        url: basePath + "/sys/dept/qDBNo",
        data: "id=" + no,
        success: function (data) {
            $("input[name='no']").val(data.no);
            $("input[name='name']").val(data.name);
        },
        dataType: "json"
    });
}

function deptUpdate() {
    $.ajax({
        type: "post",
        url: basePath + "/sys/dept/uDe",
        data: $("#deptUpdateForm").serializeArray(),
        success: function (data) {
            if (data.code == 1) {
                alert(data.info);
                window.location.href = "deptList.html";
            } else {
                alert(data.info);
                window.location.reload();
            }
        },
        dataType: "json"
    });
}
$(function () {
    getDeptNo();

});

function getDeptNo() {
    $.ajax({
        type : "post",
        url : basePath + "/sys/dept/qDNo",
        success:function (data) {
            $("input[name='no']").val(data);
        }
    });
}

function deptAdd() {
    $.ajax({
        type : "post",
        url : basePath + "/sys/dept/aDe",
        data: $("#deptAddForm").serializeArray(),
        success:function (data) {
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
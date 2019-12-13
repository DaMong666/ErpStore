$(function () {
    getDeptPo();
});

function positionAdd() {
    $.ajax({
        type:"post",
        url:basePath + "/sys/position/addPo",
        data:$("#positionAddForm").serializeArray(),
        success:function (data) {
            if (data.code == 1) {
                alert(data.info);
                window.location.href = "positionList.html";
            } else {
                alert(data.info);
                window.location.reload();
            }
        },
        dataType:"json"
    });
}
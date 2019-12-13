$(function () {
    getParentModule();

});

function moduleAdd() {
    $.ajax({
        type:"post",
        url:basePath + "/sys/module/addMo",
        data:$("#moduleAddForm").serializeArray(),
        success:function (data) {
            if (data.code == 1) {
                alert(data.info);
                window.location.href = "moduleList.html";
            } else {
                alert(data.info);
                window.location.reload();
            }
        },
        dataType:"json"
    });
}
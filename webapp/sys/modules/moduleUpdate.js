$(function () {
    getParentModule();
    getModuleById();
    
});

function getModuleById() {
    var id = getParameter("id");
    $.ajax({
        type: "post",
        async: false,
        url: basePath + "/sys/module/qMBNo",
        data: "id=" + id,
        success: function (data) {
            $("input[name='name']").val(data.name);
            $("input[name='id']").val(data.id);
            $("input[name='url']").val(data.url);
            if (data.parent != null) {
                $("#parent").val(data.parent.id);
            }
        },
        dataType: "json"
    });
}

function moduleUpdate() {
    $.ajax({
        type: "post",
        url: basePath + "/sys/module/updMo",
        data: $("#moduleUpdateForm").serializeArray(),
        success: function (data) {
            if (data.code == 1) {
                alert(data.info);
                window.location.href = "moduleList.html";
            } else {
                alert(data.info);
                window.location.reload();
            }
        },
        dataType: "json"
    });
}
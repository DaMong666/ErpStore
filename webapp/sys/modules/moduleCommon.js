function getParentModule() {
    $.ajax({
        type: "post",
        async: false,
        url: basePath + "/sys/module/qParent",
        success: function (data) {
            var parentList = data;
            for (var i = 0; i < parentList.length; i++) {
                $("#parent").append("<option value='" + parentList[i].id + "'>" + parentList[i].name + "</option>");
            }
        },
        dataType: "json"
    });
}
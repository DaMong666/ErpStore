$(function () {
    var id = getParameter("id");
    $.ajax({
        type: "post",
        url: basePath + "/sys/log/qLBId",
        data: "id=" + id,
        success:function (data) {
            $("#userNo").text(data.userId.no);
            $("#userName").text(data.userId.name);
            $("#moduleName").text(data.moduleName);
            $("#content").text(data.content);
            $("#createTime").text(data.createTime);
        },
        dataType: "json"
    });

});
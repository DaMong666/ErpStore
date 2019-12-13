var page;
$(function () {
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            logListPage(currPage);
        }
    });
    logListPage(1);
});

function logListPage(pno) {
    $("input[name='pageNo']").val(pno);
    $.ajax({
        type:"post",
        url:basePath+"/sys/log/qPage",
        async: false,
        data:$("#logListForm").serializeArray(),
        success:function (data) {
            page = data;
            var logList = page.resultList;
            var tbody = "";
            for (var i = 0; i < logList.length; i++) {
                tbody += "<tr>" +
                            "<td>" + parseInt((page.pageNo-1) * page.pageSize + i + 1) + "</td>" +
                            "<td>" + logList[i].userId.no + "</td>" +
                            "<td>" + logList[i].userId.name + "</td>" +
                            "<td>" + logList[i].moduleName + "</td>" +
                            "<td>" + logList[i].content + "</td>" +
                            "<td>" + logList[i].createTime + "</td>" +
                            "<td><a href='logView.html?id=" + logList[i].id + "' class='tablelink'>查看日志信息</a></td>" +
                         "</tr>";
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType:"json"
    });
}
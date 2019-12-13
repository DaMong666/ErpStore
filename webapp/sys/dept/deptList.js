var page;
$(function () {
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            deptListPage(currPage);
        }
    });
    deptListPage(1);
});

function deptListPage(pno) {
    $("input[name='pageNo']").val(pno);
    $.ajax({
        type:"post",
        url:basePath+"/sys/dept/qPage",
        async: false,
        data:$("#deptListForm").serializeArray(),
        success:function (data) {
            page = data;
            var deptList = page.resultList;
            var tbody = "";
            for (var i = 0; i < deptList.length; i++) {
                var status = deptList[i].status;
                var info = "";
                var deStatus = "";
                if (status == 1) {
                    status = "正常";
                    info = "是否确认注销此条信息？";
                    deStatus = "注销";
                } else {
                    status = "不正常";
                    info = "是否确认恢复此条信息？";
                    deStatus = "恢复";
                }
                tbody += "<tr>" +
                    "<td>" + parseInt((page.pageNo-1) * page.pageSize + i + 1) + "</td>" +
                    "<td>" + deptList[i].no + "</td>" +
                    "<td>" + deptList[i].name + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td>" +
                        "<a href='deptUpdate.html?no="+deptList[i].no+"' class='tablelink'>修改</a>" +
                        "   <a href='javascript:void(0)' class='tablelink' onclick='tipOpen(\"" + info + "\");setTipValue(" + deptList[i].no + "," + deptList[i].status + ") '>" + deStatus + "</a></td>" +
                    "</tr>"
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType:"json"
    });
}
var page;
$(function () {
    getDeptPo();
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            positionListPage(currPage);
        }
    });
    positionListPage(1);
});

function positionListPage(pno) {
    $("input[name='pageNo']").val(pno);
    $.ajax({
        type: "post",
        url: basePath + "/sys/position/qPage",
        async: false,
        data: $("#positionListForm").serializeArray(),
        success: function (data) {
            page = data;
            var positionList = page.resultList;
            var tbody = "";
            for (var i = 0; i < positionList.length; i++) {
                var status = positionList[i].status;
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
                var mgr = "";
                if (positionList[i].mgr != null) {
                    mgr = positionList[i].mgr.name;
                }
                tbody += "<tr>" +
                    "<td>" + parseInt((page.pageNo - 1) * page.pageSize + i + 1) + "</td>" +
                    "<td>" + positionList[i].no + "</td>" +
                    "<td>" + positionList[i].name + "</td>" +
                    "<td>" + positionList[i].deptNo.name + "</td>" +
                    "<td>" + mgr + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td><a href='positionUpdate.html?no="+positionList[i].no+"' class='tablelink'>修改</a>" +
                    "   <a href='positionGrant.html?no="+positionList[i].no+"' class='tablelink'>赋权</a>\n" +
                    "   <a href='javascript:void(0)' class='tablelink' onclick='tipOpen(\""+info+"\");setTipValue(" + parseInt(positionList[i].no) + "," + positionList[i].status + ") '>"+deStatus+"</a></td>" +
                    "</tr>"
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType: "json"
    });
}
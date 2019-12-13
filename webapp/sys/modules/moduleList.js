var page;
$(function () {

    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            moduleListPage(currPage);
        }
    });
    moduleListPage(1);
});

function moduleListPage(pno) {
    $("input[name='pageNo']").val(pno);
    $.ajax({
        type: "post",
        url: basePath + "/sys/module/qPage",
        async: false,
        data: $("#moduleListForm").serializeArray(),
        success: function (data) {
            page = data;
            var moduleList = page.resultList;
            var tbody = "";
            for (var i = 0; i < moduleList.length; i++) {
                var status = moduleList[i].status;
                var info = "";
                var deStatus = "";
                if (status == 1) {
                    status = "正常";
                    info = "是否确认禁用此条信息？";
                    deStatus = "禁用";
                } else {
                    status = "不正常";
                    info = "是否确认启用此条信息？";
                    deStatus = "启用";
                }
                var parentName = "无";
                if (moduleList[i].parent != null) {
                    parentName = moduleList[i].parent.name;
                }
                tbody += "<tr>" +
                    "<td>" + parseInt((page.pageNo - 1) * page.pageSize + i + 1) + "</td>" +
                    "<td>" + moduleList[i].name + "</td>" +
                    "<td>" + parentName + "</td>" +
                    "<td>" + moduleList[i].url + "</td>" +
                    "<td>" + status + "</td>" +
                    "<td>" +
                    "    <a href='moduleUpdate.html?id="+moduleList[i].id+"' class='tablelink'>修改</a>" +
                    "    <a href='javascript:void(0);' class='tablelink' onclick='tipOpen(\""+info+"\");setTipValue(" + moduleList[i].id + "," + moduleList[i].status + ") '>"+deStatus+"</a></td>" +
                    "</td>" +
                    "</tr>";
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType: "json"
    });
}




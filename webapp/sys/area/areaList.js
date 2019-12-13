var page;
$(function () {
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            areaListPage(currPage);
        }
    });
    areaListPage(1);
});

function areaListPage(pno) {
    $("input[name='pageNo']").val(pno);
    $.ajax({
        type:"post",
        url:basePath+"/sys/area/qPage",
        async: false,
        data:$("#areaListForm").serializeArray(),
        success:function (data) {
            page = data;
            var areaList = page.resultList;
            var tbody = "";
            for (var i = 0; i < areaList.length; i++) {
                tbody += "<tr>" +
                    "<td>" + parseInt((page.pageNo-1) * page.pageSize + i + 1) + "</td>" +
                    "<td>" + areaList[i].proId.proId + "</td>" +
                    "<td>" + areaList[i].proId.name + "</td>" +
                    "<td>" + areaList[i].cityId + "</td>" +
                    "<td>" + areaList[i].name + "</td>" +
                    "</tr>"
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType:"json"
    });
}

var page;   //分页对象
$(function () {
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            inventoryPage(currPage);
        }
    });
    inventoryPage(1);
});

function inventoryPage(pno) {
    $("input[name='pageNo']").val(pno);
    $.ajax({
        type:"post",
        url:basePath+"/storage/inventory/inventorypage",
        async: false,
        data:$("#form").serializeArray(),
        success:function (data) {
            page = data;
            var inventoryList = page.resultList;
            var tbody = "";
            for (var i = 0; i < inventoryList.length; i++) {
                var storageName = inventoryList[i].storage.name;
                var brandName = inventoryList[i].goods.goodsType.brand.name;
                var typeName = inventoryList[i].goods.goodsType.name;
                var goodName = inventoryList[i].goods.model;
                var vendorName = inventoryList[i].vendor.name;
                var goodNum = inventoryList[i].num;
                var unit = inventoryList[i].goods.unit;
                tbody += "<tr>" +
                    "<td>"+ parseInt((page.pageNo-1) * page.pageSize + i + 1)+ "</td>" +
                    "<td>" + storageName + "</td>" +
                    "<td>" + brandName + "</td>" +
                    "<td>" + typeName + "</td>" +
                    "<td>" + goodName + "</td>" +
                    "<td>" + vendorName + "</td>" +
                    "<td>" + goodNum + "</td>" +
                    "<td>" + unit + "</td>" +
                    "</tr>"
            }
            $("table tbody").html(tbody);
            $('.tablelist tbody tr:odd').addClass('odd');
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType:"json"
    });
}



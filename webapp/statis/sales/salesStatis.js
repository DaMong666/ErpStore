var page;

$(function () {
    //省份下拉框
    province();

    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            salesByPage(currPage);
        }
    });

    //营销统计分页
    salesByPage(1);
});

//省份下拉框
function province() {
    $.ajax({
        type: "post",
        url: basePath + "/storage/storage/province",
        success: function (data) {
            var html = "<option value=''>请选择省份</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].proId + "'>" + data[i].name + "</option>";
            }
            $("#provinceId").html(html);
        },
        dataType: "json"
    });
}

//城市下拉框
function loadCity() {
    var provinceId = $("#provinceId").val();
    $.ajax({
        type: "post",
        url: basePath + "/storage/storage/city",
        data: "provinceId=" + provinceId,
        success: function (data) {
            var html = "<option value=''>请选择城市</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].cityId + "'>" + data[i].name + "</option>";
            }
            $("#cityId").html(html);
        },
        dataType: "json"
    });
}

function salesByPage(pno) {
    $("input[name='pageNo']").val(pno);
    var param = $("#salesForm").serialize();
    $.ajax({
        type:"post",
        url:basePath + "/statis/sales/asyncpage",
        data:param,
        async:false,
        success:function (data) {
            page = data;
            var salesList = page.resultList;
            var html = "";
            for (var i = 0; i < salesList.length; i++) {
                html += "<tr>" +
                    "<td>"+parseInt((page.pageNo-1) * page.pageSize + i + 1)+"</td>" +
                    "<td>"+salesList[i].cusname.name+"</td>"+
                    "<td>"+salesList[i].cusname.city.proId.name+salesList[i].cusname.city.name+"</td>"+
                    "<td>"+salesList[i].count+"</td>"+
                    "<td>"+salesList[i].summoney+"</td>"+
                    "<td><a href='salesView.html?id="+salesList[i].cusname.id+"' class=\"tablelink\">查看详情</a></td>"+
                    "</tr>";
            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        },
        dataType:"json"
    });
}


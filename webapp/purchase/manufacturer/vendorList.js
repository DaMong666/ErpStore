var page;//分页对象

$(function () {
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            vendorPage(currPage);
        }
    });
    vendorPage(null);
    province();
});

//分页查询
function vendorPage(pno) {
    $("input[name='pageNo']").val(pno);
    var param=$("#form").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/manufacturer/vpage",
        async:false,
        data:param,
        success:function (data) {
            page=data;
            var vendorList=page.resultList;
            var html="";
            for (var i = 0; i < vendorList.length; i++) {
                var status = vendorList[i].status;
                var info = "";
                var deStatus = "";
                if (status == 1) {
                    status = "恢复";
                    info = "是否确认注销此厂商？";
                    deStatus = "注销";
                } else {
                    status = "注销";
                    info = "是否确认恢复此厂商？";
                    deStatus = "恢复";
                }

                html+="<tr>"
                    +"<td>"+parseInt((page.pageNo-1) * page.pageSize + i + 1)+"</td>"
                    +"<td>"+vendorList[i].name+"</td>"
                    +"<td>"+vendorList[i].principal+"</td>"
                    +"<td>"+vendorList[i].tel+"</td>"
                    +"<td>"+vendorList[i].city.proId.name+vendorList[i].city.name+"</td>";
                if(vendorList[i].status == 1){
                    html += "<td>可用</td>";
                }else {
                    html += "<td>不可用</td>";
                }
                if(vendorList[i].createTime !=null){
                    html += "<td>"+vendorList[i].createTime+"</td>";
                }else {
                    html += "<td></td>";
                }
                if(vendorList[i].createUser.name !=null){
                    html += "<td>"+vendorList[i].createUser.name+"</td>";
                }else {
                    html += "<td></td>";
                }
                html+=  "<td><a href='manufacturerView.html?id="+vendorList[i].id+"' class='tablelink'>查看详情</a>";
                if(vendorList[i].status == 1){
                    html += "    <a href='manufacturerUpdate.html?id="+vendorList[i].id+"' class='tablelink'>修改</a>";
                }
                html += "    <a href='javascript:;' class='tablelink' " +
                    "onclick='tipOpen(\""+info+"\");setTipValue("+vendorList[i].id+","+vendorList[i].status+")'>"+deStatus+"</a>";


                html +="</td></tr>";

            }
            $(".tablelist tbody").html(html);
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
            $('.tablelist tbody tr:odd').addClass('odd');
        },
        dataType:"json"
    });
}


//省份下拉框
function province() {
    $.ajax({
        type:"post",
        url:basePath +"/purchase/manufacturer/province",
        success:function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].proId+"'>"+data[i].name+"</option>";
            }
            $("#provinceId").html(html);
        },
        dataType:"json"
    });
}
//城市下拉框
function loadCity() {
    var provinceId=$("#provinceId").val();
    $.ajax({
        type:"post",
        url:basePath +"/purchase/manufacturer/city",
        data:"provinceId="+provinceId,
        success:function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].cityId+"'>"+data[i].name+"</option>";
            }
            $("#cityId").html(html);
        },
        dataType:"json"
    });
}
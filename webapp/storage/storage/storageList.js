/*初始化函数  $();*/
var page;//分页改成全局使用便捷  初始化函数后被赋值
$(function () {
    //省份
    province();//下面写，调用
    //分页查询
    $("#pagination_5").whjPaging({
        isShowPageSizeOpt: false,
        isResetPage: true,
        callBack: function (currPage, pageSize) {
            storagePage(currPage);
        }
    });
    storagePage(1);

    /*//分页按钮
    jumpPage();*/

});
//省份下拉框
function province() {
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/province",
        success:function (data) {
            var html = "<option value='0'>请选择</option>";
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
        url:basePath +"/storage/storage/city",
        data:"provinceId="+provinceId,
        success:function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].cityId+"'>"+data[i].name+"</option>";
            }
            $("#cityId").html(html);
        },
        dataType:"json"
    });
}

//分页模糊查询员工信息
function storagePage(pno) {
    $("input[name='pageNo']").val(pno);
    var param = $("#form").serialize();//jquery获取表单参数成字符串
    $.ajax({
        type:"post",
        url:basePath+"/storage/storage/storagepage",//basePath获取根路径
        async: false,
        data:param,
        success:function (data) {
            page = data;//page对象
            var storageList = page.resultList;//获取结果集
            var html = "";
            for (var i = 0; i < storageList.length; i++) {
                html += "<tr>"
                    +"<td>"+(i+1)+"</td>"
                    +"<td>"+storageList[i].name+"</td>";
                if(storageList[i].principalPerson != null){
                    html +="<td>"+storageList[i].principalPerson.name+"</td>";
                }else{
                    html += "<td></td>";
                }
                html += "<td>"+storageList[i].tel+"</td>";
                if(storageList[i].city == null){
                    html += "<td></td>";
                }else{
                    html +="<td>"+storageList[i].city.proId.name+storageList[i].city.name+"</td>";
                }
                var status = storageList[i].status;
                var info = "";
                if(storageList[i].status == 1){
                    html += "<td>可用</td>";
                    info = "是否确认注销此条信息?";
                }else {
                    html += "<td>不可用</td>";
                    info = "是否确认恢复此条信息?";
                }
                html += "<td>"+storageList[i].createTime+"</td>";
                if(storageList[i].createPerson != null){
                    html +="<td>"+storageList[i].createPerson.name+"</td>";
                }else{
                    html += "<td></td>";
                }
                html += "<td>" +
                    "<a href='storageView.html?id="+storageList[i].id+"'>查看详情</a>&nbsp;&nbsp;" +
                    "<a href='storageUpdate.html?id="+storageList[i].id+"'>修改</a>&nbsp;&nbsp;";
                if(storageList[i].status == 1){
                    html += "<a href='javascript:void(0)' class='tablelink' " +
                        "onclick='tipOpen(\"" + info + "\");setTipValue(" + storageList[i].id + "," + status + ") '>注销</a>";
                }else {
                    html += "<a href='javascript:void(0)' class='tablelink' " +
                        "onclick='tipOpen(\"" + info + "\");setTipValue(" + storageList[i].id + "," + status + ") '>恢复</a>";
                }
                html += "</td>";
                html += "</tr>";
            }
            $(".tablelist tbody").html(html);//$(".tablelist tbody")后代选择器
            /*页面行不同颜色*/
            $(".tablelist tbody tr:odd").addClass("odd");
            //分页信息
            /*$(".pageNo").val(page.pageNo);//当前页
            $("#pageNo").html(page.pageNo);//当前页
            $("#rowCount").html(page.rowCount);//总记录数
            $("#pageCount").html(page.pageCount);//总页面数*/
            $("#pagination_5").whjPaging("setPage", page.pageNo,page.pageCount);
        } ,
        dataType:"json"
    });

}

/**
 * 条件搜索
 * 当搜索的时候把当前页强制跳到第一页
 */
/*
function search() {
    $(".pageNo").val(1);
    storagePage();
}

function go() {
    var pageNo = $(".pageNo").val();
    if(pageNo > page.lastPage){
        $("#pageNo").val(page.lastPage);
    }
    storagePage();
}
*/

/**
 * 跳转页面  上一页 下一页  首页 尾页
 */
/*function jumpPage() {//.click单击事件
    $(".firstPage").click(function () {
        $(".pageNo").val(1);
        storagePage();
    })
    $(".prePage").click(function () {
        $(".pageNo").val(page.prePage);
        storagePage();
    })
    $(".nextPage").click(function () {
        $(".pageNo").val(page.nextPage);
        storagePage();
    })
    $(".lastPage").click(function () {
        $(".pageNo").val(page.lastPage);
        storagePage();
    })
}*/
/*注销仓库*/
/*function cancel(id) {
    var result = confirm("您确定要删除吗？");
    if(result){
        $.ajax({
            type:"post",
            url:basePath+"",
            data:"storageId="+id,
            success:function (data) {
                alert(data);
                // empPage();
                // mgr();
                /!*window.location.href=basePath + "/emp/empList.html";*!/
            }
        });
    }

}*/
/*
/!*判断是否是财务部门经理，是有权限添加*!/
function isMgr() {
    $.ajax({
        type:"post",
        url:basePath+"/storage/storage/ismgr",
        success:function (data) {
            alert(data);
            // empPage();
            // mgr();
            /!*window.location.href=basePath + "/emp/empList.html";*!/
        }
    })
}*/

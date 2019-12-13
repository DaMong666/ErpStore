$(function () {
    //获取通过审核的订单
    getPurchase();
    //获取符合要求的仓库
    getStorage();
    //获取当前时间
    getCurrTime();
});
//获取通过审核的订单
function getPurchase(){
    $.ajax({
        type:"post",
        url:basePath +"/storage/instorage/getpurchasestatus2",
        success:function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].purchaseNo+"'>"+data[i].purchaseNo+"</option>";
            }
            $("#purchaseNo2").html(html);
        },
        dataType:"json"
    });
}
//获取状态可用得仓库
function getStorage() {
    $.ajax({
        type:"post",
        url:basePath +"/storage/instorage/getallstorage",
        success:function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
            }
            $(".dfselect").html(html);
        },
        dataType:"json"
    });
}
//获取当前登录对象
function loadInstoPerson() {
    var storageId=$(".dfselect").val();
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/toview",
        data:"storageId="+storageId,
        success:function (data) {
            $("#instoPerson").val(data.principalPerson.name);
        },
        dataType:"json"
    });
}
//获取当前时间
function getCurrTime() {
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/getcurrtime",
        success:function (data) {
            $("input[name=createTime]").val(data);
        },
    });
}
/*实时查询通过审核的订单号*/
function showPurchaseNo() {
    $(".show_div").css("display","block");
    var purchaseNo = $("input[name=purchaseNo]").val();
    /*if(purchaseNo = null){
        purchaseNo = "CG";
    }*/
    $.ajax({
        type:"POST",
        url:basePath+"/storage/instorage/getPurchaseNo",
        data:"purchaseNo="+purchaseNo,
        success:function (data) {
            var html="";
            for (var i = 0; i < data.length; i++) {
                html+="<p>"+data[i]+"</p>"
            }
            $(".show_div").html(html)
        }
    });
}
function disappear() {
    $(".show_div").css("display","none");
}
/*添加入库*/
function addInStorage() {
    var param = $("#form").serialize();
    $.ajax({
        type:"post",
        url:basePath+"/storage/instorage/addinstorage",
        data:param,
        success:function (data) {
            alert(data)
            window.location.href=basePath + "/storage/stock/stockAdd.html";
        },
    });
}
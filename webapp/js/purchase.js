function deltipOpen() {
    $("#deltip").fadeIn(200);
}
function deltipClose() {
    $("#deltip").fadeOut(200);
}
function examinetipOpen() {
    $("#examinetip").fadeIn(200);
    /*$.ajax({
        type:"post",
        url:basePath + "/purchase/purchase/mgr",
        success:function (data) {
            for (var i = 0; i <data.length; i++) {
                    html+="<option value='"+data[i].no+"'>"+data[i].name+"</option>"
            }
        }
    });*/
}
function examinetipclose() {
    $("#examinetip").fadeOut(200);
}

$(function () {
    Mgr();
});
//根据登陆用户获取上级
function Mgr() {
    $.ajax({
        type:"post",
        url:basePath +"/purchase/purchase/mgr",
        success:function (data) {
            var html="";
            for (var i = 0; i <data.length; i++) {
                    html+="<option value='"+data[i].no+"'>"+data[i].positionNo.name+"</option>"
            }
            $("#position").html(html);
        },
        dataType:"json"
    });
}

function loadName() {
    var no=$("#position").val();
    $.ajax({
        type:"post",
        url:basePath +"/purchase/purchase/name",
        data:"No="+no,
        success:function (data) {
            var html ="";
            for (var i = 0; i < data.length; i++) {
                    html += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
            }

            $("#pname").html(html);
        },
        dataType:"json"
    });
}

/**
 * 删除采购单
 */
function deletePurchase(url) {
    var purchaseNo = $("input[name='purchaNo']").val();
    var deleteStatus = $("input[name='deleteStatus']").val();
    if (deleteStatus == 0) {
        $.ajax({
            type:"post",
            url:basePath + url +"/delete",
            data:"purchaseNo=" + purchaseNo,
            success:function (data) {
                alert(data);
                window.location.reload();
            }
        });
    }
}
function commitExamine() {
    var purchaseNo = $("input[name='purchaNo']").val();
    var deleteStatus = $("input[name='deleteStatus']").val();
    var checkUser=$("#pname").val();
    $("#hiddenNo").val(purchaseNo);
    $("#hiddenStatus").val(deleteStatus);
    var param=$("#examineForm").serializeArray();
    param+="&purchaseNo="+purchaseNo;
    param+="&status="+deleteStatus;
    param+="&checkUser.id="+checkUser
    if (deleteStatus == 0||deleteStatus==3) {
        $.ajax({
            type:"post",
            url:basePath + "/purchase/purchase/updateExamine",
            data:param,
            success:function (data) {
                alert(data);
                window.location.reload();
            }
        });
    }
}

function setValue(no,status) {
    $("input[name='purchaNo']").val(no);
    $("input[name='deleteStatus']").val(status);
}
/**
 * 获取项目的根路径
 * @returns
 * 2018年5月9日
 * @author   yup
 */
var basePath = getRootPath();
function getRootPath(){  
    var pathName=window.document.location.pathname;  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
    return projectName;  
}

/**
 * 获取参数值
 * @param name
 * @returns
 * 2018年5月9日
 * @author   yup
 */
function getParameter(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 匹配目标参数
	var result = window.location.search.substr(1).match(reg); // 对querystring匹配目标参数
	if (result != null) {
		return decodeURIComponent(result[2]);
	} else {
		return null;
	}
}

/**
 * 改变状态
 */
function changeStatus(url) {
	var changeNo = $("input[name='changeNo']").val();
	var changeStatus = $("input[name='changeStatus']").val();
	if (changeStatus == 1) {
		$.ajax({
			type:"post",
			url:basePath + url +"/can",
			data:"id=" + changeNo,
			success:function (data) {
				if (data == 1) {
					alert("修改成功");
				} else {
					alert("修改失败");
				}
				window.location.reload();
			}
		});
	} else if (changeStatus == 0) {
		$.ajax({
			type:"post",
			url:basePath + url + "/rec",
			data:"id=" + changeNo,
			success:function (data) {
				if (data == 1) {
					alert("修改成功");
				} else {
					alert("修改失败");
				}
				window.location.reload();
			}
		});
	} else {
		$.ajax({
			type:"post",
			url:basePath + "/sys/user/reset",
			data:"id=" + changeNo,
			success:function (data) {
				if (data.code == 1) {
					alert(data.info);
				} else if (data.code == 2) {
					alert(data.info);
					window.top.location.href = basePath + "/login.html"
				} else {
					alert(data.info);
				}
				window.location.reload();
			},
			dataType: "json"
		});
	}

}
/*入库操作改变状态*/
function inStoStatus(url) {
    var changeNo = $("input[name='changeNo']").val();
    var changeStatus = $("input[name='changeStatus']").val();
    if (changeStatus == 1) {
        $.ajax({
            type:"post",
            url:basePath + url +"/insto",
            data:"id=" + changeNo,
            success:function (data) {
                if (data == 1) {
                    alert("入库成功");
                } else {
                    alert("入库失败");
                }
                window.location.reload();
            }
        });
    } else if (changeStatus == 2) {
        $.ajax({
            type:"post",
            url:basePath + url + "/outsto",
            data:"id=" + changeNo,
            success:function (data) {
                if (data == 1) {
                    alert("取消入库成功");
                } else {
                    alert("取消入库失败");
                }
                window.location.reload();
            }
        });
    } else {
        $.ajax({
            type:"post",
            url:basePath + "/sys/user/reset",
            data:"id=" + changeNo,
            success:function (data) {
                if (data.code == 1) {
                    alert(data.info);
                } else if (data.code == 2) {
                    alert(data.info);
                    window.top.location.href = basePath + "/login.html"
                } else {
                    alert(data.info);
                }
                window.location.reload();
            },
            dataType: "json"
        });
    }
}
/*出库操作改变状态*/
function outStoStatus(url) {
    var changeNo = $("input[name='changeNo']").val();
    var changeStatus = $("input[name='changeStatus']").val();//订单状态
    var toStatus = $("input[name='action']").val();//要更改的状态
    if (changeStatus == 1&&toStatus==2) {
        $.ajax({
            type:"post",
            url:basePath + url +"/fahuo",
            data:"id=" + changeNo,
            success:function (data) {
                if (data == 1) {
                    alert("发货成功");
                } else {
                    alert("发货失败，库存不足，请添加库存！");
                }
                window.location.reload();
            }
        });
    }else if(changeStatus == 1&&toStatus==5){
        $.ajax({
            type:"post",
            url:basePath + url +"/weifahuoquxiaodingdan",
            data:"id=" + changeNo,
            success:function (data) {
                if (data == 1) {
                    alert("取消订单成功！");
                } else {
                    alert("取消订单失败！");
                }
                window.location.reload();
            }
        });
    } else if (changeStatus == 2&&toStatus==4) {
        $.ajax({
            type:"post",
            url:basePath + url + "/yifahuoquxiaodingdan",
            data:"id=" + changeNo,
            success:function (data) {
                if (data == 1) {
                    alert("取消订单成功");
                } else {
                    alert("取消订单失败");
                }
                window.location.reload();
            }
        });
    }else if (changeStatus == 2&&toStatus==3) {//确认回款
        $.ajax({
            type:"post",
            url:basePath + url + "/yifahuotoquerenhuikuan",
            data:"id=" + changeNo,
            success:function (data) {
                if (data == 1) {
                    alert("回款成功");
                } else {
                    alert("回款失败");
                }
                window.location.reload();
            }
        });
    } else if (changeStatus == 4&&toStatus==5) {
        $.ajax({
            type:"post",
            url:basePath + url + "/qurenyituihuo",
            data:"id=" + changeNo,
            success:function (data) {
                if (data == 1) {
                    alert("确认已退货成功");
                } else {
                    alert("确认已退货失败");
                }
                window.location.reload();
            }
        });
    } else {
        $.ajax({
            type:"post",
            url:basePath + "/sys/user/reset",
            data:"id=" + changeNo,
            success:function (data) {
                if (data.code == 1) {
                    alert(data.info);
                } else if (data.code == 2) {
                    alert(data.info);
                    window.top.location.href = basePath + "/login.html"
                } else {
                    alert(data.info);
                }
                window.location.reload();
            },
            dataType: "json"
        });
    }
}
function setTipValue(no,status,tostatus) {
	$("input[name='changeNo']").val(no);
	$("input[name='changeStatus']").val(status);
	$("input[name='action']").val(tostatus);
}

/**
 * 打开提示框(在职离职、注销恢复、禁用启用)
 * @param content
 */
function tipOpen(content) {
	$(".tipright p").text(content);
	$("#tip").fadeIn(200);
}

/**
 * 关闭提示框(在职离职、注销恢复、禁用启用)
 */
function tipClose() {
	$("#tip").fadeOut(200);
}

// /**
//  * 打开删除提示框
//  */
// function deltipOpen() {
// 	$("#deltip").fadeIn(200);
// }
//
// /**
//  * 关闭删除提示框
//  */
// function deltipClose() {
// 	$("#deltip").fadeOut(200);
// }
//
// /**
//  * 打开审核提示框
//  */
// function examinetipOpen() {
// 	$("#examinetip").fadeIn(200);
// }
//
// /**
//  * 关闭审核提示框
//  */
// function examinetipclose() {
// 	$("#examinetip").fadeOut(200);
// }
//
// /**
//  * 打开分配提示框
//  */
// function allottipOpen() {
// 	$("#allottip").fadeIn(200);
// }
//
// /**
//  * 关闭分配提示框
//  */
// function allottipclose() {
// 	$("#allottip").fadeOut(200);
// }




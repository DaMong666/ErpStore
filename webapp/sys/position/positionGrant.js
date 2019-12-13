var parentList;
var childList;
var count = 0;
$(function () {
    getParentModule2();
    getChildModule2();
    var html = "";
    for (var i = 0; i < parentList.length; i++ ) {
        html += "<li>" +
                    "<input type='checkbox' value='"+parentList[i].id+"' name='authList["+ count +"].moduleId.id' />" +
                    "<a href='javascript:void(0);'>" + parentList[i].name + "</a>" +
                    "<ul class='hidden'>";
        var parentId = parentList[i].id;
        count++;
        for (var j = 0; j < childList.length; j++ ) {
            if (childList[j].parent.id == parentId) {
                html += "<li>" +
                            "<input type='checkbox' value='"+childList[j].id+"' name='authList["+ count +"].moduleId.id' onclick=''/>" +
                            "<a href='../modules/moduleView.html?id="+childList[j].id+"'>"+childList[j].name+"</a>" +
                        "</li>";
                count++;
            }
        }
        html +=     "</ul>" +
                "</li>";
    }

    $("#treeMenu").html(html);

    // console.log($(":checkbox").eq(1).val());

    // alert($(":checkbox").length);
    getAuthByNo();
    $("#treeMenu").children("li").children(":checkbox").click(function () {
        var flag = $(this).attr("checked");
        $(this).siblings("ul").show();
        $(this).siblings("ul").children("li").children(":checkbox").attr("checked", flag);
    }).siblings("a").click(function () {
        $(this).siblings("ul").toggle();
    });

});


function getAuthByNo() {
    var no = getParameter("no");
    $("input[name='positionNo']").val(no);
    $.ajax({
        type: "post",
        url: basePath + "/sys/auth/qUpdAuth",
        data: "no=" + no,
        async: false,
        success: function (data) {
            var authList = data;
            for (var i = 0; i < authList.length; i++) {
                var auth = authList[i];
                for (var j = 0; j < $(":checkbox").length; j++) {
                    // console.log($(":checkbox").eq(j).val() + "==" + auth.moduleId.id);
                    if ($(":checkbox").eq(j).val() == auth.moduleId.id) {
                        // console.log("11");
                        $(":checkbox").eq(j).attr("checked",true);
                    }
                }

            }
        },
        dataType: "json"
    });
}

function getParentModule2() {
    $.ajax({
        type: "post",
        url: basePath + "/sys/module/qParent",
        async: false,
        success: function (data) {
            parentList = data;
        },
        dataType: "json"
    });
}

function getChildModule2() {
    $.ajax({
        type: "post",
        url: basePath + "/sys/module/qChild",
        async: false,
        success: function (data) {
            childList = data;
        },
        dataType: "json"
    });
}

function updateAuth() {
    // var parentCheck = $("#treeMenu").children("li").children(":checkbox");
    // console.log(parentCheck);
    // if (!parentCheck.attr("checked")) {
    //     parentCheck.siblings("input:hidden").attr("disabled", true);
    // } else {
    //     parentCheck.siblings("input:hidden").attr("disabled", false);
    // }
    // var childCheck = parentCheck.siblings("a").siblings("ul").children("li").children(":checkbox");
    // console.log(childCheck);
    // if (!childCheck.attr("checked")) {
    //     childCheck.siblings("input:hidden").attr("disabled", true);
    // } else {
    //     childCheck.siblings("input:hidden").attr("disabled", false);
    // }
    //
    //
    // $("#treeMenu").children("li").children(":checkbox").siblings("input:hidden").attr("disabled", !$(this).prev().attr("checked"));
    // $("#treeMenu").children("li").children(":checkbox").siblings("a").siblings("ul")
    //     .children("li").children(":checkbox").siblings("input:hidden").attr("disabled", !$(this).prev().attr("checked"))
    $.ajax({
        type: "post",
        url: basePath + "/sys/auth/updAuth",
        data: $("#positionGrantForm").serializeArray(),
        success: function (data) {
            if (data.code == 1) {
                alert(data.info);
                window.location.href = "positionList.html";
            } else {
                alert(data.info);
                window.location.reload();
            }
        },
        dataType: "json"
    });
}
$(function () {
    getDept();
});




/**
 * 添加员工
 */
function addUser() {
    $.ajax({
        type:"post",
        url:basePath + "/sys/user/ad",
        data:$("#userAddForm").serializeArray(),
        success:function (data) {
            if (data.code == 1) {
                alert(data.info);
                window.location.href = "userList.html";
            } else {
                alert(data.info);
                window.location.reload();
            }
        },
        dataType:"json"
    });
}






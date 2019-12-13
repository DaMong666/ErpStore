$(function () {
    //查看详情
    cusView();
});

function cusView() {
    var id = getParameter("id");
    $.ajax({
        type: "post",
        url: basePath + "/market/customer/cusById",
        data: "id=" + id,
        success: function (data) {
            $("#name").html(data.name);
            $("#company").html(data.company);
            $("#tel").html(data.tel);
            $("#address").html(data.address);
            $("#description").html(data.description);
            $("#createTime").html(data.createTime);
            $("#createUser").html(data.createUser.name);
            $("#city").html(data.city.proId.name + data.city.name);
            if (data.sex == '1') {
                $("#sex").html("男");
            }else if (data.sex == '0') {
                $("#sex").html("女");
            }
            if (data.status == '1') {
                $("#status").html("可用");
            }else if (data.status == '0') {
                $("#status").html("不可用");
            }
        },
        dataType: "json"
    });
}
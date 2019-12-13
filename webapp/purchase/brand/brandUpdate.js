$(function () {
    toUpdate();
});
function toUpdate() {
    var id=getParameter("id");
    $.ajax({
        type:"post",
        url:basePath+"/purchase/brand/btupdate",
        data:"id="+id,
        success:function (data) {
            $("input[name=name]").val(data.name);
            $("select[name=status]").val(data.status);
            $("#createUser").val(data.createUser.name);
            $("input[name=createTime]").val(data.createTime);
        },
        dataType:"json"
    });
}
function update() {
    var  param=$("#updateForm").serialize();
    var id=getParameter("id");
    param+="&id="+id;
    $.ajax({
        type:"post",
        url:basePath+"/purchase/brand/bupdate",
        data:param,
        success:function (data) {
            alert(data);
            window.location.reload();
        }
    });
}
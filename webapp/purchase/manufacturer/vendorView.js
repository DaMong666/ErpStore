$(function () {
    toView();
});
function toView() {
    var id=getParameter("id");
    $.ajax({
        type:"post",
        url:basePath+"/purchase/manufacturer/vtupdate",
        data:"id="+id,
        success:function (data) {
            $(".name").text(data.name);
            $(".principal").text(data.principal);
            $(".tel").text(data.tel);
            $(".address").text(data.address);
            if(data.city != null) {
                $(".area").text(data.city.proId.name+data.city.name);
            }
            $(".address").text(data.address);
            $(".description").text(data.description);
            if (data.status=='1') {
                $(".status").text("可用");
            }else if (data.status=='0') {
                $(".status").text("不可用");
            }

            $(".createUser").text(data.createUser.name);
            $(".createTime").text(data.createTime);
        },
        dataType:"json"
    });
}
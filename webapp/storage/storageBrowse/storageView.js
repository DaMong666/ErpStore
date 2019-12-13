$(function () {
    //仓库信息
    getStorageInfo();
});
//仓库信息
function getStorageInfo() {
    var storageId = getParameter("id");
    $.ajax({
        type:"post",
        url:basePath +"/storage/storage/toview",
        data:"storageId="+storageId,
        success:function (data) {
            $(".name").text(data.name);
            $(".address").text(data.address);
            if(data.city != null){
                $(".city").text(data.city.proId.name+data.city.name);
            }else{
                $(".city").text("");
            }
            if(data.principalPerson != null){
                $(".principalPerson").text(data.principalPerson.name);
            }else{
                $(".principalPerson").text("");
            }
            $(".tel").text(data.tel);
            if(data.description == null){
                $(".description").text("");
            }else{
                $(".description").text(data.description);
            }
            if(data.status == 0){
                $(".status").text("不可用");
            }else{
                $(".status").text("可用");
            }
            if(data.createPerson != null){
                $(".createName").text(data.createPerson.name);
            }
            $(".createTime").text(data.createTime);
        },
        dataType:"json"
    });
}
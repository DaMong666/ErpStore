$(function () {
    brand();
    toUpdate();
});
//获取可用的品牌
function brand() {
    $.ajax({
        type:"post",
        url:basePath+"/purchase/brand/brandList",
        async:false,
        success:function (data) {
            var html="<option value='0'>请选择</option>";
            for (var i = 0; i <data.length; i++) {
                if(data[i].status=='1'){
                    html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>"
                }
            }
            $("#brandId").html(html);
        },
        dataType:"json"
    });
}

/**
 * 到达商品类型的添加页面
 */
function toUpdate() {
    var id=getParameter("id");
    $.ajax({
        type:"post",
        url:basePath+"/purchase/productType/gttupdate",
        data:"id="+id,
        success:function (data) {
            $("#brandId").val(data.brand.id);
            $("input[name=name]").val(data.name);
            $("select[name=status]").val(data.status);
            $("#createUser").val(data.createUser.name);
            $("input[name=createTime]").val(data.createTime);
        },
        dataType:"json"
    });
}
function update() {
    var  param=$("#form").serialize();
    var id=getParameter("id");
    param+="&id="+id;
    $.ajax({
        type:"post",
        url:basePath+"/purchase/productType/gtupdate",
        data:param,
        success:function (data) {
            alert(data);
            window.location.reload();
        }
    });
}
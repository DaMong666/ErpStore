$(function () {
    brand();
    goodsType();
    toUpdate();
});
function toUpdate() {
    var id=getParameter("id");
    $.ajax({
        type:"post",
        url:basePath+"/purchase/product/gtupdate",
        data:"id="+id,
        success:function (data) {
            if(data.goodsType != null) {
                $("#brandId").val(data.goodsType.brand.id);
                $("#goodsTypeId").val(data.goodsType.id);
            }
            $("input[name=model]").val(data.model);
            $("input[name=unit]").val(data.unit);
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
        url:basePath+"/purchase/product/gupdate",
        data:param,
        success:function (data) {
            alert(data);
            window.location.reload();
        }
    });
}
//品牌下拉框
function brand() {
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/brand",
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
//商品类型下拉框
function loadGoodsType() {
    var brandId=$("#brandId").val();
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/goodsType",
        data:"brandId="+brandId,
        success:function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                if(data[i].status=='1') {
                    html += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                }
            }
            $("#goodsTypeId").html(html);
        },
        dataType:"json"
    });
}


//商品类型下拉框
function goodsType() {
    //根据商品id获取到品牌id
    var goodsId = getParameter("id");
    var brandId = "";
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/gtupdate",
        data:"id="+goodsId,
        async:false,//改成同步
        success:function (data) {
            if(data.goodsType != null){
                brandId = data.goodsType.brand.id;
            }
        },
        dataType:"json"
    });
    //通过品牌id查询该品牌可用的商品类型
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/goodsType",
        async:false,//改成同步
        data:"brandId="+brandId,
        success:function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
            }
            $("#goodsTypeId").html(html);
        },
        dataType:"json"
    });
}
var jj=0;

$(function () {
    var purchaseNo=getParameter("purchaseNo");
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchase/count",
        data:"purchaseNo="+purchaseNo,
        async:false,
        success:function (data) {
             // jj=data;
        },
        dataType:"json"
    });
    toUpdate();
    getDetail();
});
function vendor(w) {
    $.ajax({
        type:"post",
        url:basePath+"/purchase/manufacturer/vendorList",
        async:false,
        success:function (data) {
            var html="<option value='0'>请选择</option>";
            for (var i = 0; i <data.length; i++) {
                if(data[i].status=='1'){
                    html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>"
                }
            }
            $("#vendorId"+w+"").html(html);
        },
        dataType:"json"
    });
}
//品牌下拉框
function brand(w) {
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/brand",
        async:false,
        success:function (data) {
            var html="<option value='0'>请选择</option>";
            for (var i = 0; i <data.length; i++) {
                if(data[i].status=='1'){
                    html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>"
                }
            }
            $("#brandId"+w+"").html(html);
        },
        dataType:"json"
    });
}
//商品类型下拉框
function loadGoodsType(index) {
    var brandId=$("#brandId"+index+"").val();
    $("#goodsId"+index+"").val(0);
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/goodsType",
        data:"brandId="+brandId,
        success:function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                if(data[i].status=='1') {
                    html += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                }
            }
            $("#goodsTypeId"+index+"").html(html);
        },
        dataType:"json"
    });
}
//商品型号下拉框
function loadGoods(index) {
    var goodsTypeId=$("#goodsTypeId"+index+"").val();
    $.ajax({
        type:"post",
        url:basePath +"/purchase/purchase/goods",
        data:"goodsTypeId="+goodsTypeId,
        success:function (data) {
            var html = "<option value='0'>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                if(data[i].status=='1') {
                    html += "<option value='" + data[i].id + "'>" + data[i].model + "</option>";
                }
            }
            $("#goodsId"+index+"").html(html);
        },
        dataType:"json"
    });
}


//添加一行明细数据
function addGoods() {
    var html="<tr id='"+(++jj)+"'>" +
        "<input name='purchaseDetailList["+ (jj-1) +"].purchase.purchaseNo' type=\"hidden\" id='no"+jj+"' />"+
        "          <td>"+jj+"</td>" +
        "          <td>" +
        "          <select id='brandId"+jj+"' onchange='loadGoodsType(" + jj +");'required>" +
        "            <option >请选择</option>" +
        "            </select>" +
        "          </td>" +
        "          <td>" +
        "          <select id='goodsTypeId"+jj+"' onchange='loadGoods(" + jj +");' required>" +
        "            <option >请选择</option>" +
        "            </select>" +
        "          </td>" +
        "          <td>" +
        "          <select id='goodsId"+jj+"' name='purchaseDetailList["+ (jj-1) +"].goods.id' required>" +
        "            <option>请选择</option>" +
        "            </select>" +
        "          </td>" +
        "          <td>" +
        "          <select name='purchaseDetailList["+ (jj-1) +"].vendor.id' id='vendorId"+jj+"' required>" +
        "            <option >请选择</option>" +
        "            </select>" +
        "          </td>" +
        "          <td ><input id='num"+jj+"' name='purchaseDetailList["+ (jj-1) +"].num' type=\"text\"required /></td>" +
        "          <td>台</td>" +
        "          <td><input id='price"+jj+"' name='purchaseDetailList["+ (jj-1) +"].price' onblur='accountPrice("+(jj)+");' type=\"text\" required/></td>" +
        "          <td><input id='money"+jj+"' name='purchaseDetailList["+ (jj-1) +"].money' type='text'required /></td>" +
        "        </tr>";
    $("tbody").append(html);
    brand(jj);
    vendor(jj);
}
function deleteGoods() {
    $("#"+jj).remove();
    if(jj>=1){
        jj--;
    }
    // $("tbody tr:last-child").remove();
    var accountMoney=0;
    for(var i=1;i<=jj;i++){
        accountMoney += parseFloat($("#money"+jj).val());
    }
    $("#accountMoney").val(accountMoney);
}
function accountPrice(jj) {
    var price = $("#price"+jj).val();
    var num = $("#num"+jj).val();
    var money = parseFloat(price) * parseInt(num);
    $("#money"+jj).prop("value",money);
    var accountMoney=0;
    for(var i=1;i<=jj;i++){
        accountMoney += parseFloat($("#money"+i).val());
    }
    $("#accountMoney").val(accountMoney);
}
function toUpdate() {
    var purchaseNo=getParameter("purchaseNo");
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchase/tupdate",
        data:"purchaseNo="+purchaseNo,
        success:function (data) {
            $("input[name=purchaseNo]").val(data.purchaseNo);
            $("input[name=createTime]").val(data.createTime);
            $("#createUser").val(data.createUser.name);
            $("#accountMoney").val(data.money);
        },
        dataType:"json"
    });


}
function getDetail() {
    //通过采购单编号查找品牌id
    var purchaseNo=getParameter("purchaseNo");
    var brandId = "";
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchase/getPurchaseDetail",
        data:"purchaseNo="+purchaseNo,
        async:false,//改成同步
        success:function (data) {
            for (var i = 0; i <data.length; i++) {
                addGoods();
                // $("#no"+(i+1)+"").val(data[i].purchase.purchaseNo);
                if(data[i].goods != null) {
                    brandId = data[i].goods.goodsType.brand.id;
                    $("#brandId"+(i+1)).val(data[i].goods.goodsType.brand.id);
                    // $("#goodsTypeId"+(i+1)).val(data[i].goods.goodsType.id);
                    goodsType(data[i].goods.goodsType.brand.id, data[i].goods.goodsType.id,i);
                    // $("#goodsId"+(i+1)).val(data[i].goods.id);
                    goods(data[i].goods.goodsType.id,data[i].goods.id,i);
                }
                $("#vendorId"+(i+1)).val(data[i].vendor.id);
                $("#num"+(i+1)+"").val(data[i].num);
                $("#price"+(i+1)+"").val(data[i].price);
                $("#money"+(i+1)+"").val(data[i].money);
            }
            $('.tablelist tbody tr:odd').addClass('odd');
        },
        dataType:"json"
    });


}

function goodsType(brandId, goodsTypeId, index) {
    //通过品牌id查询该品牌可用的商品类型
    $.ajax({
        type:"post",
        url:basePath +"/purchase/product/goodsType",
        async:false,//改成同步
        data:"brandId="+brandId,
        success:function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                if (data[i].id == goodsTypeId) {
                    html += "<option value='" + data[i].id + "' selected>" + data[i].name + "</option>";
                }
            }
            $("#goodsTypeId"+(index+1)+"").html(html);
        },
        dataType:"json"
    });
}
function goods(goodsTypeId, goodsId, index) {
    //通过品牌id查询该品牌可用的商品类型
    $.ajax({
        type:"post",
        url:basePath +"/purchase/purchase/goods",
        async:false,//改成同步
        data:"goodsTypeId="+goodsTypeId,
        success:function (data) {
            var html = "<option value=''>请选择</option>";
            for (var i = 0; i < data.length; i++) {
                if (data[i].id == goodsId) {
                    html += "<option value='" + data[i].id + "' selected>" + data[i].model + "</option>";
                }
            }
            $("#goodsId"+(index+1)+"").html(html);
        },
        dataType:"json"
    });
}


function update() {
    var  param=$("#form").serializeArray();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchase/update",
        data:param,
        async:false,
        success:function (data) {
            alert(data);
            window.location.reload();
        }
    });
}



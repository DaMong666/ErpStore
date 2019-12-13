var jj=0;

$(function () {
    $.ajax({
        type:"post",
        url:basePath + "/purchase/brand/curruser",
        success:function (data) {
            $("#createUser").val(data.name);
        },
        dataType:"json"
    });

    $.ajax({
        type:"post",
        url:basePath + "/purchase/brand/currdate",
        success:function (data) {
            $("#createTime").val(data);
        }
    });
    getPurchaseNo();
    addGoods();
});
//生成采购单编号
function getPurchaseNo() {
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchase/getno",
        success:function (data) {
            $("input[name=purchaseNo]").val(data);
            for (var i = 0; i <= jj; i++) {
                $("#no"+i+"").val(data);
            }

        }
    });
}
function vendor() {
    $.ajax({
        type:"post",
        url:basePath+"/purchase/manufacturer/vendorList",
        success:function (data) {
            var html="<option value='0'>请选择</option>";
            for (var i = 0; i <data.length; i++) {
                if(data[i].status=='1'){
                    html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>"
                }
            }
            $("#vendorId"+jj+"").html(html);
        },
        dataType:"json"
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
            $("#brandId"+jj+"").html(html);
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
        "          <select id='brandId"+jj+"' onchange='loadGoodsType(" + jj +");' required>" +
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
        "          <td ><input id='num"+jj+"' name='purchaseDetailList["+ (jj-1) +"].num' type=\"text\" required/></td>" +
        "          <td>台</td>" +
        "          <td><input id='price"+jj+"' name='purchaseDetailList["+ (jj-1) +"].price' onblur='accountPrice("+(jj)+");' type=\"text\" required/></td>" +
        "          <td><input id='money"+jj+"' name='purchaseDetailList["+ (jj-1) +"].money' type='text' /></td>" +
        "        </tr>";
    $("tbody").append(html);
    brand();
    vendor();
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
/**
 * 添加采购单
 */
function addPurchase() {
    var  param=$("#form").serializeArray();
    $.ajax({
        type:"post",
        url:basePath+"/purchase/purchase/add",
        data:param,
        async:false,
        success:function (data) {
            alert(data);
            window.location.href=basePath+'/purchase/purchase/purchaseAdd.html';
        }
    });
}

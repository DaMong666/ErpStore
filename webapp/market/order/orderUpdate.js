var jj=0;

$(function () {

    //查询可用客户
    $.ajax({
        type:"post",
        url:basePath + "/market/customer/findNice",
        success:function (data) {
            var html = "";
            for (var i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
            }
            $("#cid").html(html);
        }
    });

    toUpdate();

    getDetail();

});

//添加一行明细数据
function addGoods() {
    var html="<tr id='"+(++jj)+"'>" +
        "          <td>"+jj+"</td>" +
        "          <td>" +
        "          <select id='brandId"+jj+"' name='' onchange='loadGoodsType(" + jj +");'>" +
        "            <option >请选择</option>" +
        "            </select>" +
        "          </td>" +
        "          <td>" +
        "          <select id='goodsTypeId"+jj+"' name='' onchange='loadGoods(" + jj +");'>" +
        "            <option >请选择</option>" +
        "            </select>" +
        "          </td>" +
        "          <td>" +
        "          <select id='goodsId"+jj+"' name='orderDetailList["+(jj-1)+"].goods.id'>" +
        "            <option>请选择</option>" +
        "            </select>" +
        "          </td>" +
        "          <td>" +
        "          <select name=\"orderDetailList["+(jj-1)+"].vendor.id\" id='vendorId"+jj+"'>" +
        "            <option >请选择</option>" +
        "            </select>" +
        "          </td>" +
        "          <td ><input id='num"+jj+"' name=\"orderDetailList["+(jj-1)+"].num\" type=\"text\" /></td>" +
        "          <td>台</td>" +
        "          <td><input id='price"+jj+"' name=\"orderDetailList["+(jj-1)+"].price\" onblur='accountPrice("+(jj)+");' type=\"text\" /></td>" +
        "          <td><input id='money"+jj+"' name=\"orderDetailList["+(jj-1)+"].money\" type='text' /></td>" +

        "        </tr>";
    $("tbody").append(html);
    console.log(html);
    brand(jj);
    vendor(jj);
}

//删除商品详细
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

//统计金额
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

//修改信息回显
function toUpdate() {
    var orderNo=getParameter("orderNo");
    $.ajax({
        type:"post",
        url:basePath+"/market/order/asynctoview",
        data:"orderNo="+orderNo,
        success:function (data) {
            $("#orderNo").val(data.orderNo);
            $("#cid").val(data.customer.id);
            $("#createTime").val(data.createTime);
            $("#createUser").val(data.createUser.name);
            var html1 = "";
            if(data.status == '0'){
                html1 += "未审核";
            }else if (data.status == '1') {
                html1 += "审核中";
            }else if (data.status == '2') {
                html1 += "审核通过";
            }else if (data.status == '3') {
                html1 += "审核不通过";
            }
            $("#status").val(html1);
            $("#accountMoney").val(data.money);
        },
        dataType:"json"
    });
}

function getDetail() {
    //通过订购单编号查找品牌id
    var orderNo=getParameter("orderNo");
    var brandId = "";
    $.ajax({
        type:"post",
        url:basePath+"/market/orderDetail/asyncbyno",
        data:"orderNo="+orderNo,
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

//修改订购单明细
function updateOrder() {
    var param = $("#orderUpdateForm").serialize();
    $.ajax({
        type:"post",
        url:basePath + "/market/order/asyncupdate",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href = basePath + "/market/order/orderList.html";
        }
    });
}




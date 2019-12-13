var jj=0;

$(function () {
    //获取当前登录用户姓名
    $.ajax({
        type: "post",
        url: basePath + "/login/curr",
        success: function (data) {
            $("#createUser").val(data.name);
        },
        dataType: "json"
    });
    addGoods();

    //获取当前时间
    $.ajax({
        type: "post",
        url: basePath + "/purchase/brand/currdate",
        success: function (data) {
            $("#createTime").val(data);
        }
    });

    //获取可用用户
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

    //获取订购单编号
    $.ajax({
        type:"post",
        url:basePath + "/market/order/asyncgetno",
        success: function (data) {
            $("#orderNo").val(data);
        }
    });
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
    brand();
    vendor();
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

function addOrder() {
    var param = $("#orderAddForm").serialize();
    console.log(param);
    $.ajax({
        type:"post",
        url:basePath + "/market/order/asyncadd",
        data:param,
        success:function (data) {
            alert(data);
            window.location.href = basePath + "/market/order/orderList.html";
        }
    });
}


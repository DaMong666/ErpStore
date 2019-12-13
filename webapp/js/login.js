
$(function () {
    // var cookie_name = "erpUser";
    // var cookie_value = getCookie(cookie_name);
    // console.log(cookie_value);


});

//自动生成验证码
function getYanZheng() {
    var codes=[];
    //数字:48-57;unicode编码
    for(var i=48;i<57;codes.push(i),i++);
    /*console.log(codes);*/
    //大写字母:65-90;unicode编码
    for(var i=60;i<90;codes.push(i),i++);
    //小写字母:97-122;unicode编码
    for(var i=97;i<122;codes.push(i),i++);
    var arr=[];
    for(var i=0;i<4;i++) {   //从0-61之间取随机数
        var index = Math.floor(Math.random() * (9 - 0 + 1) + 0);
        // var char = String.fromCharCode(codes[index]);
        arr.push(index);
    }
    var code=arr.join("");
    $("#yanzheng").text(code);
}

function login() {
    if ($("input[name='yzm']").val() == $("#yanzheng").text()) {
        $.ajax({
            type:"post",
            url:basePath + "/login/lo",
            data:$("#loginForm").serializeArray(),
            success:function (data) {
                if (data.code == 1) {
                    if ($("input[name='remember']").attr('checked')) {
                        // var cookie_value = $.cookie("erpUser");
                        $.cookie("erpUser",data.cookie, { expires: 7 });
                    } else {
                        $.cookie('erpUser', null);
                    }
                    window.location.href = basePath + "/main.html";
                } else {
                    $("#info").text(data.info);
                }
            },
            dataType:"json"
        });
    } else {
        $("#info").text("验证码错误");
    }
}

function getCookie(cookie_name) {
    var allCookies = document.cookie;
    var cookie_pos = allcookies.indexOf(cookie_name);
    // 如果找到了索引，就代表cookie存在,否则不存在
    if (cookie_pos != -1) {
        // 把cookie_pos放在值的开始，只要给值加1即可
        //计算取cookie值得开始索引，加的1为“=”
        cookie_pos = cookie_pos + cookie_name.length + 1;
        //计算取cookie值得结束索引
        var cookie_end = allcookies.indexOf(";", cookie_pos);

        if (cookie_end == -1) {
            cookie_end = allcookies.length;
        }
        //得到想要的cookie的值
        var value = unescape(allcookies.substring(cookie_pos, cookie_end));
    }
    return value;
}
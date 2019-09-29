layui.use(['form'], function () {
    let form = layui.form;

});

// 切换验证码
function changeCode() {
    let node = document.getElementById("captcha-img");
    //修改验证码
    if (node) {
        node.src = node.src + '?id=' + uuid();
    }
}

function uuid() {
    //获取系统当前的时间
    let d = new Date().getTime();
    //替换uuid里面的x和y
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        //取余 16进制
        let r = (d + Math.random() * 16) % 16 | 0;
        //向下去整
        d = Math.floor(d / 16);
        //toString 表示编程16进制的数据
        return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
}

function cambiar_login() {
    document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
    document.querySelector('.cont_form_login').style.display = "block";
    document.querySelector('.cont_form_sign_up').style.opacity = "0";

    setTimeout(function () {
        document.querySelector('.cont_form_login').style.opacity = "1";
    }, 400);

    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
    }, 200);
}
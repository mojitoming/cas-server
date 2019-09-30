layui.use(['form'], function () {
    let form = layui.form;

    form.verify({
        username: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (isEmpty(value)) {
                return '用户名不能为空';
            }
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                return '用户名不能全为数字';
            }
        },

        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        password: function (value, item) {
            if (isEmpty(value)) {
                return '密码不能为空';
            }
        },
        captcha: function (value, item) {
            if (isEmpty(value)) {
                return '验证码不能为空';
            }
        }
    });
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
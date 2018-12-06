(function (factory) {
    if (typeof define === "function" && define.amd) {
        define(["", "jquery-validator"], factory);
    } else {
        factory(jQuery);
    }
}(function ($) {
    /*
     * Translated default messages for the jQuery validation plugin.
     * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
     */
    $.extend($.validator.messages, {
        required: "这是必填字段",
        remote: "已被占用",
        email: "请输入有效的电子邮件地址",
        url: "请输入有效的网址",
        date: "请输入有效的日期",
        dateISO: "请输入有效的日期 (YYYY-MM-DD)",
        number: "请输入有效的数字",
        digits: "只能输入数字",
        equalTo: "两次输入不一致",
        extension: "请输入有效的后缀",
        maxlength: $.validator.format("最多可以输入 {0} 个字符"),
        minlength: $.validator.format("最少要输入 {0} 个字符"),
        rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
        range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
        max: $.validator.format("请输入不大于 {0} 的数值"),
        min: $.validator.format("请输入不小于 {0} 的数值")
    });
}));

//下面是自定义校验
$.extend($.validator.addMethod("isPassword", function (value) {
    var str = /^[a-zA-Z0-9]{8,20}$/;
    return str.test(value);
}, "密码必须是8至20位的字母或数字"));

$.extend($.validator.addMethod("isCode", function (value) {
    var str = /^[0-9]{6}$/;
    return str.test(value);
}, "验证码必须是6位数字"));

$.extend($.validator.addMethod("isIdNo", function (value, element) {
    var str = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    return this.optional(element) || str.test(value);
}, "请输入正确的18位身份证号码"));

$.extend($.validator.addMethod("isMobileNo", function (value, element) {
    var isMobileNo = /\\d/;
    return this.optional(element) || isMobileNo.test(value);
}, "请输入正确的手机号码"));

$.extend($.validator.addMethod("isEmail", function (value, element) {
    var isEmail = /^[\\.a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    return this.optional(element) || isEmail.test(value);
}, "请输入正确的电子邮箱"));

$.extend($.validator.addMethod("isRoleCode", function (value) {
    var str = /^ROLE[A-Z_]{0,28}$/;
    return str.test(value);
}, "以ROLE开头,纯大写,可带下划线,不超过32位"));

$.extend($.validator.addMethod("isMenuCode", function (value) {
    var str = /^[A-Z_]{1,32}$/;
    return str.test(value);
}, "纯大写,可带下划线,不超过32位"));

$.extend($.validator.addMethod("isInt", function (value) {
    var str = /^[0-9]+$/;
    return str.test(value);
}, "请输入自然数"));

$.extend($.validator.addMethod("isNum", function (value) {
    var str = /^[0-9\\.]+$/;
    return str.test(value);
}, "请输数字"));

$.extend($.validator.addMethod("isPoint", function (value) {
    var str = /^[1-9]\d*0$/;
    return str.test(value);
}, "充值金额必须为10元的倍数"));

$.extend($.validator.addMethod("isUSDPoint", function (value) {
    value = value * 1 * 100 / 60;
    var str = /^[1-9]\d*00$/;
    return str.test(value);
}, "Must be 60 integer times"));

$.extend($.validator.addMethod("isVideoCode", function (value) {
    var str = /^<iframe/;
    var ok = str.test(value);

    if (ok) {
        return true;
    }
    str = /^<embed/;
    return str.test(value);
}, "视频代码格式错误，请查看教程"));
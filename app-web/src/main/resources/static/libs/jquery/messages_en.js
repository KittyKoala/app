(function (factory) {
    if (typeof define === "function" && define.amd) {
        define(["jquery", "jquery-validator"], factory);
    } else {
        factory(jQuery);
    }
}(function ($) {

    /*
     * Translated default messages for the jQuery validation plugin.
     * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
     */
    $.extend($.validator.messages, {
        required: "This field is required.",
        remote: "Please fix this field.",
        email: "Please enter a valid email address.",
        url: "Please enter a valid URL.",
        date: "Please enter a valid date.",
        dateISO: "Please enter a valid date ( ISO ).",
        number: "Please enter a valid number.",
        digits: "Please enter only digits.",
        creditcard: "Please enter a valid credit card number.",
        equalTo: "Please enter the same value again.",
        maxlength: $.validator.format( "Please enter no more than {0} characters." ),
        minlength: $.validator.format( "Please enter at least {0} characters." ),
        rangelength: $.validator.format( "Please enter a value between {0} and {1} characters long." ),
        range: $.validator.format( "Please enter a value between {0} and {1}." ),
        max: $.validator.format( "Please enter a value less than or equal to {0}." ),
        min: $.validator.format( "Please enter a value greater than or equal to {0}." )
    });
}));

//下面是自定义校验
$.extend($.validator.addMethod("isPassword", function (value) {
    var str = /^[a-zA-Z0-9]{8,20}$/;
    return str.test(value);
}, "Password must be 8 to 20 letters or Numbers"));

$.extend($.validator.addMethod("isCode", function (value) {
    var str = /^[0-9]{6}$/;
    return str.test(value);
}, "Verify code must be 6 digits"));

$.extend($.validator.addMethod("isIdNo", function (value, element) {
    var str = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    return this.optional(element) || str.test(value);
}, "Id card must be 18 digits"));

$.extend($.validator.addMethod("isMobileNo", function (value, element) {
    var isMobileNo = /\\d/;
    return this.optional(element) || isMobileNo.test(value);
}, "Please enter the correct phone number"));

$.extend($.validator.addMethod("isEmail", function (value, element) {
    var isEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    return this.optional(element) || isEmail.test(value);
}, "Please enter the correct email"));

$.extend($.validator.addMethod("isRoleCode", function (value) {
    var str = /^ROLE[A-Z_]{0,28}$/;
    return str.test(value);
}, "Begin with ROLE, uppercase, but underlined, no more than 32 bits"));

$.extend($.validator.addMethod("isMenuCode", function (value) {
    var str = /^[A-Z_]{1,32}$/;
    return str.test(value);
}, "Uppercase, underlined, no more than 32 bits"));

$.extend($.validator.addMethod("isInt", function (value) {
    var str = /^[0-9]+$/;
    return str.test(value);
}, "Please enter the Numbers"));

$.extend($.validator.addMethod("isNum", function (value) {
    var str = /^[0-9\\.]+$/;
    return str.test(value);
}, "Please enter the Numbers"));

$.extend($.validator.addMethod("isPoint", function (value) {
    var str = /^[1-9]\d*0$/;
    return str.test(value);
}, "Must be a multiple of 10"));

$.extend($.validator.addMethod("isUSDPoint", function (value) {
    value = value * 1 * 100 / 60;
    var str = /^[1-9]\d*00$/;
    return str.test(value);
}, "Must be 60 integer times"));
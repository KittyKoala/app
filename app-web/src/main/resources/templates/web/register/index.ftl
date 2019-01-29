<#assign title="注册"/>
<#include "../../common/auth-form.ftl"/>

<@override name="main">
    <@panel style="background: #000 url('${ctx}/app/images/bg.jpg')">
        <div style="text-align: right;font: 30px;color: #f5f5f5;padding-top: 100px;">暂不开放注册</div>
    </@panel>
</@override>

<@override name="script">
<script>
    /**
     * 注册成功的回调
     */
    function success() {
        window.location.href = ctx + "/register/success";
    }

    $(function () {
        var time = 60;
        var $form = $(".register-form");

        // 发送验证码
        var $sendSmsBtn = $("#sendBtn");
        $sendSmsBtn.click(function () {
            $form.validate().element($("#email"));

            var isValid = $form.validate().valid();
            if (!isValid) {
                return false;
            }

            var email = $('#email').val();
            $sendSmsBtn.button('loading');
            $sendSmsBtn.addClass('sending');
            $.post("${ctx}/api/email/register", {email: email}, function (response) {
                if ("0000" === response.respCo) {
                    $.growl.notice({
                        title: '消息',
                        message: '获取成功'
                    });
                    var t = setInterval(function () {
                        time--;
                        $sendSmsBtn.html(time + "秒");
                        $sendSmsBtn.removeClass('sending');
                        if (time === 0) {
                            clearInterval(t);
                            $sendSmsBtn.html("获取");
                            $sendSmsBtn.button('reset');
                            time = 60;
                        }
                    }, 1000)
                } else {
                    $sendSmsBtn.button('reset');
                    $sendSmsBtn.removeClass('sending');
                    $.growl.error({
                        title: '错误',
                        message: response.respMsg
                    });
                }
            });

            return false;
        });

        $("body").css({"background": "#000"});
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
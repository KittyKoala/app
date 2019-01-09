<#assign title="注册"/>
<#include "../../common/auth-form.ftl"/>

<@override name="main">
    <@panel bg_img="/app/images/bg.jpg">
        <@form action="${ctx}/register" class="register-form" success="success">
            <@input type="email" label="电子邮箱" id="email" name="email" placeholder="可用于登录找回密码和接收通知" remote="${ctx}/api/validate/email" required=true/>
            <@input label="邮箱验证码" name="code" required=true>
            <button id="sendBtn" class="btn" data-loading-text="正在发送...">
                获取
            </button>
            </@input>
            <@input label="密码" id="password" name="password" type="password" validator="isPassword" required=true/>
            <@input label="确认密码" name="rePassword" type="password" equal_to="#password" required=true/>

            <@actions>
                <@button name="注册" type="submit" icon="fa-users"/>
                <@button name="重置" type="reset" icon="fa-undo"/>
            </@actions>
        </@form>
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
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
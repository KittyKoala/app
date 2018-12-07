<#assign title="注册"/>

<@override name="main">
    <@panel bg_img="/app/images/bg.jpg">
        <@web_form action="${ctx}/register" class="register-form">
            <@web_input label="电子邮箱" id="email" name="email" placeholder="可用于登录找回密码和接收通知"/>
            <@web_input label="邮箱验证码" name="code">
            <button id="sendBtn" class="btn" data-loading-text="正在发送...">
                获取
            </button>
            </@web_input>
            <@web_input label="密码" id="password" name="password" type="password"/>
            <@web_input label="确认密码" name="rePassword" type="password"/>

            <@web_actions>
                <@web_button name="注册" type="submit" icon="fa-users"/>
                <@web_button name="重置" type="reset" icon="fa-undo"/>
            </@web_actions>
        </@web_form>
    </@panel>
</@override>

<@override name="script">
<script>
    $(function () {
        // 表单校验
        var $form = $('.form');
        var $btn = $form.find("button[type='submit']");

        $form.validate({
            rules: {
                email: {
                    required: true,
                    isEmail: true,
                    remote: {
                        url: "${ctx}/api/validate/email",
                        type: 'post',
                        data: {
                            'email': function () {
                                return $('#email').val()
                            }
                        }
                    }
                },
                password: {
                    required: true,
                    isPassword: true
                },
                rePassword: {
                    required: true,
                    equalTo: "#password"
                },
                code: {
                    required: true,
                    isCode: true
                }
            },
            submitHandler: function (form, event) {
                event.preventDefault();
                $btn.button('loading');
                formSubmit($form, $btn, function () {
                    window.location.href = ctx + "/register/success";
                })
            },
            errorElement: "div"
        });

        var time = 60;

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
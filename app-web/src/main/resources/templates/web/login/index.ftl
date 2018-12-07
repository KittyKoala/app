<#assign title="登录"/>

<@override name="main">
    <@panel bg_img="/app/images/bg.jpg">
        <@web_form action="${ctx}/login" class="login-form">
            <@web_input label="电子邮箱" name="email"/>
            <@web_input label="密码" name="password" type="password"/>
            <@web_captcha label="验证码" name="captcha"/>

            <@web_actions>
                <@web_button name="登录" type="submit" icon="fa-check"/>
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
                    isEmail: true
                },
                password: {
                    required: true,
                    isPassword: true
                },
                captcha: {
                    required: true
                }
            },
            submitHandler: function (form, event) {
                event.preventDefault();
                formSubmit($form, $btn, function () {
                    var redirectUrl = '${redirectUrl}';
                    if (redirectUrl != '') {
                        window.location.href = redirectUrl;
                        return;
                    }
                    window.location.href = "${ctx}/";
                }, function () {
                    $(".captcha").attr('src', '${ctx}/captcha?r=' + Math.random());
                })
            },
            errorElement: "div"
        });
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
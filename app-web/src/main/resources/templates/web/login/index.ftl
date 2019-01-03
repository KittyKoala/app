<#assign title="登录"/>

<@override name="main">
    <@panel bg_img="/app/images/bg.jpg">
        <@form action="${ctx}/login" class="login-form" success="success" error="error">
            <@input type="email" label="电子邮箱" name="email" required=true/>
            <@input label="密码" name="password" type="password" required=true validator="isPassword">
                <a href="${ctx}/forgot" target="_blank" class="forgot">忘记密码？</a>
            </@input>
            <@captcha label="验证码" id="captcha" name="captcha"/>

            <@actions>
                <@button name="登录" type="submit" icon="fa-check"/>
                <@button name="重置" type="reset" icon="fa-undo"/>
            </@actions>
        </@form>
    </@panel>
</@override>

<@override name="script">
<script>
    /**
     * 登录成功的回调
     *
     * @param response
     */
    function success(response) {
        var redirectUrl = '${redirectUrl}';
        if (redirectUrl !== '') {
            window.location.href = redirectUrl;
            return;
        }
        window.location.href = "${ctx}/";
    }

    /**
     * 登录失败的回调
     */
    function error() {
        $("#captcha").attr('src', '${ctx}/captcha?r=' + Math.random());
        $("#captcha").val("");
    }
</script>
</@override>

<@extends name="../layout.ftl"/>
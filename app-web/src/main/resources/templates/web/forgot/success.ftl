<#assign title="找回密码成功"/>

<@override name="style">
<style>
    .container {
        background: #060606;
    }

    .forgot-panel {
        background: url('/app/images/bg.jpg') no-repeat;
    }

    .info {
        color: #fff;
        text-align: center;
        margin-left: 550px;
        font-size: 22px;
        padding-top: 140px;
        letter-spacing: 2px;
    }
</style>
</@override>

<@override name="main">
<div class="forgot-panel">
    <div class="inner">
        <div class="info">
            恭喜您密码修改成功啦！现在就去登录吧(<span id="timer">5s</span>)...
        </div>
    </div>
    <div class="clear"></div>
</div>
</@override>

<@override name="script">
<script>
    $(function () {
        // 动态高度设定
        $(".forgot-panel").css({"minHeight": $(window).height() - 330});

        // 5秒后取登录
        var time = 5;
        var $timer = $("#timer");
        var t = setInterval(function () {
            time--;
            $timer.html(time + "s");
            $timer.removeClass('sending');
            if (time === 0) {
                clearInterval(t);
                window.location.href = '${ctx}/login';
            }
        }, 1000);
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
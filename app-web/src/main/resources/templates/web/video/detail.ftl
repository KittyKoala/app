<#assign title="${video.title}"/>

<#if video.cover?starts_with('http')>
    <#assign bg_img=video.cover/>
<#else>
    <#assign bg_img="https://kangyonggan.com/" + video.cover/>
</#if>

<@override name="style">
<style>

    @media (min-width: 1133px) {
        .video {
            width: 1024px;
            height: 576px;
            position: relative;
            border: 1px solid #d5d5d5;
            margin: 0 auto;
        }

        .video iframe, .video embed {
            width: 1024px;
            height: 576px;
            margin: 0 auto;
        }
    }

    .loading {
        position: absolute;
        width: 100%;
        left: 0;
        right: 0;
        text-align: center;
        top: 200px;
        color: #999;
        z-index: -1;
    }
</style>
</@override>

<@override name="main">
<div class="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name="视频" href="${ctx}/video"/>
            <@breadcrumb name=title/>
        </@breadcrumbs>

        <div class="video">
        ${video.content}
            <div class="loading">努力加载中...</div>
        </div>
    </@panel>
</div>
</@override>

<@override name="script">

<script>
    $(function () {
        $(window).resize(function () {
            var width = $(window).width();
            if (width < 1133) {
                hengshuping();
            }
        });

        //判断手机横竖屏状态
        function hengshuping() {
            var width = $(window).width();
            var offset = 8;
            if (width >= 992) {
                offset = 50;
            } else if (width >= 768) {
                offset = 20;
            }

            if ($(window).width() >= 1133) {
                // 大屏幕
                return;
            } else {
                if (!window.orientation || window.orientation === 180 || window.orientation === 0) {
                    var wid = width - offset * 2;
                    $(".video iframe, .video embed").attr("height", (wid * 9 / 16) + "px");
                    $(".video iframe, .video embed").attr("width", wid + "px");
                    navbar("show");
                }
                if (window.orientation === 90 || window.orientation === -90) {
                    $(".video iframe, .video embed").attr("height", $(window).height() - offset * 9 / 16 + "px");
                    $(".video iframe, .video embed").attr("width", width - offset * 2 + "px");
                    navbar("hide");
                }
            }
        }

        hengshuping();

        window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", hengshuping, false);
    })
</script>

<script>window._bd_share_config = {
    "common": {
        "bdSnsKey": {},
        "bdText": "${video.title}",
        "bdDesc": "不用怀疑，我就是你要找的人！点开看看吧...",
        "bdUrl": "https://kangyonggan.com/video/${video.videoId}",
        "bdMini": "2",
        "bdMiniList": false,
        "bdPic": "${bg_img}",
        "bdStyle": "0",
        "bdSize": "16"
    }, "slide": {"type": "slide", "bdImg": "5", "bdPos": "right", "bdTop": "200"}
};
with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = '${ctx}/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
</@override>

<@extends name="../layout.ftl"/>
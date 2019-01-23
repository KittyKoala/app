<#assign title="${video.title}"/>

<@override name="style">
<style>
    .video {
        width: 1024px;
        height: 576px;
        position: relative;
        border: 1px solid #d5d5d5;
    }

    .video iframe, .video embed {
        width: 1024px;
        height: 576px;
        margin: 0 auto;
    }

    .loading {
        position: absolute;
        width: 100px;
        left: 462px;
        text-align: center;
        top: 200px;
        color: #999;
        z-index: -1;
    }
</style>
</@override>

<@override name="main">
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
</@override>

<@override name="script">
<script>window._bd_share_config = {
    "common": {
        "bdSnsKey": {},
        "bdText": "${video.title}",
        "bdDesc": "不用怀疑，我就是你要找的人！点开看看吧...",
        "bdUrl": "https://kangyonggan.com/video/${video.videoId}",
        "bdMini": "2",
        "bdMiniList": false,
        "bdPic": "https://kangyonggan.com/${video.cover}",
        "bdStyle": "0",
        "bdSize": "16"
    }, "slide": {"type": "slide", "bdImg": "5", "bdPos": "right", "bdTop": "200"}
};
with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
</@override>

<@extends name="../layout.ftl"/>
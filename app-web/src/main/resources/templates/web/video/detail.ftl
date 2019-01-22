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
</@override>

<@extends name="../layout.ftl"/>
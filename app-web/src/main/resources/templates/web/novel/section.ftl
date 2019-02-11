<#assign title="${section.title}"/>

<@override name="style">
<style>
    .title {
        text-align: center;
        font-size: 24px;
    }

    .toolbar {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .toolbar a {
        color: #333;
        display: inline-block;
        text-decoration: none;
        width: 32%
    }

    .content {
        line-height: 1.7;
        font-size: 17px !important;
    }
</style>
</@override>

<@override name="main">
<div class="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name="小说" href="${ctx}/novel"/>
            <@breadcrumb name="${novel.name}" href="${ctx}/novel/${novel.novelId}"/>
            <@breadcrumb name=title/>
        </@breadcrumbs>

        <div class="title">
        ${section.title}
        </div>

        <#include "toolbar.ftl"/>

        <div class="border content">
        ${section.content}
        </div>

        <#include "toolbar.ftl"/>
        <#include "../audio.ftl"/>
    </@panel>
</div>
</@override>

<@override name="script">
<script>
    $(function () {
        // 更新
        $(".pull-btn").click(function () {
            $this = $(this);
            $this.button('loading');
            $.get($this.attr("href"), function (response) {
                $this.button('reset');
                if ("0000" === response.respCo) {
                    $.growl.notice({
                        title: '消息',
                        message: '已经加入更新队列'
                    });
                } else {
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


<script>window._bd_share_config = {
    "common": {
        "bdSnsKey": {},
        "bdText": "${section.title}",
        "bdDesc": "${section.content}",
        "bdUrl": "https://kangyonggan.com/novel/${novel.novelId}/${section.sectionId}",
        "bdMini": "2",
        "bdMiniList": false,
        "bdPic": "${(novel.cover!='')?string('https://kangyonggan.com/${novel.cover}', 'https://kangyonggan.com/app/images/nocover.jpg')}",
        "bdStyle": "0",
        "bdSize": "16"
    }, "slide": {"type": "slide", "bdImg": "5", "bdPos": "right", "bdTop": "200"}
};
with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = '${ctx}/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
</@override>
</@override>

<@extends name="../layout.ftl"/>
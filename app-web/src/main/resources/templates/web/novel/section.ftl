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
        width: 333px;
    }

    .content {
        line-height: 1.7;
        font-size: 17px !important;
    }
</style>
</@override>

<@override name="main">
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
</@override>

<@extends name="../layout.ftl"/>
<#assign title="${section.title}"/>

<@override name="style">
<style>
    .toolbar {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .toolbar a {
        color: #ccc;
        display: inline-block;
        text-decoration: none;
        width: 32%;
    }

    .content {
        padding: 5px;
        line-height: 1.7;
        font-size: 17px !important;
    }
</style>
</@override>

<@override name="main">
<#assign backUrl="${ctx}/wap/novel/${novel.novelId}"/>
<#include "../navbar.ftl"/>
<#include "toolbar.ftl"/>

<div class="content">
    ${section.content}
</div>

<#include "toolbar.ftl"/>
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
                    alert("已经加入更新队列");
                    window.location.reload();
                } else {
                    alert(response.respMsg);
                }
            });

            return false;
        });
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
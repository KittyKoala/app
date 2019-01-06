<#assign title="${novel.name}"/>

<@override name="style">
<style>
    .novel-info {
        padding: 10px;
    }

    .left {
        float: left;
        width: 32%;
    }

    .right {
        float: left;
        width: 63%;
        margin-left: 10px;
    }

    .name {
        font-size: 20px;
    }

    .info {
        margin-top: 15px;
        font-size: 13px;
    }

    .info div {
        margin-top: 6px;
        overflow: hidden;
    }

    .info a {
        color: #ccc;
        text-decoration: none;
    }

    img {
        width: 100%;
        background-color: rgb(255, 255, 255);
        border-width: 1px;
        border-style: solid;
        border-color: rgb(221, 221, 221);
        border-image: initial;
        padding: 1px;
    }

    .header {
        font-size: 18px;
        text-align: center;
        padding: 10px 3px;
        border-top: 1px solid #ccc;
    }

    .header a {
        font-size: 14px;
        text-decoration: none;
        color: #ccc;
        margin-right: 10px;
    }

    .section-list ul {
        list-style: none;
        margin-top: 5px;
        padding: 0;
    }

    .section-list ul li {
        border-bottom: 1px dashed #d5d5d5;
        margin: 5px;
    }

    .section-list ul li a {
        color: #ccc;
        text-decoration: none;
        line-height: 32px;
        font-size: 14px;
    }

    .empty-sections {
        text-align: center;
        margin-top: 20px;
    }
</style>
</@override>

<@override name="main">
    <#include "../navbar.ftl"/>
    <div class="novel-info">
        <div class="left">
        <#if novel.cover!=''>
            <img src="${ctx}/${novel.cover}"/>
        <#else>
            <img src="${ctx}/app/images/nocover.jpg"/>
        </#if>
        </div>
        <div class="right">
            <div class="name">
                ${novel.name}
            </div>

            <div class="info">
                <div>作　　者：${novel.author}</div>
                <div>状　　态：<#if novelQueue??>${enum('name', 'NovelQueueStatus', novelQueue.status)}<#else>无</#if></div>
                <div>最新章节：<#if lastSection??><a href="${ctx}/wap/novel/${novel.novelId}/${lastSection.sectionId}">${lastSection.title}</a><#else>无</#if></div>
                <div>来　　源：${enum('name', 'NovelSource', novel.source)}</div>
                <div>最后更新：<#if novelQueue??>${novelQueue.createdTime?datetime}<#else>无</#if></div>
            </div>
        </div>
        <div style="clear: both"></div>
    </div>

    <div class="section-list border">
        <div class="header">
            《${novel.name}》章节列表
            <#if !novelQueue?? || novelQueue.status=='Y'>
                <a class="pull-right pull-btn" href="${ctx}/novel/${novel.novelId}/pull"
                   data-loading-text="更新中">
                    更新
                    <i class="fa fa-refresh"></i>
                </a>
            </#if>
        </div>
        <ul>
            <#if sections?size gt 0>
                <#list sections as section>
               <li>
                   <a href="${ctx}/wap/novel/${novel.novelId}/${section.sectionId}">${section.title}</a>
               </li>
                </#list>
            <#else>
                <div class="empty-sections">没有相关章节</div>
            </#if>
            <div style="clear: both"></div>
        </ul>
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
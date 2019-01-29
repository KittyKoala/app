<#assign title="${novel.name}"/>

<@override name="style">
<style>
    .section-list {
        margin-top: 15px;
    }

    .left {
        float: left;
        width: 130px;
    }

    .name {
        font-size: 22px;
        margin-left: 150px;
    }

    .name span {
        font-size: 14px;
        color: #a72c11;
    }

    .info {
        margin-top: 5px;
        font-size: 14px;
        margin-left: 150px;
    }

    .info div {
        float: left;
        width: 40%;
        margin-top: 2px;
    }

    .info a {
        color: #a72c11;
        text-decoration: none;
    }

    img {
        width: 124px;
        height: 154px;
        background-color: rgb(255, 255, 255);
        border-width: 1px;
        border-style: solid;
        border-color: rgb(221, 221, 221);
        border-image: initial;
        padding: 1px;
    }

    .summary {
        width: 70%;
        float: left;
        margin-left: 20px;
        margin-top: 5px;
        border-top: 1px solid #D5D5D5;
        min-height: 65px;
        overflow: hidden;
        font-size: 13px;
        line-height: 21px;
        padding: 7px 0;
    }

    .header {
        font-size: 18px;
        text-align: center;
        background: #f5f5f5;
        padding: 3px;
    }

    .header a {
        font-size: 14px;
        text-decoration: none;
        color: #a72c11;
        margin-right: 10px;
    }

    .section-list ul {
        list-style: none;
        margin-top: 6px;
        padding: 0;
    }

    .section-list ul li {
        float: left;
        width: 33%;
        border-bottom: 1px dashed #d5d5d5;
    }

    /*中屏*/
    @media (max-width: 992px) {
        .section-list ul li {
            width: 50%;
        }
    }

    /*小屏*/
    @media (max-width: 768px) {
        .section-list ul li {
            width: 100%;
        }
    }

    .section-list ul li a {
        color: #333;
        text-decoration: none;
        line-height: 32px;
        margin-left: 10px;
        font-size: 14px;
    }

    .empty-sections {
        text-align: center;
        margin-top: 20px;
    }

    /*中屏*/
    @media (max-width: 992px) {
    }

    /*小屏*/
    @media (max-width: 768px) {
        .info div {
            width: 100%;
        }

        .summary {
            width: 100%;
            margin-left: 0;
        }
    }
</style>
</@override>

<@override name="main">
<div class="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name="小说" href="${ctx}/novel"/>
            <@breadcrumb name=title/>
        </@breadcrumbs>

        <div class="novel-info border">
            <div class="left">
                <#if novel.cover!=''>
                    <img src="${ctx}/${novel.cover}"/>
                <#else>
                    <img src="${ctx}/app/images/nocover.jpg"/>
                </#if>
            </div>
            <div class="name">
            ${novel.name}
                <#if novelQueue??>
                    <span>（${enum('name', 'NovelQueueStatus', novelQueue.status)}）</span>
                </#if>
            </div>

            <div class="info">
                <div>作者：${novel.author}</div>
                <div>最新章节：<#if lastSection??><a
                        href="${ctx}/novel/${novel.novelId}/${lastSection.sectionId}">${lastSection.title}</a><#else>
                    无</#if></div>
                <div>来源：${enum('name', 'NovelSource', novel.source)}</div>
                <div>最后更新：<#if novelQueue??>${novelQueue.createdTime?datetime}<#else>无</#if></div>
            </div>

            <div class="summary">
            ${novel.summary}
            </div>
            <@clear/>
        </div>

        <div class="section-list border">
            <div class="header">
                《${novel.name}》最新章节
                <#if !novelQueue?? || novelQueue.status=='Y'>
                    <a class="pull-right pull-btn" href="${ctx}/novel/${novel.novelId}/pull" data-loading-text="更新中">
                        更新
                        <i class="fa fa-refresh"></i>
                    </a>
                </#if>
            </div>
            <ul>
                <#if sections?size gt 0>
                    <#list sections?reverse as section>
                        <#if section_index lt 9>
                            <li>
                                <a href="${ctx}/novel/${novel.novelId}/${section.sectionId}">${section.title}</a>
                            </li>
                        <#else>
                            <#break />
                        </#if>
                    </#list>
                <#else>
                    <div class="empty-sections">没有最新章节</div>
                </#if>
                <@clear/>
            </ul>
            <div class="header">
                《${novel.name}》章节列表
            </div>
            <ul>
                <#if sections?size gt 0>
                    <#list sections as section>
                        <li>
                            <a href="${ctx}/novel/${novel.novelId}/${section.sectionId}">${section.title}</a>
                        </li>
                    </#list>
                <#else>
                    <div class="empty-sections">没有相关章节</div>
                </#if>
                <@clear/>
            </ul>
        </div>
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
</@override>

<@extends name="../layout.ftl"/>
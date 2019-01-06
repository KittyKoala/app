<@override name="style">
<style>
    .novel-list, .novel-list dl, .novel-list dt, .novel-list dd {
        margin: 0;
        padding: 0;
    }

    .novel-list {
        list-style: none;
        padding: 10px;
    }

    .novel-list li {
        display: block;
        margin-bottom: 10px;
        padding-bottom: 10px;
        border-bottom: 1px solid #999;
    }

    .novel-list dd {
        width: 35%;
        float: left;
    }

    .novel-list dt {
        float: left;
        width: 60%;
        margin-left: 10px;
    }

    .novel-list a {
        color: #ccc;
        text-decoration: none;
    }

    .novel-list .author {
        font-size: 13px;
        float: right;
        color: rgb(179, 179, 179);
    }

    .novel-list .summary {
        max-height: 116px;
        overflow: hidden;
        font-size: 13px;
        line-height: 21px;
        text-indent: 2em;
        padding: 7px 0;
    }

    .novel-list img {
        width: 100%;
        background-color: rgb(255, 255, 255);
        border-width: 1px;
        border-style: solid;
        border-color: rgb(221, 221, 221);
        border-image: initial;
        padding: 1px;
    }

    .empty {
        text-align: center;
        line-height: 80px;
    }
</style>
</@override>

<@override name="main">
<#if novels?size gt 0>
    <ul class="novel-list">
    <#list novels as novel>
        <li>
            <dl>
                <dd>
                    <a href="${ctx}/wap/novel/${novel.novelId}">
                        <#if novel.cover!=''>
                            <img src="${ctx}/${novel.cover}"/>
                        <#else>
                            <img src="${ctx}/app/images/nocover.jpg"/>
                        </#if>
                    </a>
                </dd>
                <dt>
                    <div>
                        <a href="${ctx}/wap/novel/${novel.novelId}">${novel.name}</a>
                        <span class="author">${novel.author}</span>
                    </div>
                    <div class="summary">
                        ${novel.summary}
                    </div>
                </dt>
            </dl>
            <div style="clear: both"></div>
        </li>
    </#list>
</ul>
<#else>
    <div class="empty">暂时没有小说</div>
</#if>
</@override>

<@extends name="layout.ftl"/>
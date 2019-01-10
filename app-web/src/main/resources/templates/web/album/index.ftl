<#assign title="相册"/>

<@override name="style">
<style>
    .album-list, .album-list dl, .album-list dt, .album-list dd {
        margin: 0;
        padding: 0;
    }

    .album-list {
        list-style: none;
    }

    .album-list li {
        float: left;
        width: 330px;
        margin-top: 10px;
        margin-right: 5px;
        border: 1px solid #d2d2d2;
        text-align: center;
    }

    .album-list li dl dd {
        width: 300px;
        height: 200px;
        margin: 0 auto;
        margin-top: 10px;
        overflow: hidden;
        position: relative;
    }

    .album-list li dl dd a {
        display: inline-block;
        width: 300px;
        height: 200px;
        background-position: center;
        background-size: cover;
    }

    .album-list .size {
        position: absolute;
        bottom: 10px;
        right: 10px;
        color: #fff;
        font-size: 30px;
    }

    .album-list li dl dt {
        margin-top: 3px;
        margin-bottom: 5px;
        color: #595959;
        line-height: 22px;
        text-align: left;
        margin-left: 13px;
    }

    .album-list li dl dt span {
        color: #999;
        font-size: 13px;
        margin-right: 13px;
    }

    .album-list img {
        width: 300px;
        margin: auto;
    }

    .empty {
        text-align: center;
        line-height: 80px;
    }
</style>
</@override>

<@override name="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name=title/>
        </@breadcrumbs>

        <#if albums?size gt 0>
        <ul class="album-list border">
            <#list albums as album>
                <li>
                    <dl>
                        <dd>
                            <a href="${ctx}/album/${album.albumId}" style="background-image: url('${ctx}/${album.cover}')"></a>
                            <div class="size">${album.size}</div>
                        </dd>
                        <dt>
                        ${album.albumName}
                            <#if album.password!=''>
                                <i class="fa fa-lock"></i>
                            </#if>
                            <span class="pull-right">${album.createdTime?date}</span>
                        </dt>
                    </dl>
                </li>
            </#list>
            <div class="clear"></div>
        </ul>
        <#else>
        <div class="empty">没有相册</div>
        </#if>
    </@panel>
</@override>

<@extends name="../layout.ftl"/>
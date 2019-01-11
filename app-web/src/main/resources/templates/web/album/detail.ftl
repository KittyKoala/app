<#assign title="${album.albumName}"/>
<#assign pwd = RequestParameters.pwd!'' />

<@override name="style">
<link rel="stylesheet" href="${ctx}/libs/zoomify/dist/zoomify.min.css">
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
        width: 195px;
        margin-top: 10px;
        margin-right: 5px;
        border: 1px solid #d2d2d2;
        text-align: center;
    }

    .album-list li dl dd {
        height: 133px;
        margin: 0 auto;
        overflow: hidden;
        position: relative;
    }

    .album-list li dl dd div {
        width: 195px;
        height: 133px;
        background-position: center;
        background-size: cover;
    }

    .album-list li dl dt {
        margin-top: 3px;
        margin-bottom: 5px;
        line-height: 22px;
        color: #999;
        font-size: 13px;
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
            <@breadcrumb name="相册" href="${ctx}/album"/>
            <@breadcrumb name=title/>
        </@breadcrumbs>

        <#if page.list?size gt 0>
        <ul class="album-list border">
            <#list page.list as photo>
                <li>
                    <dl>
                        <dd>
                            <div style="background-image: url('${ctx}/${photo.url}')">
                            </div>
                        </dd>
                        <dt>
                        ${photo.createdTime?date}
                        </dt>
                    </dl>
                </li>
            </#list>
            <div class="clear" style="height: 5px;"></div>
        </ul>

        <div style="height: 20px;"></div>

            <#assign url="album/${album.albumId}"/>
            <#assign params="pwd=${pwd}"/>
            <#if (page.list)?? && page.pages gt 1>
            <ul class="pagination">
                <#if page.hasPreviousPage>
                    <li>
                        <a href="${ctx}/${url}?pageNum=1<#if params?has_content>&${params}</#if>" title="首页">
                            <i class="ace-icon fa fa-angle-double-left"></i>
                        </a>
                    </li>
                    <li>
                        <a href="${ctx}/${url}?pageNum=${page.prePage}<#if params?has_content>&${params}</#if>"
                           title="上一页">
                            <i class="ace-icon fa fa-angle-left"></i>
                        </a>
                    </li>
                </#if>

                <#list page.navigatepageNums as nav>
                    <#if nav == page.pageNum>
                        <li class="active">
                            <a href="javascript:">${nav}</a>
                        </li>
                    <#else>
                        <li>
                            <a href="${ctx}/${url}?pageNum=${nav}<#if params?has_content>&${params}</#if>">${nav}</a>
                        </li>
                    </#if>
                </#list>

                <#if page.hasNextPage>
                    <li>
                        <a href="${ctx}/${url}?pageNum=${page.nextPage}<#if params?has_content>&${params}</#if>"
                           title="下一页">
                            <i class="ace-icon fa fa-angle-right"></i>
                        </a>
                    </li>

                    <li>
                        <a href="${ctx}/${url}?pageNum=${page.pages}<#if params?has_content>&${params}</#if>"
                           title="尾页">
                            <i class="ace-icon fa fa-angle-double-right"></i>
                        </a>
                    </li>
                </#if>
            </ul>
            </#if>
        <#else>
        <div class="empty">没有照片</div>
        </#if>
    </@panel>
</@override>

<@override name="script">
<script src="${ctx}/libs/zoomify/dist/zoomify.min.js"></script>
<script>
    $('.album-list li dl dd').zoomify();
</script>
</@override>

<@extends name="../layout.ftl"/>
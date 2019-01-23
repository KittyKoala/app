<#assign title="视频"/>

<@override name="style">
<style>
    .video-list, .video-list dl, .video-list dt, .video-list dd {
        margin: 0;
        padding: 0;
    }

    .video-list {
        list-style: none;
    }

    .video-list li {
        float: left;
        width: 330px;
        margin-top: 10px;
        margin-right: 5px;
        border: 1px solid #d2d2d2;
        text-align: center;
    }

    .video-list li dl dd {
        width: 300px;
        height: 200px;
        margin: 0 auto;
        margin-top: 10px;
        overflow: hidden;
        position: relative;
    }

    .video-list li dl dd a {
        display: inline-block;
        width: 300px;
        height: 200px;
        background-position: center;
        background-size: cover;
    }

    .video-list .size {
        position: absolute;
        bottom: 10px;
        right: 10px;
        color: #fff;
        font-size: 30px;
    }

    .video-list li dl dt {
        margin-top: 3px;
        margin-bottom: 5px;
        color: #595959;
        line-height: 22px;
        text-align: left;
        margin-left: 13px;
    }

    .video-list li dl dt span {
        color: #999;
        font-size: 13px;
        margin-right: 13px;
        float: right;
    }

    .video-list img {
        width: 300px;
        margin: auto;
    }

    .video-list .title {
        float: left;
        width: 215px;
        height: 25px;
        overflow: hidden;
        text-overflow:ellipsis;
        white-space: nowrap;
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

        <#if page.list?size gt 0>
        <ul class="video-list border">
            <#list page.list as video>
                <#if video.cover?starts_with('http')>
                    <#assign bg_img=video.cover/>
                <#else>
                    <#assign bg_img=ctx + video.cover/>
                </#if>
                <li title="${video.title}">
                    <dl>
                        <dd>
                            <a href="${ctx}/video/${video.videoId}"
                               style="background-image: url('${bg_img}')"></a>
                            <div class="size">${video.viewNum}</div>
                        </dd>
                        <dt>
                            <div class="title">${video.title}</div>
                            <span class="pull-right">${video.createdTime?date}</span>
                        </dt>
                    </dl>
                </li>
            </#list>
            <div class="clear"></div>
        </ul>

        <div style="height: 20px;"></div>

            <#assign url="video"/>
            <#assign params=""/>
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
        <div class="empty">没有视频</div>
        </#if>
    </@panel>
</@override>

<@extends name="../layout.ftl"/>
<#assign title="视频"/>

<@override name="style">
<style>
    .video-list, .video-list li {
        margin: 0;
        padding: 0;
    }

    .video-list {
        list-style: none;
    }

    .video-list li {
        float: left;
        position: relative;
    }

    .video-list li video {
        width: 500px;
        height: 287px;
        margin-left: 6px;
        margin-bottom: 10px;
    }

    .video-list li .title {
        position: absolute;
        left: 10px;
        bottom: 16px;
        font-size: 12px;
        color: rgba(255, 255, 255, 0.7);
    }

    .video-list li .date {
        position: absolute;
        right: 10px;
        bottom: 16px;
        font-size: 12px;
        color: rgba(255, 255, 255, 0.7);
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
                <li>
                    <video controls="controls">
                        <source src="${ctx}/${video.url}" type="video/ogg">
                        <source src="${ctx}/${video.url}" type="video/mp4">
                        您的浏览器不支持播放此视频
                    </video>
                    <div class="title">${video.title}</div>
                    <div class="date">${video.createdTime?date}</div>
                    <div class="clear"></div>
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
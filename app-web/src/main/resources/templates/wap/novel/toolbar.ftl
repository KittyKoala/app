<div class="toolbar">
<#if prevSection??>
    <a href="${ctx}/wap/novel/${novel.novelId}/${prevSection.sectionId}">
        <i class="fa fa-arrow-left"></i>
        上一章
    </a>
<#else>
    <a href="javascript:">没有上一章</a>
</#if>

    <a href="${ctx}/wap/novel/${novel.novelId}" class="text-center">
        <i class="fa fa-th-list"></i>
        章节列表
    </a>

<#if nextSection??>
    <a href="${ctx}/wap/novel/${novel.novelId}/${nextSection.sectionId}" class="text-right">
        下一章
        <i class="fa fa-arrow-right"></i>
    </a>
<#else>
    <#if !novelQueue?? || novelQueue.status=='Y'>
        <a class="text-right pull-btn" href="${ctx}/novel/${novel.novelId}/pull" data-loading-text="更新中">
            更新
            <i class="fa fa-refresh"></i>
        </a>
    <#else>
        <a href="javascript:" class="text-right">${enum('name', 'NovelQueueStatus', novelQueue.status)}</a>
    </#if>
</#if>
</div>
<div class="border toolbar">
<#if prevSection??>
    <a href="${ctx}/novel/${novel.novelId}/${prevSection.sectionId}">
        <i class="fa fa-arrow-left"></i>
        上一章
    </a>
<#else>
    <a href="javascript:">没有上一章</a>
</#if>

<a href="${ctx}/novel/${novel.novelId}" class="text-center">
    <i class="fa fa-th-list"></i>
    章节列表
</a>

<#if nextSection??>
    <a href="${ctx}/novel/${novel.novelId}/${nextSection.sectionId}" class="text-right">
        下一章
        <i class="fa fa-arrow-right"></i>
    </a>
<#else>
    <a href="javascript:" class="text-right">没有下一章</a>
</#if>
</div>
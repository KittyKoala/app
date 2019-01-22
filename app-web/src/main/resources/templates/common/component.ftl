<#--面板-->
<#macro panel style="">
<div class="panel" style="${style}">
    <div class="inner">
        <#nested />
    </div>
    <div class="space-20"></div>
</div>

<script>
    // 动态高度设定
    $(".panel").css({"minHeight": $(window).height() - 320});
</script>
</#macro>

<#--面包屑-->
<#macro breadcrumbs>
    <ul class="breadcrumbs">
        <li>当前位置：</li>
        <li>
            <a href="${ctx}/">首页</a>
        </li>
        <#nested />
        <div class="clear"></div>
    </ul>
</#macro>
<#macro breadcrumb name href="">
    <li class="split">&gt;</li>
    <li>
        <#if href!=''>
            <a href="${href}">${name}</a>
        <#else>
            <span>${name}</span>
        </#if>
    </li>
</#macro>

<#--清除浮动-->
<#macro clear>
    <div class="clear"></div>
</#macro>
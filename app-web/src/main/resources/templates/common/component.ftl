<#--面板-->
<#macro panel bg_img="">
<div class="panel" <#if bg_img != ''>style="background: url('${bg_img}') no-repeat;" </#if>>
    <div class="inner">
        <#nested />
    </div>
    <div class="space-20"></div>
</div>

<script>
    // 动态高度设定
    $(".panel").css({"minHeight": $(window).height() - 330});
</script>
</#macro>

<#--确认按钮-->
<#macro confirm name href title>
<a href="${href}" title="${title}" data-type="confirm">
${name}
</a>
</#macro>

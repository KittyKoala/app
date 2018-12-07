<#macro panel bg_img="">
<div class="panel">
    <div class="inner">
        <#nested />
    </div>
    <div class="space-20"></div>
</div>

<style>
    /*设置背景*/
        <#if bg_img != ''>
        .panel {
            background: url('${bg_img}') no-repeat;
        }
        </#if>
</style>

<script>
    // 动态高度设定
    $(".panel").css({"minHeight": $(window).height() - 330});
</script>
</#macro>
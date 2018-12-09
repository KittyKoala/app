<#--菜单状态-->
<#macro menu_status active opens=[]>
<script>
    $(function () {
        <#if opens?size gt 0>
            <#list opens as open>
        $('#${open}').addClass('open active');
            </#list>
        </#if>
        $('#${active}').addClass('active');

        // 设置面包屑

    })
</script>
</#macro>
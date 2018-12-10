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
    <#local id=func('uuid')/>
<a id="${id}" href="javascript:" title="${title}">
${name}
</a>
<script>
    $("#${id}").click(function (e) {
        e.preventDefault();
        var $table = $(this).parents("table");

        $.messager.confirm("提示", "确定${title}吗?", function () {
            $.get("${href}").success(function (res) {
                if (res.respCo === '0000') {
                    Message.success(res.respMsg);
                    if ($table) {
                        $table.bootstrapTable("refresh");
                    }
                } else {
                    Message.error(res.respMsg);
                }
            }).error(function () {
                Message.error("网络错误，请稍后重试");
            })
        });
        return false;
    });
</script>
</#macro>

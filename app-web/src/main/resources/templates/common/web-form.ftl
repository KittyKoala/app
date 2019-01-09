<#--表单-->
<#macro form action="" id="" action="" method="post" class="" beforeSubmit="" valid_ignore="" rules="" success="" error="">
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<form <#if action!=''>action="${action}"</#if>
      id="${id}"
      method="${method}"
      class="form ${class}">
    <#nested />
</form>

<script>
    $(function () {
        // 表单校验
        var $form = $('#${id}');
        var $btn = $form.find("button[data-type='submit']");

        $form.validate({
            <#if valid_ignore!=''>
                ignore: '${valid_ignore}',
            </#if>
            <#if rules!=''>
                rules: eval('${rules}()'),
            </#if>
            submitHandler: function (form, event) {
                event.preventDefault();

                <#if beforeSubmit!=''>
                    eval('${beforeSubmit}()');
                </#if>

                $btn.button('loading');
                formSubmit($(form), $btn, ${success}<#if error!=''>, ${error}</#if>);
            }
        });
    })
</script>
</#macro>
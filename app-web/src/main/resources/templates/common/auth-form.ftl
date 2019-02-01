<#--表单-->
<#macro form action="" id="" action="" method="post" class="" beforeSubmit="" valid_ignore="" rules="" success="" error="" token=false>
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<form <#if action!=''>action="${action}"</#if>
      id="${id}"
      method="${method}"
      class="form ${class}" xmlns="http://www.w3.org/1999/html">
    <#if token>
        <input type="hidden" name="_token" value="${_token!''}"/>
    </#if>
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

<#--输入框-->
<#macro input label name="" id="" type="text" placeholder="" number=false
required=false min_length=-1 max_length=-1 validator="" remote="" equal_to="" range_length=[]>
<div class="form-group">
    <div class="label">
        <label <#if required>class="required"</#if>>${label}</label>
    </div>
        <input type="${type}"
               <#if id!=''>id="${id}"</#if>
               <#if name!=''>name="${name}"</#if>
    <#if placeholder!=''>
               placeholder="${placeholder}"
    <#else>
               placeholder="请输入${label}"
    </#if>
               <#if required>required</#if>
    <#if min_length!=-1>
               minlength="${min_length}"
    </#if>
    <#if max_length!=-1>
               maxlength="${max_length}"
    </#if>
    <#if validator!=''>
    ${validator}="true"
    </#if>
    <#if remote!=''>
        remote="${remote}"
    </#if>
    <#if equal_to!=''>
        equalTo="${equal_to}"
    </#if>
    <#if range_length?size gt 1>
        rangelength="[${range_length[0]}, ${range_length[1]}]"
    </#if>
    <#if number>
        number="true"
    </#if>
    />
    <#nested />
</div>
</#macro>

<#--文本域-->
<#macro textarea label name="" id="" placeholder=""
required=false min_length=-1 max_length=-1 range_length=[]>
    <div class="form-group">
        <div class="label">
            <label <#if required>class="required"</#if>>${label}</label>
        </div>
        <textarea
               <#if id!=''>id="${id}"</#if>
            <#if name!=''>name="${name}"</#if>
            <#if placeholder!=''>
                placeholder="${placeholder}"
            <#else>
                placeholder="请输入${label}"
            </#if>
            <#if required>required</#if>
            <#if min_length!=-1>
                minlength="${min_length}"
            </#if>
            <#if max_length!=-1>
               maxlength="${max_length}"
        </#if>
        <#if range_length?size gt 1>
            rangelength="[${range_length[0]}, ${range_length[1]}]"
        </#if>
        ></textarea>
    </div>
</#macro>

<#macro captcha label name="" id="" required=true>
    <@input label="${label}" name="${name}" id="${id}" required=required>
    <img class="captcha" onclick="this.src='${ctx}/captcha?r=' + Math.random();" src="${ctx}/captcha"/>
    </@input>
</#macro>

<#--按钮组-->
<#macro actions>
<div class="form-actions">
    <#nested />
</div>
</#macro>

<#--按钮-->
<#macro button name id="" loading_text="" type="button" icon="">
<button <#if id!=''>id="${id}"</#if>
        class="btn" data-type="${type}"
    <#if loading_text!=''>
        data-loading-text="${loading_text}"
    <#else>
        data-loading-text="正在${name}..."
    </#if>
>
    <#if icon!=''>
        <i class="fa ${icon}"></i>
    </#if>
${name}
</button>
</#macro>
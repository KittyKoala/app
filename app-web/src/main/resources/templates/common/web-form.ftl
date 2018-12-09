<#--web表单-->
<#macro web_form action="" id="" action="" method="post" class="">
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
        /**
         * 重置
         */
        $("#${id}").find("button[type='reset']").click(function () {
            $("#${id}").validate().resetForm();
        });
    })
</script>
</#macro>

<#--web输入框-->
<#macro web_input label name="" id="" type="text" required=true placeholder="">
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
    >
    <#nested />
</div>
</#macro>

<#macro web_captcha label name="" id="">
    <@web_input label="${label}" name="${name}" id="${id}">
    <img class="captcha" onclick="this.src='${ctx}/captcha?r=' + Math.random();" src="${ctx}/captcha"/>
    </@web_input>
</#macro>

<#--web按钮组-->
<#macro web_actions>
<div class="form-actions">
    <#nested />
</div>
</#macro>

<#--web按钮-->
<#macro web_button name id="" loading_text="" type="button" icon="">
<button <#if id!=''>id="${id}"</#if>
        class="btn" type="${type}"
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
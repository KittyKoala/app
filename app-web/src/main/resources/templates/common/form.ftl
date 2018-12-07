<#--web表单-->
<#macro web_form action="" id="" action="" method="post" class="">
    <#if id==''>
        <#local id=app('uuid')/>
    </#if>
<form <#if action!=''>action="${action}"</#if>
      id="${id}"
      method="${method}"
      class="form ${class}">
    <#nested />
</form>

<style>
    .btn {
        height: 34px;
        line-height: 22px;
        padding: 5px 15px;
        background: none;
        border-radius: 17px;
        border: 1px solid #ddd;
        color: #ccc;
        font-size: 13px;
        cursor: pointer;
        box-shadow: none !important;
        outline: none;
        letter-spacing: 2px;
    }

    .form .form-group {
        position: relative;
        margin-top: 30px;
    }

    .form .captcha {
        position: absolute;
        right: 10px;
        top: 2px;
        height: 30px;
        line-height: 20px;
        width: 80px;
        padding: 0;
        cursor: pointer;
    }

    .form .label {
        display: inline-block;
        _zoom: 1;
        *display: inline;
    }

    .form label {
        display: inline-block;
        width: 180px;
        text-align: right;
    }

    .form .label:after {
        content: '：';
    }

    .form .required:after {
        content: '*';
        color: red;
    }

    .form input {
        display: inline-block;
        width: 400px;
        height: 34px;
        line-height: 34px;
        box-sizing: border-box;
        outline: none;
        font-size: 14px;
        padding: 4px 10px;
        margin-right: 10px;
        background: rgba(0, 0, 0, 0.2);
        border: 1px solid #aaa;
        color: #fff;
    }

    .form .form-actions {
        margin-top: 50px;
        margin-left: 206px;
    }

    .form .form-actions button {
        margin-right: 15px;
    }

    .form div.error {
        text-indent: 205px;
        color: #ff5151;
        font-size: 13px;
        margin-top: 3px;
    }
</style>

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
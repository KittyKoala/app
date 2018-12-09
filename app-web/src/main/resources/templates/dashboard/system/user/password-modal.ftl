<#assign modal_title="修改密码" />

<@override name="modal-body">
    <@ca.form id="modal-form" action="${ctx}/dashboard/system/user/password" token=true>
        <input type="hidden" name="userId" value="${user.userId}"/>
        <@ca.select id="areaCode" name="areaCode" label="区号" readonly=true required=true>
            <#assign list=dict('list', 'AREA_CODE')/>
            <#if list?? && list?size gt 0>
                <#list list as dict>
                <option value="${dict.value}" <#if user.areaCode?? && dict.value==user.areaCode>selected</#if>>
                ${dict.dictCode}[${dict.value}]
                </option>
                </#list>
            </#if>
        </@ca.select>
        <@ca.input name="mobileNo" value="${user.mobileNo!''}" label="手机号" readonly=true/>

        <@ca.input id="password" name="password" type="password" label="新密码" required=true/>
        <@ca.input name="rePassword" type="password" label="确认新密码" required=true/>
    </@ca.form>
</@override>

<@override name="modal-footer">
    <@ca.button name="取消" icon="fa-times" dismiss=true/>
    <@ca.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/app/js/dashboard/system/user/password-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>
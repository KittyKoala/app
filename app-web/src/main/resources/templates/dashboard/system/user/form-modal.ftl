<#assign isEdit=userDto.userId??>
<#assign modal_title="${isEdit?string('编辑', '新增')}用户" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/system/user/${isEdit?string('update', 'save')}" table_id="table" token=true>
        <#if isEdit>
            <@input name="userId" label="用户ID" value="${userDto.userId}" readonly=true/>
            <@input type="email" name="email" label="电子邮箱" value="${userDto.email!''}" readonly=true/>
        <#else>
            <@input type="email" name="email" label="电子邮箱" value="${userDto.email!''}" required=true remote="${ctx}/api/validate/email"/>
            <@input type="password" name="password" label="密码" required=true validator="isPassword"/>
        </#if>
        <@input name="name" label="姓名" value="${userDto.name!''}" max_length=20/>
        <@input name="idNo" label="证件号码" value="${userDto.idNo!''}" remote="${ctx}/api/validate/idNo"/>
    </@form>
</@override>

<@override name="modal-footer">
    <@cancel/>
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
<#assign isEdit=role.roleId??>
<#assign modal_title="${isEdit?string('编辑', '新增')}角色" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/system/role/${isEdit?string('update', 'save')}" table_id="table" token=true>
        <#if isEdit>
            <@input name="roleId" label="角色ID" value="${role.roleId}" readonly=true/>
            <@input name="roleCode" label="角色代码" value="${role.roleCode!''}" readonly=true/>
        <#else>
            <@input name="roleCode" label="角色代码" required=true remote="${ctx}/api/validate/roleCode" validator="isRoleCode"/>
        </#if>
        <@input name="roleName" label="角色名称" value="${role.roleName!''}" required=true range_length=[2, 10]/>
    </@form>
</@override>

<@override name="modal-footer">
    <@cancel/>
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
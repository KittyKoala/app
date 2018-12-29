<#assign modal_title="设置角色" />

<@override name="modal-body">
    <@form action="${_baseUrl}/editRole" table_id="table" token=true>
        <@input name="userId" label="用户ID" value="${userDto.userId}" readonly=true/>
        <@input type="email" name="email" label="电子邮箱" value="${userDto.email!''}" readonly=true/>
        <@dual name="roleIds" label="用户角色">
            <#list allRoles as role>
            <option value="${role.roleId}" ${userRoles?seq_contains(role.roleId)?string("selected", "")}>${role.roleName}</option>
            </#list>
        </@dual>
    </@form>
</@override>

<@override name="modal-footer">
    <@cancel/>
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
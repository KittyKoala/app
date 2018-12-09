<#assign isEdit=role.roleId??>
<#assign modal_title="${isEdit?string('编辑角色', '添加新角色')}" />

<@override name="modal-body">
    <@ca.form id="modal-form" action="${ctx}/dashboard/system/role/${isEdit?string('update', 'save')}" token=true>
    <input type="hidden" id="old-roleCode" value="${role.roleCode!''}"/>

    <#if isEdit>
    <input type="hidden" name="roleId" value="${role.roleId}"/>
    </#if>

        <@ca.input id="roleCode" name="roleCode" value="${role.roleCode!''}" label="角色代码" required=true/>
        <@ca.input name="roleName" value="${role.roleName!''}" label="角色名称"/>
    </@ca.form>
</@override>

<@override name="modal-footer">
    <@ca.button name="取消" icon="fa-times" dismiss=true/>
    <@ca.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/app/js/dashboard/system/role/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>
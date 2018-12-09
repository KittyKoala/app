<#assign modal_title="设置角色" />

<@override name="modal-body">
<@ca.form id="modal-form" action="${ctx}/dashboard/system/user/${userId}/roles" token=true>
    <div class="control-group">
        <#list allRoles as role>
            <div class="checkbox">
                <label>
                    <input name="roleIds" type="checkbox" value="${role.roleId}"
                           class="ace" ${userRoles?seq_contains(role.roleId)?string("checked", "")}/>
                    <span class="lbl"> ${role.roleName} </span>
                </label>
            </div>
        </#list>
    </div>
</@ca.form>
</@override>

<@override name="modal-footer">
    <@ca.button name="取消" icon="fa-times" dismiss=true/>
    <@ca.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/app/js/dashboard/system/user/roles-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="设置角色权限" />

<link rel="stylesheet" href="${ctx}/libs/ztree/css/zTreeStyle.css"/>

<@override name="modal-body">
    <@ca.form id="modal-form" action="${ctx}/dashboard/system/role/${roleId}/menus" token=true>
            <input type="hidden" name="menuIds" id="menuIds"/>
            <div class="control-group">
                <div>
                    <ul id="tree" class="ztree"></ul>
                </div>
            </div>
    </@ca.form>
</@override>

<@override name="modal-footer">
    <@ca.button name="取消" icon="fa-times" dismiss=true/>
    <@ca.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script>
    var zNodes = [
        <#list allMenus as menu>
            {
                id:${menu.menuId},
                pId:${menu.pid},
                name: "${menu.menuName}",
                open: true
                ${(roleMenus?? && roleMenus?seq_contains(menu.menuId))?string(", checked:true", "")}
            },
        </#list>];
</script>
<script src="${ctx}/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/libs/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${ctx}/app/js/dashboard/system/role/menus-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>
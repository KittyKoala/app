<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=menu.menuId??>
<#assign modal_title="${isEdit?string('修改菜单', '添加菜单')}" />

<@override name="modal-body">
    <@ca.form id="modal-form" action="${ctx}/dashboard/system/menu/${isEdit?string('update', 'save')}" token=true>
        <#if isEdit>
        <input type="hidden" name="menuId" value="${menu.menuId!''}"/>
        </#if>
        <#if parentMenu??>
        <input type="hidden" name="parentCode" value="${(parentMenu.menuCode)!''}">
            <@ca.input name="" value="${parentMenu.menuName!''}" label="上级菜单" readonly=true/>
        </#if>

        <@ca.input id="menuCode" name="menuCode" value="${menu.menuCode!''}" label="菜单代码" required=true placeholder="格式参考:SYSTEM_USER"/>
        <@ca.input name="menuName" value="${menu.menuName!''}" label="菜单名称" required=true/>
        <#if isEdit>
        <input type="hidden" id="old-menuCode" value="${menu.menuCode}"/>
        </#if>
        <@ca.input name="url" value="${menu.url!''}" label="菜单地址" placeholder="格式参考:system/user"/>
        <@ca.input name="sort" value="${menu.sort!'0'}" label="排序" required=true placeholder="0排在最上面"/>
        <@ca.input name="icon" value="${menu.icon!''}" label="图标" placeholder="格式参考:menu-icon fa fa-dashboard"/>
    </div>
    </@ca.form>
</@override>

<@override name="modal-footer">
    <@ca.button name="取消" icon="fa-times" dismiss=true/>
    <@ca.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/app/js/dashboard/system/menu/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>
<#assign isEdit=menu.menuId??>
<#assign modal_title="${isEdit?string('编辑', '新增')}菜单" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/system/menu/${isEdit?string('update', 'save')}" token=true>
        <#if parentMenu??>
            <@input type="hidden" name="parentCode" label="父菜单代码" value="${parentMenu.menuCode}"/>
            <@input label="父菜单" value="${parentMenu.menuName}" readonly=true/>
        </#if>
        <#if isEdit>
            <@input type="hidden" name="menuId" label="菜单ID" value="${menu.menuId}"/>
            <@input name="menuCode" label="菜单代码" value="${menu.menuCode!''}" readonly=true/>
        <#else>
            <@input name="menuCode" label="菜单代码" required=true placeholder="格式参考:SYSTEM_USER"
            remote="${ctx}/api/validate/menuCode" validator="isMenuCode"/>
        </#if>
        <@input name="menuName" label="菜单名称" value="${menu.menuName!''}" required=true range_length=[2, 10]/>
        <@input name="url" label="菜单地址" value="${menu.url!''}" range_length=[0, 32] placeholder="格式参考:dashboard/system/user"/>
        <@input name="sort" label="排序" value="${menu.sort!''}" placeholder="数字小的排在最上面" number=true/>
        <@input name="icon" label="图标" value="${menu.icon!''}" placeholder="格式参考:menu-icon fa fa-dashboard"/>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
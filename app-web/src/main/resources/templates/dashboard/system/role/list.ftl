<@override name="main">
    <@search_form id="form">
        <@input name="roleCode" label="角色代码"/>
        <@input name="roleName" label="角色名称"/>

        <@form_actions>
            <@query table_id="table"/>
            <@edit table_id="table" url="${_baseUrl}/{{roleId}}/edit"/>
            <@create url="${_baseUrl}/create"/>
            <@delete table_id="table" url="${_baseUrl}/delete?roleIds={{roleId}}"/>
            <@restore table_id="table" url="${_baseUrl}/restore?roleIds={{roleId}}"/>
            <@modal name="查看授权用户" table_id="table" url="${_baseUrl}/{{roleId}}/users"/>
            <@modal name="设置权限" table_id="table" url="${_baseUrl}/{{roleId}}/menus"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="roleId" title="角色ID" auto_hide=true/>
        <@th field="roleCode" title="角色代码"/>
        <@th field="roleName" title="角色名称"/>
        <@thYesNo field="isDeleted" title="逻辑删除"/>
        <@thDatetime field="createdTime" title="创建时间" auto_hide=true/>
    </@table>
</@override>

<@extends name="../../layout.ftl"/>
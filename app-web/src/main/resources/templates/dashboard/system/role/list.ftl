<#assign ctx="${(rca.contextPath)!''}">
<#assign subtitle="系统"/>
<#assign title="角色管理"/>
<#assign baseUrl="${ctx}/dashboard/system/role"/>

<@override name="main">
    <@ca.form class="col-xs-12 fa-border radius-base">
        <@ca.input name="roleCode" label="角色代码" inline=true/>
        <@ca.input name="roleName" label="角色名称" inline=true/>

        <@ca.form_actions background=false>
            <@ca.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/>
            <@ca.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/>
            <@ca.link name="添加新角色" class="btn btn-sm btn-skin" href="${baseUrl}/create" modal="myModal"/>
        </@ca.form_actions>
    </@ca.form>

    <@ca.table url="${baseUrl}/list">
        <@ca.th field="roleId" title="角色ID" class="hidden-sm hidden-xs" sortable=true/>
        <@ca.th field="roleCode" title="角色代码" sortable=true/>
        <@ca.th field="roleName" title="角色名称" sortable=true/>
        <@ca.th field="isDeleted" title="逻辑删除" render=true>
            <#include "delete.ftl"/>
        </@ca.th>
        <@ca.th field="createdTime" title="创建时间" sortable=true render=true class="hidden-sm hidden-xs">
            <@ca.thFormat type="datetime"/>
        </@ca.th>
        <@ca.th title="操作" render=true>
            <#include "operation.ftl"/>
        </@ca.th>
    </@ca.table>
</@override>

<@override name="script">
<script src="${ctx}/app/js/dashboard/system/role/list.js"></script>
</@override>

<@extends name="../../layout.ftl"/>
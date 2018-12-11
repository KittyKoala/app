<#assign ctx="${(rcontextPath)!''}">

<@override name="main">
    <@search_form id="form">
        <@input name="email" label="电子邮箱"/>
        <@input name="name" label="姓名"/>
        <@input name="idNo" label="证件号码"/>
        <@date name="beginDate" label="注册开始时间"/>
        <@date name="endDate" label="注册结束时间"/>

        <@form_actions>
            <@query table_id="table"/>
            <@reset/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form">
        <@th field="email" title="电子邮箱"/>
        <@thYesNo field="enableEmailNotice" title="启用邮件通知"/>
        <@th field="name" title="姓名"/>
        <@th field="idNo" title="证件号码"/>
        <@th field="ipAddress" title="注册IP"/>
        <@th field="isDeleted" title="逻辑删除" render=true>
            <#--<#include "delete.ftl"/>-->
        </@th>
        <@thDatetime field="createdTime" title="注册时间"/>
        <@th title="操作" render=true>
            <#include "operation.ftl"/>
        </@th>
    </@table>
</@override>

<@extends name="../../layout.ftl"/>
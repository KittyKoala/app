<#assign ctx="${(rca.contextPath)!''}">
<#assign baseUrl="${ctx}/dashboard/system/user"/>

<@override name="main">
    <@search_form id="form">
        <@input name="email" label="电子邮箱"/>
        <@input name="name" label="姓名"/>
        <@input name="idNo" label="证件号码"/>
        <@date name="beginDate" label="注册开始时间"/>
        <@date name="endDate" label="注册结束时间"/>
    
        <@form_actions>
            <@query/>
            <@reset/>
        </@form_actions>
    </@search_form>
</@override>

<@override name="script">
<@menu_status opens=["SYSTEM"] active="SYSTEM_USER"/>
</@override>

<@extends name="../../layout.ftl"/>
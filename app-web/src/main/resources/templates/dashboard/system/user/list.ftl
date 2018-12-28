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
            <@edit table_id="table" url="${ctx}/dashboard/system/user/{{userId}}/edit"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="userId" title="用户ID" auto_hide=true/>
        <@th field="email" title="电子邮箱"/>
        <@th field="name" title="姓名"/>
        <@th field="idNo" title="证件号码" auto_hide=true/>
        <@th field="ipAddress" title="注册IP" auto_hide=true/>
        <@thYesNo field="isDeleted" title="逻辑删除"/>
        <@thDatetime field="createdTime" title="注册时间" auto_hide=true/>
    </@table>
</@override>

<@extends name="../../layout.ftl"/>
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
        <@thYesNo field="isDeleted" title="逻辑删除"/>
        <@thDatetime field="createdTime" title="注册时间"/>
        <@thOperations name="编辑" href="${_baseUrl}/{{row.userId}}/edit" icon="fa-pencil">
            <@operation name="设置角色" href="${_baseUrl}/{{row.userId}}/edit"/>
            <@operation name="修改密码" href="${_baseUrl}/{{row.userId}}/password"/>
            <@operation name="物理删除" href="${_baseUrl}/{{row.userId}}/remove"/>
        </@thOperations>
    </@table>
</@override>

<@extends name="../../layout.ftl"/>
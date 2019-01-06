<@override name="main">
    <@search_form id="form">
        <@input name="openid" label="openid"/>
        <@input name="content" label="内容"/>
        <@date name="beginDate" label="开始日期"/>
        <@date name="endDate" label="结束日期"/>

        <@form_actions>
            <@query table_id="table"/>
            <@edit table_id="table" url="${_baseUrl}/{{id}}/edit"/>
            <@delete table_id="table" url="${_baseUrl}/delete?ids={{id}}"/>
            <@restore table_id="table" url="${_baseUrl}/restore?ids={{id}}"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="id" title="ID" auto_hide=true/>
        <@th field="openid" title="openid"/>
        <@th field="content" title="内容"/>
        <@thYesNo field="isDeleted" title="逻辑删除" auto_hide=true/>
        <@thDatetime field="createdTime" title="创建时间" auto_hide=true/>
    </@table>
</@override>

<@extends name="../../layout.ftl"/>
<@override name="main">
    <@search_form id="form">
        <@input name="description" label="描述" placeholder="支持模糊查询"/>

        <@form_actions>
            <@query table_id="table"/>
            <@edit table_id="table" url="${_baseUrl}/{{photoId}}/edit"/>
            <@create url="${_baseUrl}/create"/>
            <@delete table_id="table" url="${_baseUrl}/delete?photoIds={{photoId}}"/>
            <@restore table_id="table" url="${_baseUrl}/restore?photoIds={{photoId}}"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="albumId" title="相册ID" auto_hide=true/>
        <@th field="photoId" title="照片ID" auto_hide=true/>
        <@th field="description" title="描述"/>
        <@th field="url" title="照片" render=true auto_hide=true>
        <a href="${ctx}/{{value}}" target="_blank">查看</a>
        </@th>
        <@th field="sort" title="排序" auto_hide=true/>
        <@thYesNo field="isDeleted" title="逻辑删除" auto_hide=true/>
        <@thDatetime field="createdTime" title="创建时间" auto_hide=true/>
    </@table>
</@override>

<@extends name="../../layout.ftl"/>
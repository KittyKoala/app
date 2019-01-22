<@override name="main">
    <@search_form id="form">
        <@input name="userId" label="用户ID"/>
        <@input name="title" label="标题" placeholder="支持模糊查询"/>

        <@form_actions>
            <@query table_id="table"/>
            <@edit table_id="table" url="${_baseUrl}/{{videoId}}/edit"/>
            <@create url="${_baseUrl}/create"/>
            <@delete table_id="table" url="${_baseUrl}/delete?videoIds={{videoId}}"/>
            <@restore table_id="table" url="${_baseUrl}/restore?videoIds={{videoId}}"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="videoId" title="视频ID" auto_hide=true/>
        <@th field="userId" title="用户ID" auto_hide=true/>
        <@th field="title" title="标题" render=true>
        <a href="${ctx}/video/{{row.videoId}}" target="_blank">
            {{value}}
        </a>
        </@th>
        <@th field="cover" title="封面" sortable=true render=true>
        <a href="${ctx}/{{value}}" target="_blank">查看</a>
        </@th>
        <@th field="viewNum" title="查看量" auto_hide=true/>
        <@thYesNo field="isDeleted" title="逻辑删除" auto_hide=true/>
        <@thDatetime field="createdTime" title="创建时间" auto_hide=true/>
    </@table>
</@override>

<@extends name="../../layout.ftl"/>
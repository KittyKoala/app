<@override name="main">
    <@search_form id="form">
        <@input name="userId" label="用户ID"/>
        <@input name="albumName" label="相册名称" placeholder="支持模糊查询"/>

        <@form_actions>
            <@query table_id="table"/>
            <@edit table_id="table" url="${_baseUrl}/{{albumId}}/edit"/>
            <@create url="${_baseUrl}/create"/>
            <@delete table_id="table" url="${_baseUrl}/delete?albumIds={{albumId}}"/>
            <@restore table_id="table" url="${_baseUrl}/restore?albumIds={{albumId}}"/>
            <@button id="photoBtn" name="查看" icon="fa-eye"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="albumId" title="相册ID" auto_hide=true/>
        <@th field="userId" title="用户ID"/>
        <@th field="albumName" title="相册名称"/>
        <@th field="cover" title="封面" render=true auto_hide=true>
        {{if startWith(value, "http")}}
        <a href="{{value}}" target="_blank">查看</a>
        {{else}}
        <a href="${ctx}/{{value}}" target="_blank">查看</a>
        {{/if}}
        </@th>
        <@th field="password" title="密码"/>
        <@th field="size" title="大小"/>
        <@th field="sort" title="排序" auto_hide=true/>
        <@thYesNo field="isDeleted" title="逻辑删除" auto_hide=true/>
        <@thDatetime field="createdTime" title="创建时间" auto_hide=true/>
    </@table>
</@override>

<@override name="script">
<script>
    $(function () {
        $("#photoBtn").click(function () {
            var $table = $("#table");

            var selections = $table.bootstrapTable('getSelections');
            if (selections.length !== 1) {
                Message.warning("请选择一条记录");
                return false;
            }

            window.location.href = '${_baseUrl}/' + selections[0].albumId;
            return false;
        });
    })
</script>
</@override>


<@extends name="../../layout.ftl"/>
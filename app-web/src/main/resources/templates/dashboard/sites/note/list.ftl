<@override name="main">
    <@search_form id="form">
        <@input name="email" label="电子邮箱"/>
        <@input name="ipAddress" label="IP地址"/>

        <@form_actions>
            <@query table_id="table"/>
            <@button id="reply" name="回复" icon="fa-comments-o"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="noteId" title="留言ID" auto_hide=true/>
        <@th field="email" title="电子邮箱"/>
        <@th field="ipAddress" title="IP地址" auto_hide=true/>
        <@th field="content" title="留言内容"/>
        <@thYesNo field="isReply" title="是否回复"/>
        <@thYesNo field="isDeleted" title="逻辑删除" auto_hide=true/>
        <@thDatetime field="createdTime" title="创建时间" auto_hide=true/>
    </@table>
</@override>

<@override name="script">
    <script>
        $(function () {
            $("#reply").click(function () {
                var $table = $("#table");

                var selections = $table.bootstrapTable('getSelections');
                if (selections.length !== 1) {
                    Message.warning("请选择一条记录");
                    return false;
                }

                var row = selections[0];
                $('#myModal').modal({
                    backdrop: 'static',
                    keyboard: false,
                    show: true,
                    remote: "${_baseUrl}/" + row.noteId + "/reply"
                })
            });
        })
    </script>
</@override>

<@extends name="../../layout.ftl"/>
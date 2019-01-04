<@override name="main">
    <@search_form id="form">
        <@selectEnum name="source" label="来源" enum_key="NovelSource"/>
        <@input name="code" label="小说代码"/>
        <@input name="name" label="小说名称" placeholder="支持模糊查询"/>
        <@input name="author" label="作者"/>

        <@form_actions>
            <@query table_id="table"/>
            <@edit table_id="table" url="${_baseUrl}/{{novelId}}/edit"/>
            <@create url="${_baseUrl}/create"/>
            <@delete table_id="table" url="${_baseUrl}/delete?novelIds={{novelId}}"/>
            <@restore table_id="table" url="${_baseUrl}/restore?novelIds={{novelId}}"/>
            <@button id="updateBtn" name="更新" icon="fa-refresh"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="novelId" title="小说ID" auto_hide=true/>
        <@thEnum field="source" title="来源" enum_key="NovelSource"/>
        <@th field="code" title="小说代码"/>
        <@th field="name" title="小说名称"/>
        <@th field="author" title="作者"/>
        <@th field="cover" title="封面" render=true>
        {{if value==''}}
        <a href="${ctx}/app/images/nocover.jpg" target="_blank">查看</a>
        {{else}}
        <a href="${ctx}/{{value}}" target="_blank">查看</a>
        {{/if}}
        </@th>
        <@th field="summary" title="描述"/>
        <@thYesNo field="isDeleted" title="逻辑删除"/>
        <@thDatetime field="createdTime" title="创建时间" auto_hide=true/>
    </@table>
</@override>

<@override name="script">
<script>
    $(function () {
        $("#updateBtn").click(function () {
            var $table = $("#table");

            var selections = $table.bootstrapTable('getSelections');
            if (selections.length === 0) {
                Message.warning("至少选择一条记录");
                return false;
            }

            var ids = [];
            for (var i = 0; i < selections.length; i++) {
                ids.push(selections[i].novelId);
            }

            $.messager.confirm("提示", "确定更新所选择的" + ids.length + "本小说吗?", function () {
                $.get("${ctx}/dashboard/sites/novel/pull?novelIds=" + ids.join(",")).success(function (res) {
                    if (res.respCo === '0000') {
                        Message.success(res.respMsg);
                        $table.bootstrapTable("refresh");
                    } else {
                        Message.error(res.respMsg);
                    }
                }).error(function () {
                    Message.error("网络错误，请稍后重试");
                })
            });

            return false;
        });
    })
</script>
</@override>

<@extends name="../../layout.ftl"/>
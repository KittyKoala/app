<@override name="main">
    <@search_form id="form">
        <@selectEnum name="status" label="状态" enum_key="NovelQueueStatus"/>
        <@input name="novelId" label="小说ID"/>

        <@form_actions>
            <@query table_id="table"/>
            <@button id="finished" name="更新完成" icon="fa-refresh"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="queueId" title="队列ID" auto_hide=true/>
        <@th field="novelId" title="小说ID"/>
        <@thEnum field="status" title="状态" enum_key="NovelQueueStatus"/>
        <@thDatetime field="createdTime" title="创建时间"/>
        <@thDatetime field="updatedTime" title="更新时间" auto_hide=true/>
    </@table>
</@override>

<@override name="script">
<script>
    $(function () {
        $("#finished").click(function () {
            var $table = $("#table");

            var selections = $table.bootstrapTable('getSelections');
            if (selections.length === 0) {
                Message.warning("至少选择一条记录");
                return false;
            }

            var ids = [];
            for (var i = 0; i < selections.length; i++) {
                ids.push(selections[i].queueId);
            }

            $.messager.confirm("提示", "确定更新完成所选择的" + ids.length + "个小说队列吗?", function () {
                $.get("${ctx}/dashboard/sites/novelQueue/finished?queueId=" + ids.join(",")).success(function (res) {
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
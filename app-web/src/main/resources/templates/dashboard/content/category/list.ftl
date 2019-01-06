<@override name="main">
    <@search_form id="form">
        <@selectEnum name="categoryType" label="栏目类型" enum_key="CategoryType"/>
        <@input name="categoryCode" label="栏目代码"/>
        <@input name="categoryName" label="栏目名称"/>

        <@form_actions>
            <@query table_id="table"/>
            <@edit table_id="table" url="${_baseUrl}/{{categoryId}}/edit"/>
            <@button id="createBtn" name="新增" icon="fa-pencil-square-o"/>
            <@delete table_id="table" url="${_baseUrl}/delete?categoryIds={{categoryId}}"/>
            <@restore table_id="table" url="${_baseUrl}/restore?categoryIds={{categoryId}}"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="categoryId" title="栏目ID" auto_hide=true/>
        <@thEnum field="categoryType" title="栏目类型" enum_key="CategoryType"/>
        <@th field="parentCode" title="父栏目代码"/>
        <@th field="categoryCode" title="栏目代码"/>
        <@th field="categoryName" title="栏目名称"/>
        <@th field="sort" title="排序" auto_hide=true/>
        <@th field="url" title="路径"/>
        <@thYesNo field="isBlank" title="开启新界面"/>
        <@thYesNo field="isDeleted" title="逻辑删除" auto_hide=true/>
        <@thDatetime field="createdTime" title="创建时间" auto_hide=true/>
    </@table>
</@override>

<@override name="script">
<script>
    $(function () {
        $("#createBtn").click(function () {
            var $table = $("#table");

            var selections = $table.bootstrapTable('getSelections');
            var row = {};
            if (selections.length > 1) {
                Message.warning("最多选择一条记录");
                return false;
            } else if (selections.length === 1) {
                row = selections[0];
            }

            $('#myModal').modal({
                backdrop: 'static',
                keyboard: false,
                show: true,
                remote: "${ctx}/dashboard/content/category/create?parentType=" + row.categoryType + "&parentCode=" + row.categoryCode
            });
            return false;
        });
    })
</script>
</@override>

<@extends name="../../layout.ftl"/>
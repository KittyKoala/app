<@override name="main">
    <@search_form id="form">
        <@selectEnum name="dictType" label="字典类型" enum_key="DictType"/>
        <@input name="dictCode" label="字典代码"/>
        <@input name="value" label="字典的值"/>

        <@form_actions>
            <@query table_id="table"/>
            <@edit table_id="table" url="${_baseUrl}/{{dictId}}/edit"/>
            <@create url="${_baseUrl}/create"/>
            <@delete table_id="table" url="${_baseUrl}/delete?dictIds={{dictId}}"/>
            <@restore table_id="table" url="${_baseUrl}/restore?dictIds={{dictId}}"/>
        </@form_actions>
    </@search_form>

    <@table id="table" url="${_baseUrl}/list" form_id="form" checkbox=true>
        <@th field="dictId" title="字典ID" auto_hide=true/>
        <@thEnum field="dictType" title="字典类型" enum_key="DictType"/>
        <@th field="dictCode" title="字典代码"/>
        <@th field="value" title="字典的值"/>
        <@th field="sort" title="排序" auto_hide=true/>
        <@th field="remark" title="备注" auto_hide=true/>
        <@thYesNo field="isDeleted" title="逻辑删除" auto_hide=true/>
        <@thDatetime field="createdTime" title="创建时间" auto_hide=true/>
    </@table>
</@override>

<@extends name="../../layout.ftl"/>
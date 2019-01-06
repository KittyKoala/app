<#assign modal_title="编辑宝宝点滴" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/sites/record/update" table_id="table" token=true>
        <@input name="id" label="ID" value="${record.id}" readonly=true/>
        <@input label="openid" value="${record.openid!''}" readonly=true/>
        <@input name="content" label="内容" value="${record.content!''}"/>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
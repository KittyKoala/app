<#assign ctx="${(rca.contextPath)!''}"/>
<#assign modal_title="回复${note.email}的留言" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/sites/note/${note.noteId}/reply" table_id="table" token=true>
        <@textarea name="content" label="回复内容" required=true range_length=[1, 512]/>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
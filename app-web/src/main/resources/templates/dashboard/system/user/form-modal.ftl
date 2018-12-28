<#assign isEdit=userDto.userId??>
<#assign modal_title="${isEdit?string('编辑用户', '添加新用户')}" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/system/user/${isEdit?string('update', 'save')}" table_id="table">
        <@input name="userId" label="用户ID" value="${userDto.userId}" readonly=true/>
        <@input label="电子邮箱" value="${userDto.email!''}" readonly=true/>
        <@input name="name" label="姓名" value="${userDto.name!''}" required=true/>
        <@input name="idNo" label="证件号码" value="${userDto.idNo}" required=true/>
    </@form>
</@override>

<@override name="modal-footer">
    <@cancel/>
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
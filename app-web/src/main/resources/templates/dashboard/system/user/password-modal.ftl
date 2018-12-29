<#assign modal_title="修改密码" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/system/user/editPassword" table_id="table" token=true>
        <@input name="userId" label="用户ID" value="${userDto.userId}" readonly=true/>
        <@input type="email" name="email" label="电子邮箱" value="${userDto.email!''}" readonly=true/>
        <@input id="password" type="password" name="password" label="新密码" required=true validator="isPassword"/>
        <@input type="password" label="确认密码" required=true equal_to="#password"/>
    </@form>
</@override>

<@override name="modal-footer">
    <@cancel/>
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="角色用户" />

<@override name="modal-body">
<table class="table table-hover table-striped">
    <thead>
    <tr>
        <th>用户ID</th>
        <th>手机号</th>
        <th>注册时间</th>
        <th>更新时间</th>
    </tr>
    </thead>
    <tbody>
        <#list users as user>
        <tr>
            <td>${user.userId}</td>
            <td>${user.areaCode}-${user.mobileNo}</td>
            <td>${user.createdTime?datetime}</td>
            <td>${user.updatedTime?datetime}</td>
        </tr>
        </#list>
    </tbody>
</table>
</@override>

<@override name="modal-footer">
    <@ca.button name="取消" icon="fa-times" dismiss=true/>
</@override>

<@extends name="../../modal-layout.ftl"/>
<#assign modal_title="查看授权用户" />

<@override name="modal-body">
<table class="table table-hover table-striped">
    <thead>
    <tr>
        <th>用户ID</th>
        <th>电子邮箱</th>
        <th>注册时间</th>
    </tr>
    </thead>
    <tbody>
        <#list users as user>
        <tr>
            <td>${user.userId}</td>
            <td>${user.email}</td>
            <td>${user.createdTime?datetime}</td>
        </tr>
        </#list>
    </tbody>
</table>
</@override>

<@override name="modal-footer">
    <@cancel/>
</@override>

<@extends name="../../modal-layout.ftl"/>
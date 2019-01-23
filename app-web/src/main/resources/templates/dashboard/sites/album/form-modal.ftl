<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=album.albumId??>
<#assign modal_title="${isEdit?string('编辑', '新增')}相册" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/sites/album/${isEdit?string('update', 'save')}" table_id="table" token=true>
        <#if isEdit>
            <@input name="albumId" label="相册ID" value="${album.albumId}" readonly=true/>
            <@input label="用户ID" value="${album.userId!''}" readonly=true/>
        </#if>
        <@input name="albumName" label="相册名称" value="${album.albumName!''}" required=true range_length=[1, 64]/>
        <@input name="password" label="密码" value="${album.password!''}" required=false range_length=[6, 6]/>
        <@input name="sort" label="排序" value="${album.sort!''}" placeholder="数字小的排在最上面" number=true/>
        <@file name="file" label="封面" required=true remark="请上传png、gif、jpg、jpeg格式的图片文件，文件大小不能超过2MB。建议上传一张 300*200 像素或等比例的图片。"/>
        <@input name="cover" label="封面地址" value="${album.cover!''}" range_length=[1, 256]/>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
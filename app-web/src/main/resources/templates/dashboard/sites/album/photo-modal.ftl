<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=albumPhoto.photoId??>
<#assign modal_title="${isEdit?string('编辑', '新增')}照片" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/sites/album/${albumId}/${isEdit?string('update', 'save')}" table_id="table" token=true>
        <@input label="相册ID" value="${albumId}" readonly=true/>
        <#if isEdit>
            <@input name="photoId" label="照片ID" value="${albumPhoto.photoId}" readonly=true/>
        </#if>
        <@input name="description" label="描述" value="${albumPhoto.description!''}" required=false range_length=[1, 64]/>
        <@input name="sort" label="排序" value="${albumPhoto.sort!''}" placeholder="数字小的排在最上面" number=true/>
        <@file name="file" label="照片" size=10485760 required=true remark="请上传png、gif、jpg、jpeg格式的图片文件，文件大小不能超过10MB。"/>
        <@input name="url" label="照片地址" value="${albumPhoto.url!''}" range_length=[1, 256]/>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
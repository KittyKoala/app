<#assign ctx="${(rca.contextPath)!''}"/>
<#assign isEdit=video.videoId??/>
<#assign modal_title="${isEdit?string('编辑', '新增')}视频" />

<@override name="modal-body">
    <@form action="${ctx}/dashboard/sites/video/${isEdit?string('update', 'save')}" table_id="table" token=true>
        <#if isEdit>
            <@input name="videoId" label="视频ID" value="${video.videoId}" readonly=true/>
            <@input label="用户ID" value="${video.userId!''}" readonly=true/>
        </#if>
        <@input name="title" label="标题" value="${video.title!''}" required=true range_length=[1, 64]/>
        <@file name="file" label="封面" required=true remark="请上传png、gif、jpg、jpeg格式的图片文件，文件大小不能超过2MB。建议上传一张 300*200 像素或等比例的图片。"/>
        <@input name="content" label="视频代码" value="${(video.content!'')?html}" required=true range_length=[1, 512]/>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
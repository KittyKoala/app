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
        <@video_file name="file" label="视频" required=true remark="视频大小不能超过100MB。"/>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
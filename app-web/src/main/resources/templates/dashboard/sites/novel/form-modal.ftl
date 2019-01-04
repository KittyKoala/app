<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=novel.novelId??>
<#assign modal_title="${isEdit?string('编辑', '新增')}小说" />

<script>
    /**
     * 自定义校验规则
     *
     * @returns 返回jquery validate规则对象
     */
    function myRules() {
        return {
            source: {
                remote: {
                    url: "${ctx}/api/validate/novelCode",
                    type: 'get',
                    data: {
                        'oldSource': "${novel.source!''}",
                        'oldCode': "${novel.code!''}",
                        'source': function () {
                            return $('#source').val()
                        },
                        'code': function () {
                            return $('#code').val()
                        }
                    }
                }
            },
            code: {
                remote: {
                    url: "${ctx}/api/validate/novelCode",
                    type: 'get',
                    data: {
                        'oldSource': "${novel.source!''}",
                        'oldCode': "${novel.code!''}",
                        'source': function () {
                            return $('#source').val()
                        },
                        'code': function () {
                            return $('#code').val()
                        }
                    }
                }
            }
        };
    }
</script>

<@override name="modal-body">
    <@form action="${ctx}/dashboard/sites/novel/${isEdit?string('update', 'save')}" table_id="table" token=true rules="myRules">
        <#if isEdit>
            <@input name="novelId" label="小说ID" value="${novel.novelId}" readonly=true/>
        </#if>
        <@selectEnum id="source" name="source" value="${novel.source!''}" label="来源" enum_key="NovelSource" required=true/>
        <@input id="code" name="code" value="${novel.code!''}" label="小说代码" required=true range_length=[1, 20]/>
        <@input name="name" label="小说名称" value="${novel.name!''}" required=true range_length=[1, 20]/>
        <@input name="author" label="作者" value="${novel.author!''}" required=true range_length=[1, 20]/>
        <@input name="summary" label="描述" value="${novel.summary!''}" required=true range_length=[0, 256]/>
        <@file name="file" label="封面" required=true remark="请上传png、gif、jpg、jpeg格式的图片文件，文件大小不能超过2MB。建议上传一张 124*154 像素或等比例的图片。"/>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
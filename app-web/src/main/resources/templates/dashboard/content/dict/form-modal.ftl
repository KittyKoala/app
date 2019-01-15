<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=dict.dictId??>
<#assign modal_title="${isEdit?string('编辑', '新增')}字典" />

<script>
    /**
     * 自定义校验规则
     *
     * @returns 返回jquery validate规则对象
     */
    function myRules() {
        return {
            dictType: {
                remote: {
                    url: "${ctx}/api/validate/dictCode",
                    type: 'get',
                    data: {
                        'oldDictType': "${dict.dictType!''}",
                        'oldDictCode': "${dict.dictCode!''}",
                        'dictType': function () {
                            return $('#dictType').val()
                        },
                        'dictCode': function () {
                            return $('#dictCode').val()
                        }
                    }
                }
            },
            dictCode: {
                remote: {
                    url: "${ctx}/api/validate/dictCode",
                    type: 'get',
                    data: {
                        'oldDictType': "${dict.dictType!''}",
                        'oldDictCode': "${dict.dictCode!''}",
                        'dictType': function () {
                            return $('#dictType').val()
                        },
                        'dictCode': function () {
                            return $('#dictCode').val()
                        }
                    }
                }
            }
        };
    }
</script>

<@override name="modal-body">
    <@form action="${ctx}/dashboard/content/dict/${isEdit?string('update', 'save')}" table_id="table" token=true rules="myRules">
        <#if isEdit>
            <@input name="dictId" label="字典ID" value="${dict.dictId}" readonly=true/>
        </#if>
        <@selectEnum id="dictType" name="dictType" value="${dict.dictType!''}" label="字典类型" enum_key="DictType" required=true empty=false/>
        <@input id="dictCode" name="dictCode" value="${dict.dictCode!''}" label="字典代码" required=true/>
        <@input name="value" label="字典的值" value="${dict.value!''}" required=true range_length=[1, 128]/>
        <@input name="sort" label="排序" value="${dict.sort!''}" placeholder="数字小的排在最上面" number=true/>
        <@input name="remark" label="备注" value="${dict.remark!''}" range_length=[0, 128]/>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
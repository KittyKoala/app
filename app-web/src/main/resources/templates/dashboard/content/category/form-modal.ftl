<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=category.categoryId??>
<#assign modal_title="${isEdit?string('编辑', '新增')}栏目" />

<script>
    /**
     * 自定义校验规则
     *
     * @returns 返回jquery validate规则对象
     */
    function myRules() {
        return {
            categoryType: {
                remote: {
                    url: "${ctx}/api/validate/categoryCode",
                    type: 'get',
                    data: {
                        'oldCategoryType': "${category.categoryType!''}",
                        'oldCategoryCode': "${category.categoryCode!''}",
                        'categoryType': function () {
                            return $('#categoryType').val()
                        },
                        'categoryCode': function () {
                            return $('#categoryCode').val()
                        }
                    }
                }
            },
            categoryCode: {
                remote: {
                    url: "${ctx}/api/validate/categoryCode",
                    type: 'get',
                    data: {
                        'oldCategoryType': "${category.categoryType!''}",
                        'oldCategoryCode': "${category.categoryCode!''}",
                        'categoryType': function () {
                            return $('#categoryType').val()
                        },
                        'categoryCode': function () {
                            return $('#categoryCode').val()
                        }
                    }
                }
            }
        };
    }
</script>

<@override name="modal-body">
    <@form action="${ctx}/dashboard/content/category/${isEdit?string('update', 'save')}" table_id="table" token=true rules="myRules">
        <#if isEdit>
            <@input name="categoryId" label="栏目ID" value="${category.categoryId}" readonly=true/>
        </#if>
        <#if parentCategory??>
        <input type="hidden" name="parentCode" value="${parentCategory.categoryCode}"/>
        <input type="hidden" id="categoryType" name="categoryType" value="${parentCategory.categoryType}"/>
            <@input label="父栏目" value="${parentCategory.categoryName!''}" readonly=true/>
            <@selectEnum label="栏目类型" enum_key="CategoryType" value="${parentCategory.categoryType}" readonly=true empty=false/>
        <#elseif isEdit>
        <input type="hidden" id="categoryType" name="categoryType" value="${category.categoryType}"/>
            <@selectEnum label="栏目类型" enum_key="CategoryType" value="${category.categoryType}" readonly=true empty=false/>
        <#else>
            <@selectEnum id="categoryType" name="categoryType" label="栏目类型" enum_key="CategoryType" required=true empty=false/>
        </#if>
        <@input id="categoryCode" name="categoryCode" value="${category.categoryCode!''}" label="栏目代码" required=true/>
        <@input name="categoryName" label="栏目名称" value="${category.categoryName!''}" required=true range_length=[1, 128]/>
        <@input name="sort" label="排序" value="${category.sort!''}" placeholder="数字小的排在最上面" number=true/>
        <@input name="url" label="路径" value="${category.url!''}" range_length=[0, 128]/>
        <@select name="isBlank" label="开启新界面">
        <option value="0" <#if category.isBlank?? && category.isBlank==0>selected</#if>>不开启</option>
        <option value="1" <#if category.isBlank?? && category.isBlank==1>selected</#if>>开启</option>
        </@select>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
</@override>

<@extends name="../../modal-layout.ftl"/>
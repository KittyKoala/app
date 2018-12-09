<#--表格-->
<#macro table url id="table" pagination=true undefined_text="" form_id="form">
<div class="form-table-space"></div>
<table id="${id}" data-toggle="table" data-url="${url}" data-pagination="${pagination?string}"
       data-side-pagination="server" data-undefined-text="${undefined_text}" data-striped="true"
       data-form-id="${form_id}" data-click-to-select="true">
    <thead>
    <tr>
        <#nested/>
    </tr>
    </thead>
</table>
<script>
    $(function () {
        $('#${id}').bootstrapTable();
    })
</script>
</#macro>

<#--表格的行-->
<#macro th title="" field="" class="" sortable=false render=false checkbox=false>
<th data-field="${field}" class="${class}"
    <#if field!='' && sortable>
    data-sortable="${sortable?c}"
    </#if>
    <#if checkbox>
    data-checkbox="true"
    </#if>
    <#if render>
        <#local uuid=func('uuid', 'th')/>
        <#local formatter=uuid + "Format"/>
    data-formatter="${formatter}"
    </#if>
>
    ${title}
    <#if render>
        <div id="${uuid}" class="hidden">
            <#nested/>
        </div>
        <script>
            function ${formatter}(value, row, index) {
                var data = {"value": value, "row": row, "index": index};
                return template('${uuid}', data);
            }
        </script>
    </#if>
</th>
</#macro>

<#--表格的格式化-->
<#macro thFormat type="" enum_key="" dict_type="" category_type="" show_code=false table_id="">
    <#if type="rowNum">
        <#local uuid=func('uuid', 'rowNum')/>
    <script id="${uuid}">
        template.helper('${uuid}', function (index) {
            var options = $("#${table_id}").bootstrapTable("getOptions");
            return (options.pageNumber - 1) * options.pageSize + index * 1 + 1;
        });
    </script>
    {{index | ${uuid}}}
    <#elseif type=="enum">
        <#local uuid=func('uuid', 'enum')/>
    <script id="${uuid}">
        var obj${uuid} = {};
            <#local map=enum('map', enum_key)/>
            <#if map?? && map?size gt 0>
                <#list map?keys as key>
                obj${uuid}["${key}"] = "${map[key]}";
                </#list>
            </#if>
        template.helper('${uuid}', function (value) {
            for (var key in obj${uuid}) {
                if (key == value) {
                    return obj${uuid}[key]<#if show_code> + "[" + key + "]"</#if>;
                }
            }
            return value;
        });
        $("#${uuid}").remove();
    </script>
    {{value | ${uuid}}}
    <#elseif type=="dict">
        <#local uuid=func('uuid', 'dict')/>
    <script id="${uuid}">
        var arr${uuid} = [];
            <#local list=dict('list', dict_type)/>
            <#if list?? && list?size gt 0>
                <#list list as dict>
                arr${uuid}.push({"dictCode": "${dict.dictCode}", "value": "${dict.value}"});
                </#list>
            </#if>
        template.helper('${uuid}', function (dictCode) {
            for (var i in arr${uuid}) {
                if (arr${uuid}[i].dictCode == dictCode) {
                    return arr${uuid}[i].value<#if show_code> + "[" + dictCode + "]"</#if>;
                }
            }
            return dictCode;
        });
        $("#${uuid}").remove();
    </script>
    {{value | ${uuid}}}
    <#elseif type=="category">
        <#local uuid=func('uuid', 'category')/>
    <script id="${uuid}">
        var arr${uuid} = [];
            <#local list=category('list', category_type)/>
            <#if list?? && list?size gt 0>
                <#list list as ctg>
                arr${uuid}.push({"categoryCode": "${ctg.categoryCode}", "categoryName": "${ctg.categoryName}"});
                </#list>
            </#if>
        template.helper('${uuid}', function (categoryCode) {
            for (var i in arr${uuid}) {
                if (arr${uuid}[i].categoryCode == categoryCode) {
                    return arr${uuid}[i].categoryName<#if show_code> + "[" + categoryCode + "]"</#if>;
                }
            }
            return categoryCode;
        });
        $("#${uuid}").remove();
    </script>
    {{value | ${uuid}}}
    <#elseif type=="datetime">
    {{value | datetimeFormat}}
    <#elseif type=="date">
    {{value | dateFormat}}
    <#elseif type=="time">
    {{value | timeFormat}}
    <#elseif type=="yesNo">
    {{if value==1}}
    是
    {{else}}
    否
    {{/if}}
    <#else>
    {{value}}
    </#if>
</#macro>
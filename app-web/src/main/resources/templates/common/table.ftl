<#--表格-->
<#macro table url id="" pagination=true undefined_text="" form_id="" radio=false checkbox=false>
<div class="clearfix"></div>
<div class="space-10"></div>
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<table id="${id}" data-toggle="table" data-url="${url}" data-pagination="${pagination?string}"
       data-side-pagination="server" data-undefined-text="${undefined_text}" data-striped="true"
       <#if form_id!=''>data-form-id="${form_id}"</#if> data-click-to-select="true">
    <thead>
    <tr>
        <#if radio>
            <th data-radio="true"></th>
        </#if>
        <#if checkbox>
            <th data-checkbox="true"></th>
        </#if>
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
<#macro th title="" field="" sortable=true render=false type="" auto_hide=false enum_key="" show_code=false>
<th data-field="${field}"
    <#if field!='' && sortable>
    data-sortable="${sortable?c}"
    </#if>
    <#if render>
        <#local uuid=func('uuid')/>
        <#local formatter=uuid + "Format"/>
    data-formatter="${formatter}"
    </#if>
    class="<#if auto_hide>hidden-sm hidden-xs</#if>"
>
${title}
    <#if render>
        <div id="${uuid}" class="hidden">
            <#nested/>
            <#if type=='yesNo'>
                {{value | yesNoFormat}}
            <#elseif type="date">
                {{value | dateFormat}}
            <#elseif type="time">
                {{value | timeFormat}}
            <#elseif type="datetime">
                {{value | datetimeFormat}}
            </#if>
        </div>
        <script id="${uuid}-script">
            <#if type=='enum'>
                var obj${uuid} = {};
                <#local map=enum('map', enum_key)/>
                <#if map?? && map?size gt 0>
                    <#list map?keys as key>
                    obj${uuid}["${key}"] = "${map[key]}";
                    </#list>
                </#if>
            </#if>
            function ${formatter}(value, row, index) {
                <#if type=='enum'>
                    for (var key in obj${uuid}) {
                        if (key === value) {
                            return obj${uuid}[key]<#if show_code> + "[" + key + "]"</#if>;
                        }
                    }
                    return value;
                <#else>
                    var data = {"value": value, "row": row, "index": index};
                    return template('${uuid}', data);
                </#if>
            }
            $("#${uuid}-script").remove();
        </script>
    </#if>
</th>
</#macro>

<#--yesNo格式化-->
<#macro thYesNo title="" field="" sortable=true auto_hide=false>
    <@th title=title field=field render=true type="yesNo" auto_hide=auto_hide/>
</#macro>

<#--date格式化-->
<#macro thDate title="" field="" sortable=true auto_hide=false>
    <@th title=title field=field render=true type="date" auto_hide=auto_hide/>
</#macro>

<#--time格式化-->
<#macro thTime title="" field="" sortable=true auto_hide=false>
    <@th title=title field=field render=true type="time" auto_hide=auto_hide/>
</#macro>

<#--datetime格式化-->
<#macro thDatetime title="" field="" sortable=true auto_hide=false>
    <@th title=title field=field render=true type="datetime" auto_hide=auto_hide/>
</#macro>

<#--enum格式化-->
<#macro thEnum enum_key title="" field="" sortable=true auto_hide=false show_code=false>
    <@th title=title field=field render=true type="enum" enum_key=enum_key auto_hide=auto_hide show_code=show_code/>
</#macro>

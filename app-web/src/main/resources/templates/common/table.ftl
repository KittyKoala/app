<#--表格-->
<#macro table url id="" pagination=true undefined_text="" form_id="">
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
<#macro th title="" field="" sortable=true render=false checkbox=false type="">
<th data-field="${field}"
    <#if field!='' && sortable>
    data-sortable="${sortable?c}"
    </#if>
    <#if checkbox>
    data-checkbox="true"
    </#if>
    <#if render>
        <#local uuid=func('uuid')/>
        <#local formatter=uuid + "Format"/>
    data-formatter="${formatter}"
    </#if>
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
        <script>
            function ${formatter}(value, row, index) {
                var data = {"value": value, "row": row, "index": index};
                return template('${uuid}', data);
            }
        </script>
    </#if>
</th>
</#macro>

<#--yesNo格式化-->
<#macro thYesNo title="" field="" sortable=true>
    <@th title=title field=field render=true type="yesNo"/>
</#macro>

<#--date格式化-->
<#macro thDate title="" field="" sortable=true>
    <@th title=title field=field render=true type="date"/>
</#macro>

<#--time格式化-->
<#macro thTime title="" field="" sortable=true>
    <@th title=title field=field render=true type="time"/>
</#macro>

<#--datetime格式化-->
<#macro thDatetime title="" field="" sortable=true>
    <@th title=title field=field render=true type="datetime"/>
</#macro>

<#--操作组-->
<#macro thOperations name href icon="" render=true>
    <@th title="操作" render=true>
    <div class="btn-group">
        <a href="${href}" class="btn btn-xs btn-inverse">
            <#if icon!=''>
                <i class="fa ace-icon ${icon}"></i>
            </#if>
        ${name}
        </a>
        <#if render>
            <button data-toggle="dropdown" class="btn btn-xs btn-inverse dropdown-toggle" aria-haspopup="true"
                    aria-expanded="false">
                <span class="ace-icon fa fa-caret-down icon-only"></span>
            </button>

            <ul class="dropdown-menu dropdown-menu-right dropdown-inverse">
                <#nested/>
            </ul>
        </#if>
    </div>
    </@th>
</#macro>

<#--操作-->
<#macro operation name href>
    <li>
        <a href="${href}">
        ${name}
        </a>
    </li>
</#macro>
<#--搜索表单-->
<#macro search_form id="">
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<form class="form-horizontal col-xs-12 fa-border radius-base" id="${id}">
    <div class="space-10"></div>
    <#nested />
</form>
</#macro>

<#--输入框-->
<#macro input name label id="" value="" placeholder="">
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<div class="form-group col-lg-4 col-md-6 col-xs-12">
    <div class="app-label nowrap col-md-5 col-xs-12">
        <label>${label}</label>
    </div>
    <div class="col-md-7 controls col-xs-12">
        <input id="${id}" name="${name}" value="${value}" class="form-control"
               placeholder="${(placeholder=='')?string('请输入${label}', placeholder)}"/>
    </div>
    <div>
        <#nested />
    </div>
</div>
</#macro>

<#--日期选择框-->
<#macro date name label id="" value="" placeholder="" date_format="yyyy-mm-dd">
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<div class="form-group col-lg-4 col-md-6 col-xs-12">
    <div class="app-label nowrap col-md-5 col-xs-12">
        <label>${label}</label>
    </div>
    <div class="col-md-7 controls col-xs-12">
        <input id="${id}" name="${name}" value="${value}" class="form-control date-picker readonly" readonly
               placeholder="${(placeholder=='')?string('请选择${label}', placeholder)}"/>
    </div>
    <div>
        <#nested />
    </div>

    <script>
        $(function () {
            $('#${id}').datepicker({
                format: '${date_format}'
            });
        })
    </script>
</div>
</#macro>

<#--表单按钮组-->
<#macro form_actions>
    <div class="col-xs-12 align-center">
    <#nested />
        <div class="space-6"></div>
    </div>
</#macro>

<#--查询按钮-->
<#macro query table_id>
    <a href="javascript:" class="btn btn-sm btn-purple" data-table-id="${table_id}" data-type="submit">
        <i class="ace-icon fa fa-search"></i>
        查询
    </a>
</#macro>

<#--重置按钮-->
<#macro reset>
    <a href="javascript:" class="btn btn-sm btn-danger" data-type="reset">
        <i class="ace-icon fa fa-undo"></i>
        清除
    </a>
</#macro>
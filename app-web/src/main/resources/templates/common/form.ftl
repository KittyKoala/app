<#--搜索表单-->
<#macro search_form id="">
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
    <#assign _isSearchForm=true/>
<form class="form-horizontal col-xs-12 fa-border radius-base" id="${id}">
    <div class="space-10"></div>
    <#nested />
</form>
</#macro>

<#--普通表单-->
<#macro form  action method="post">
<form class="form-horizontal" method="${method}" action="${action}">
    <#nested />
</form>
</#macro>

<#--输入框-->
<#macro input label name="" id="" value="" placeholder="" readonly=false required=false>
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<div class="form-group <#if _isSearchForm??>col-lg-4 col-md-6 col-xs-12</#if>">
    <div class="app-label nowrap <#if _isSearchForm??>col-md-5 col-xs-12<#else>col-md-3</#if>">
        <label class="<#if required>required</#if>">${label}</label>
    </div>
    <div class="col-md-7 controls <#if _isSearchForm??>col-xs-12</#if>">
        <input id="${id}" <#if name!=''>name="${name}"</#if> value="${value}" class="form-control"
               <#if readonly>readonly</#if>
               placeholder="${(placeholder=='')?string('请输入${label}', placeholder)}" <#if required>required</#if>/>
    </div>
    <div>
        <#nested />
    </div>
</div>
</#macro>

<#--日期选择框-->
<#macro date label name="" id="" value="" placeholder="" date_format="yyyy-mm-dd">
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<div class="form-group <#if _isSearchForm??>col-lg-4 col-md-6 col-xs-12</#if>">
    <div class="app-label nowrap <#if _isSearchForm??>col-md-5 col-xs-12<#else>col-md-3</#if>">
        <label>${label}</label>
    </div>
    <div class="col-md-7 controls <#if _isSearchForm??>col-xs-12</#if>">
        <input id="${id}" <#if name!=''>name="${name}"</#if> value="${value}" class="form-control date-picker readonly"
               readonly
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

<#--编辑按钮-->
<#macro edit table_id url>
<a href="javascript:" class="btn btn-sm btn-info" data-table-id="${table_id}" data-url="${url}" data-type="edit">
    <i class="ace-icon fa fa-pencil-square-o"></i>
    编辑
</a>
</#macro>

<#--取消按钮-->
<#macro cancel>
<button class="btn btn-warning" data-dismiss="modal">
    <i class="ace-icon fa fa-times"></i>
    取消
</button>
</#macro>

<#--提交按钮-->
<#macro submit>
<button class="btn btn-success" data-type="submit" data-loading-text="正在提交...">
    <i class="ace-icon fa fa-check"></i>
    提交
</button>
</#macro>
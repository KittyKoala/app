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
<#macro form action id="" method="post" table_id="" token=false>
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<form id="${id}" class="form-horizontal" method="${method}" action="${action}">
    <#if token>
        <input type="hidden" name="_token" value="${_token!''}"/>
    </#if>
    <#nested />
</form>

<script>
    $(function () {
        var $modal = $('.modal');
        var $form = $modal.find("form");
        var $btn = $modal.find("button[data-type=submit]");

        $form.validate({
            submitHandler: function (form, event) {
                event.preventDefault();
                $btn.button('loading');
                formSubmit($(form), $btn, function () {
                    $modal.modal('hide');
                    <#if table_id!=''>
                        $('#${table_id}').bootstrapTable("refresh");
                    </#if>
                });
            }
        });
    })
</script>
</#macro>

<#--输入框-->
<#macro input label type="text" name="" id="" value="" placeholder="" readonly=false
required=false min_length=-1 max_length=-1 validator="" remote="" equal_to="">
    <#if id==''>
        <#local id=func('uuid')/>
    </#if>
<div class="form-group <#if _isSearchForm??>col-lg-4 col-md-6 col-xs-12</#if>">
    <div class="app-label nowrap <#if _isSearchForm??>col-md-5 col-xs-12<#else>col-md-3</#if>">
        <label class="<#if required>required</#if>">${label}</label>
    </div>
    <div class="col-md-7 controls <#if _isSearchForm??>col-xs-12</#if>">
    <input type="${type}" id="${id}" <#if name!=''>name="${name}"</#if> value="${value}" class="form-control"
           <#if readonly>readonly</#if>
           placeholder="${(placeholder=='')?string('请输入${label}', placeholder)}" <#if required>required</#if>
        <#if min_length!=-1>
           minlength="${min_length}"
        </#if>
        <#if max_length!=-1>
           maxlength="${max_length}"
        </#if>
        <#if validator!=''>
        ${validator}="true"
        </#if>
        <#if remote!=''>
            remote="${remote}"
        </#if>
        <#if equal_to!=''>
            equalTo="${equal_to}"
        </#if>
        />
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
<div class="col-xs-12 align-center app-form-actions">
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
    <@reset/>
</#macro>

<#--重置按钮-->
<#macro reset>
<a href="javascript:" class="btn btn-sm btn-warning" data-type="reset">
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

<#--模态框按钮-->
<#macro modal name table_id url icon="">
<a href="javascript:" class="btn btn-sm btn-skin" data-table-id="${table_id}" data-url="${url}" data-type="edit">
    <#if icon!=''>
        <i class="ace-icon fa ${icon}"></i>
    </#if>
${name}
</a>
</#macro>

<#--新增按钮-->
<#macro create url>
<a href="javascript:" class="btn btn-sm btn-skin" data-url="${url}" data-type="create">
    <i class="ace-icon fa fa-pencil-square-o"></i>
    新增
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

<#--删除按钮-->
<#macro delete table_id url>
<a href="javascript:" class="btn btn-sm btn-danger" data-table-id="${table_id}" data-url="${url}" data-type="delete">
    <i class="ace-icon fa fa-trash"></i>
    删除
</a>
</#macro>

<#--恢复按钮-->
<#macro restore table_id url>
<a href="javascript:" class="btn btn-sm btn-info2" data-table-id="${table_id}" data-url="${url}" data-type="delete">
    <i class="ace-icon fa fa-recycle"></i>
    恢复
</a>
</#macro>
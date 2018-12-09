<#assign ctx="${(rca.contextPath)!''}">
<#include "../common/all.ftl"/>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>${title!'未知'} · ${appName}</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctx}/ace/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/libs/bootstrap-table/dist/bootstrap-table.min.css">
    <link rel="stylesheet" href="${ctx}/ace/dist/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctx}/ace/dist/css/jquery.gritter.min.css"/>
    <link rel="stylesheet" href="${ctx}/ace/dist/css/datepicker.min.css"/>
    <link rel="stylesheet" href="${ctx}/ace/dist/css/chosen.min.css"/>
    <link rel="stylesheet" href="${ctx}/ace/dist/css/jquery-ui.min.css"/>

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${ctx}/ace/dist/css/ace-fonts.min.css"/>

    <!--skin-->
    <link rel="stylesheet" href="${ctx}/ace/dist/css/ace-skins.min.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctx}/ace/dist/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/ace/dist/css/ace-part2.min.css" class="ace-main-stylesheet"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/ace/dist/css/ace-ie.min.css"/>
    <![endif]-->

    <link rel="stylesheet" href="${ctx}/app/css/dashboard.css"/>

    <script src="${ctx}/libs/jquery/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/libs/artTemplate/template.js"></script>
    <script src="${ctx}/ace/dist/js/date-time/datepicker-zh.js"></script>

    <!--[if lte IE 8]>
    <script src="${ctx}/ace/dist/js/html5shiv.min.js"></script>
    <script src="${ctx}/ace/dist/js/respond.min.js"></script>
    <![endif]-->
<@block name="style"/>
</head>
<body class="<@app.user property="skin" default="no-skin"/>">

<#include "navbar.ftl"/>

<div class="main-container" id="main-container">

<#include "sidebar.ftl"/>

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="menu-icon fa fa-dashboard"></i>
                        <a href="${ctx}/dashboard">工作台</a>
                    </li>
                <#--<#if subtitle??>-->
                    <#--<li>-->
                        <#--<a href="javascript:">${subtitle}</a>-->
                    <#--</li>-->
                <#--</#if>-->
                <#--<@block name="breadcrumbs"/>-->
                    <#--<li class="active">${title}</li>-->
                </ul>
            </div>

            <div class="page-content">
            <@block name="main"/>
            </div>
        </div>
    </div>

<#include "footer.ftl"/>
<#include "modal.ftl"/>
</div>

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${ctx}/ace/dist/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='/static/ace/dist/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/ace/dist/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>

<!--[if lte IE 8]>
<script src="${ctx}/ace/dist/js/excanvas.min.js"></script>
<![endif]-->

<script>var ctx = '${ctx}';</script>
<script src="${ctx}/ace/dist/js/jquery-ui.min.js"></script>
<script src="${ctx}/ace/dist/js/bootstrap.min.js"></script>
<script src="${ctx}/libs/jquery/jquery.bootstrap.min.js"></script>
<script src="${ctx}/libs/bootstrap-table/dist/bootstrap-table.min.js"></script>
<script src="${ctx}/libs/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx}/ace/dist/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/ace/dist/js/jquery.gritter.min.js"></script>
<script src="${ctx}/ace/dist/js/chosen.jquery.min.js"></script>
<script src="${ctx}/ace/dist/js/ace-extra.min.js"></script>
<script src="${ctx}/ace/dist/js/ace-elements.min.js"></script>
<script src="${ctx}/ace/dist/js/ace.min.js"></script>
<script src="${ctx}/ace/dist/js/prettify.min.js"></script>
<script src="${ctx}/libs/jquery/jquery.form.min.js"></script>
<script src="${ctx}/libs/jquery/jquery.validate.min.js"></script>
<script src="${ctx}/libs/jquery/jquery.validate.extends.js"></script>
<script src="${ctx}/app/js/dashboard.js"></script>
<@block name="script"/>
</body>
</html>

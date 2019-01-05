<#assign title="重复提交"/>

<@override name="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name=title/>
        </@breadcrumbs>
<h1 style="text-align: center;margin-top: 100px;">token失效或重复提交，请刷新界面重试</h1>

        <#include "back-tools.ftl"/>
    </@panel>
</@override>

<@extends name="../web/layout.ftl"/>
<#assign title="权限不足（403）"/>

<@override name="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name=title/>
        </@breadcrumbs>

<h1 style="text-align: center;margin-top: 100px;">权限不足（403）</h1>

        <#include "back-tools.ftl"/>
    </@panel>
</@override>

<@extends name="../web/layout.ftl"/>
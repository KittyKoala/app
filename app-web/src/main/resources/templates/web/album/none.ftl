<#assign title="不存在的相册"/>

<@override name="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name="相册" href="${ctx}/album"/>
            <@breadcrumb name=title/>
        </@breadcrumbs>

    <h1 style="text-align: center;margin-top: 100px;">${title}</h1>

        <#include "../../error/back-tools.ftl"/>
    </@panel>
</@override>

<@extends name="../layout.ftl"/>
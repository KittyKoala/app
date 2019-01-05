<#assign title="${section.title}"/>

<@override name="style">
<style>
    .title {
        text-align: center;
        font-size: 24px;
    }

    .toolbar {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .toolbar a {
        color: #333;
        display: inline-block;
        text-decoration: none;
        width: 334px;
    }

    .content {
        line-height: 1.7;
        font-size: 17px !important;
    }
</style>
</@override>

<@override name="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name="小说" href="${ctx}/novel"/>
            <@breadcrumb name="${novel.name}" href="${ctx}/novel/${novel.novelId}"/>
            <@breadcrumb name=title/>
        </@breadcrumbs>

        <div class="title">
            ${section.title}
        </div>

        <#include "toolbar.ftl"/>

        <div class="border content">
            ${section.content}
        </div>

        <#include "toolbar.ftl"/>
    </@panel>
</@override>

<@extends name="../layout.ftl"/>
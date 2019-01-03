<#assign title="错误界面"/>

<@override name="main">
<h1 style="text-align: center;margin-top: 100px;">您请求的页面不存在，或者发生未知错误。</h1>

    <#include "error/back-tools.ftl"/>
</@override>

<@extends name="web/layout.ftl"/>
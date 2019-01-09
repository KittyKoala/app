<#assign title="生成身份证"/>
<#include "../../common/web-form.ftl"/>

<@override name="style">
<style>
    .tool-form {
        width: 800px;
        margin: 0 auto;
    }

    .tool-form input {
        background: #fff;
        border: 1px solid #aaa;
        color: #333;
        display: inline;
    }

    .tool-form .btn {
        border: 1px solid #666;
        color: #333;
    }

    .tips {
        color: #999;
        font-size: 12px;
        margin-top: 5px;
        text-indent: 2em;
    }

    .result {
        width: 800px;
        margin: 0 auto;
        margin-top: 20px;
    }

    .result table {
        width: 100%;
        color: #333333;
        border-collapse: collapse;
        border: 1px solid #ccc;
    }

    .result table tr td {
        border: 1px solid #ccc;
        padding: 8px;
        font-size: 14px;
    }

    .result table tr td.first {
        background: #f2f2f2;
        text-align: center;
        width: 100px;
    }
</style>
</@override>

<@override name="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name="工具" href="${ctx}/tool"/>
            <@breadcrumb name=title/>
        </@breadcrumbs>

    <div class="border">
        <@form action="${ctx}/tool/idNoGen" class="tool-form" success="success">

        开发中
        <#--<button class="btn" data-type="submit" data-loading-text="正在生成...">-->
                <#--<i class="fa fa-coffee"></i>-->
                <#--生成-->
            <#--</button>-->
        </@form>

        <div class="result">
            <table>
                <tbody>
                </tbody>
            </table>
        </div>

        <div style="height: 20px;clear: both"></div>
    </div>
    </@panel>
</@override>

<@override name="script">
<script>
    /**
     * 查询成功的回调
     */
    function success(response) {

    }
</script>
</@override>

<@extends name="../layout.ftl"/>
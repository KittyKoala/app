<#assign title="生成身份证"/>
<#include "../../common/web-form.ftl"/>

<@override name="style">
<style>
    .tool-form {
        margin: 0 20px;
    }

    .tool-form input {
        background: #fff;
        border: 1px solid #aaa;
        color: #333;
    }

    .tool-form input[type=radio] {
        box-sizing: border-box;
        outline: none;
        font-size: 14px;
        margin-right: 5px;
    }

    .tool-form .radio-text {
        margin-right: 15px;
    }

    .form input[type=number] {
        width: 90px;
        height: 28px;
        line-height: 28px;
        box-sizing: border-box;
        outline: none;
        font-size: 14px;
        padding: 4px 10px;
        background: #fff;
        border: 1px solid #aaa;
        color: #333;
    }

    .tool-form select {
        display: block;
        width: 280px;
        height: 28px;
        line-height: 28px;
        box-sizing: border-box;
        outline: none;
        font-size: 14px;
        padding: 4px 10px;
        margin-right: 10px;
        background: #fff;
        border: 1px solid #aaa;
        color: #333;
    }

    .tool-form .btn {
        border: 1px solid #666;
        color: #333;
        margin-top: 15px;
    }

    .search {
        width: 100%;
        margin-top: 10px;
        color: #333333;
        border-collapse: collapse;
        border: 1px solid #ccc;
    }

    .search tr td {
        border: 1px solid #ccc;
        font-size: 14px;
        height: 36px;
        line-height: 36px;
        padding-left: 10px;
    }

    .search tr td.first {
        background: #f2f2f2;
        text-align: center;
        width: 100px;
        padding-left: 0;
    }

    label.error {
        margin-left: 3px;
    }

    .result {
        margin-left: 20px;
        margin-top: 20px;
    }

    .result ul {
        width: 100%;
        list-style: none;
        padding-left: 0;
        margin-top: 0;
    }

    .result ul li {
        float: left;
        width: 33%;
        color: #595959;
        font-size: 13px;
        line-height: 25px;
    }

    /*小屏*/
    @media (max-width: 768px) {
        .tool-form select {
            width: 180px;
        }

        .result ul li {
            width: 50%;
        }
    }
</style>
</@override>

<@override name="main">
<div class="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name=title/>
        </@breadcrumbs>

        <div class="border">
            <@form action="${ctx}/tool/idNoGen" beforeSubmit="beforeSubmit" class="tool-form" success="success">
                <table class="search">
                    <tbody>
                    <tr>
                        <td class="first">省　　份</td>
                        <td>
                            <select name="prov">
                                <option value="">随机</option>
                                <#list cities?keys as key>
                                    <option value="${key}">${cities[key]}</option>
                                </#list>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="first">性　　别</td>
                        <td>
                            <input type="radio" checked name="sex" value=""/><span class="radio-text">随机</span>
                            <input type="radio" name="sex" value="0"/><span class="radio-text">男</span>
                            <input type="radio" name="sex" value="1"/><span class="radio-text">女</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="first">年　　龄</td>
                        <td>
                            <input type="number" name="startAge" value="18" placeholder="最小年龄" min="1" max="99"/>
                            ~
                            <input type="number" value="60" name="endAge" placeholder="最大年龄" min="1" max="99"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="first">证件类型</td>
                        <td>
                            <input type="radio" checked name="len" value="18"/><span class="radio-text">18位</span>
                            <input type="radio" name="len" value="15"/><span class="radio-text">15位</span>
                            <input type="radio" name="len" value="-1"/><span class="radio-text">15+18位</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="first">生成数量</td>
                        <td>
                            <input type="number" name="size" value="30" placeholder="生成数量，默认30个" min="1" max="99"/>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <button class="btn" data-type="submit" data-loading-text="正在生成...">
                    <i class="fa fa-coffee"></i>
                    开始生成
                </button>
            </@form>

            <div class="result">
                <ul>

                </ul>
            </div>

            <div style="height: 20px;clear: both"></div>
        </div>
    </@panel>
</div>
</@override>

<@override name="script">
<script>
    /**
     * 请求之前
     */
    function beforeSubmit() {
        $(".result ul").empty();
    }

    /**
     * 成功的回调
     */
    function success(response) {
        for (var i = 0; i < response.idNos.length; i++) {
            $(".result ul").append("<li>" + response.idNos[i] + "</li>");
        }
    }
</script>
</@override>

<@extends name="../layout.ftl"/>
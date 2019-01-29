<#assign title="电子印章"/>
<#include "../../common/web-form.ftl"/>

<@override name="style">
<style>

    .tool-form {
        margin: 10px 20px;
    }

    .tool-form input[type=text] {
        display: inline-block;
        background: #fff;
        border: 1px solid #aaa;
        color: #333;
        width: 300px;
        height: 34px;
        line-height: 34px;
        box-sizing: border-box;
        outline: none;
        font-size: 14px;
        padding: 4px 10px;
    }

    .tool-form .btn {
        border: 1px solid #666;
        color: #333;
    }

    #name-error {
        color: red;
        font-size: 12px;
        margin-left: 3px;
    }

    .result dl {
        float: left;
        width: 106px;
        text-align: center;
        padding: 0;
        margin: 20px 30px 0 30px;
    }

    .result dl dt, .result dl dd {
        padding: 0;
        margin: 0;
        font-size: 13px;
        color: #777;
        text-align: center;
    }

    .result img {
        width: 100px;
        height: 100px;
        margin: 3px;
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
            <@form action="${ctx}/tool/seal" beforeSubmit="beforeSubmit" rules="rules" class="tool-form" success="success">
                姓名（2~4个汉字）
                <input type="text" name="name" placeholder="请输入您的姓名"/>
                <button class="btn" data-type='submit' style="margin-top: 10px;" data-loading-text="正在生成...">
                    生成印章
                </button>
            </@form>

            <div class="result">
            </div>

            <div class="clear"></div>
        </div>
    </@panel>
</div>
</@override>

<@override name="script">
<script>
    /**
     * 自定义校验规则
     *
     * @returns 返回jquery validate规则对象
     */
    function rules() {
        return {
            name: {
                required: true,
                isName: true
            }
        }
    }

    /**
     * 查询请求之前
     */
    function beforeSubmit() {
        $(".result").empty();
    }

    /**
     * 查询成功的回调
     */
    function success(response) {
        for (var i = 0; i < response.result.length; i++) {
            $(".result").append("<dl><dd><img src='${ctx}/" + response.result[i][0] + "'/></dd><dt>" + response.result[i][1] + "</dt></dl>");
        }
    }
</script>
</@override>

<@extends name="../layout.ftl"/>
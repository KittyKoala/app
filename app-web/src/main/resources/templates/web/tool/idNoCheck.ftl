<#assign title="身份证校验"/>

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
            <@breadcrumb name=title/>
        </@breadcrumbs>

    <div class="border">
        <@form action="${ctx}/tool/idNoCheck" beforeSubmit="beforeSubmit" class="tool-form" success="success">
            身份证号码:
            <input id="data" name="idNo" placeholder="请输入要查询的身份证号码" required/>
            <button class="btn" data-type="submit" data-loading-text="正在查询...">
                <i class="fa fa-search"></i>
                查询
            </button>
            <div class="tips">支持15位或18位身份证号码查询。</div>
        </@form>

        <div class="result">
            <table>
                <tbody>
                <tr>
                    <td class="first">查询号码</td>
                    <td id="idNo"></td>
                </tr>
                <tr>
                    <td class="first">查询结果</td>
                    <td id="isIdNo"></td>
                </tr>
                <tr>
                    <td class="first">原户籍地</td>
                    <td id="address"></td>
                </tr>
                <tr>
                    <td class="first">出生年月</td>
                    <td id="birthday"></td>
                </tr>
                <tr>
                    <td class="first">性　　别</td>
                    <td id="gender"></td>
                </tr>
                <tr>
                    <td class="first">生　　肖</td>
                    <td id="shengXiao"></td>
                </tr>
                <tr>
                    <td class="first">星　　座</td>
                    <td id="xingZuo"></td>
                </tr>
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
     * 查询请求之前
     */
    function beforeSubmit() {
        $("#idNo").html($("#data").val());
        $("#address").html("");
        $("#birthday").html("");
        $("#gender").html("");
        $("#shengXiao").html("");
        $("#xingZuo").html("");
    }
    /**
     * 查询成功的回调
     */
    function success(response) {
        $("#idNo").html(response.idNo);

        var isIdNo = response.isIdNo;
        $("#isIdNo").html(isIdNo ? "合法" : "不合法");

        if (isIdNo) {
            $("#address").html(response.address);
            $("#birthday").html(response.birthday);
            $("#gender").html(response.gender === 1 ? "女" : "男");
            $("#shengXiao").html(response.shengXiao);
            $("#xingZuo").html(response.xingZuo);
        }
    }
</script>
</@override>

<@extends name="../layout.ftl"/>
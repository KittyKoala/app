<#assign title="留言"/>
<#include "../../common/auth-form.ftl"/>

<@override name="style">
<style>
    .note-form {
        width: 600px;
        margin: 0 auto;
    }

    .note-form input[type=email], .note-form input[type=text] {
        width: 340px;
        background: #fff;
        border: 1px solid #aaa;
        color: #333;
        display: inline;
    }

    .note-form .form-actions {
        margin-left: 0;
    }

    .note-form .btn {
        border: 1px solid #666;
        color: #333;
    }

    .note-form .captcha {
        right: 150px;
    }

    .note-form .error {
        display: block;
    }

    .note-form textarea {
        width: 320px;
        height: 100px;
        padding: 5px 10px;
        font-size: 14px;
        line-height: 1.42857143;
        box-shadow: none !important;
        color: #858585;
        outline: none;
        background-color: #ffffff;
        border: 1px solid #d5d5d5;
        border-radius: 0 !important;
    }

    /*小屏*/
    @media (max-width: 768px) {
        .note-form {
            width: 100%;
            padding: 10px;
        }

        .note-form input[type=text] {
            width: 220px !important;
        }


        .note-form .captcha {
            left: 240px;
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
            <@form action="${ctx}/note" class="note-form" success="success">
                <@input type="email" label="电子邮箱" name="email" required=true range_length=[1, 64]/>

                <@captcha label="验证码" id="captcha" name="captcha"/>

                <@textarea label="留言内容" name="content" required=true range_length=[1, 512]/>

                <@actions>
                    <@button name="提交" type="submit" icon="fa-check"/>
                    <@button name="重置" type="reset" icon="fa-undo"/>
                </@actions>
            </@form>
            <div style="height: 20px;clear: both"></div>
        </div>
    </@panel>
</div>
</@override>

<@override name="script">
<script>
    /**
     * 查询成功的回调
     */
    function success(response) {
        $(".note-form").validate().resetForm();
        Message.success("留言提交成功，请耐心等待站长邮件反馈");
    }
</script>
</@override>

<@extends name="../layout.ftl"/>
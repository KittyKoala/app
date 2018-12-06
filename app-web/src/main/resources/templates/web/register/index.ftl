<#assign title="注册"/>

<@override name="style">
<style>
    .container {
        background: #060606;
    }

    .register-panel {
        background: url('/app/images/bg.jpg') no-repeat;
        min-height: 640px;
    }

    #register-form {
        clear: both;
        margin-top: 110px;
        float: right;
        color: #ccc;
    }

    #sendBtn {
        position: absolute;
        right: -55px;
        top: 4px;
    }

    .btn {
        height: 28px;
        line-height: 24px;
        padding: 3px 12px;
        background: none;
        border-radius: 13px;
        border: 1px solid #ddd;
        color: #ccc;
        font-size: 13px;
        cursor: pointer;
        box-shadow: none !important;
        outline: none;
        letter-spacing: 2px;
    }

    .form .form-group {
        position: relative;
        margin-top: 38px;
    }

    .form .app-label {
        display: inline-block;
        _zoom: 1;
        *display: inline;
    }

    .form label {
        display: inline-block;
        width: 180px;
        text-align: right;
    }

    .form .app-label:after {
        content: '：';
    }

    .form .required:after {
        content: '*';
        color: red;
    }

    .form .input {
        display: inline-block;
        width: 400px;
        height: 34px;
        line-height: 34px;
        box-sizing: border-box;
        outline: none;
        font-size: 14px;
        padding: 4px 10px;
        margin-right: 10px;
        background: rgba(0, 0, 0, 0.2);
        border: 1px solid #aaa;
        color: #fff;
    }

    .form .form-actions {
        margin-top: 65px;
        margin-left: 206px;
    }

    .form .form-actions button {
        margin-right: 15px;
    }
</style>
</@override>

<@override name="main">
<div class="register-panel">
    <div class="inner">
        <form action="${ctx}/register" id="register-form" method="post" class="form">
            <div class="form-group">
                <div class="app-label">
                    <label class="required">电子邮箱</label>
                </div>
                <input class="input" id="email" name="email" placeholder="可用于找回密码和接收通知">
            </div>
            <div class="form-group">
                <div class="app-label">
                    <label class="required">短信验证码</label>
                </div>
                <input class="input" id="code" name="code" placeholder="请输入邮箱验证码">
                <button id="sendBtn" class="btn" data-loading-text="正在发送...">
                    获取
                </button>
            </div>
            <div class="form-group">
                <div class="app-label">
                    <label class="required">密码</label>
                </div>
                <input class="input" id="password" type="password" name="password" placeholder="请输入登录密码">
            </div>
            <div class="form-group">
                <div class="app-label">
                    <label class="required">确认密码</label>
                </div>
                <input class="input" type="password" name="rePassword" placeholder="请再次输入登录密码">
            </div>
            <div class="form-actions">
                <button id="submitBtn" class="btn" type="submit" data-loading-text="正在注册...">
                    <i class="fa fa-users"></i>
                    注册
                </button>
                <button id="reset" class="btn" type="reset">
                    <i class="fa fa-undo"></i>
                    重置
                </button>
            </div>
        </form>
    </div>

    <div class="clear"></div>
</div>
</@override>

<@extends name="../layout.ftl"/>
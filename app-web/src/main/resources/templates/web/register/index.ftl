<#assign title="注册"/>

<@override name="style">
<style>
    .container {
        background: #060606;
    }

    .register-panel {
        background: url('/app/images/bg.jpg') no-repeat;
    }

    #register-form {
        clear: both;
        float: right;
        color: #d5d5d5;
        margin-top: 30px;
    }

    #sendBtn {
        position: absolute;
        left: 623px;
        top: 2px;
        height: 30px;
        line-height: 20px;
        width: 60px;
        padding: 0;
    }

    .form .sending {
        width: 100px !important;
    }

    .btn {
        height: 34px;
        line-height: 22px;
        padding: 5px 15px;
        background: none;
        border-radius: 17px;
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
        margin-top: 30px;
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
        margin-top: 50px;
        margin-left: 206px;
    }

    .form .form-actions button {
        margin-right: 15px;
    }

    .form div.error {
        text-indent: 205px;
        color: #ff5151;
        font-size: 13px;
        margin-top: 3px;
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
                <input class="input" id="email" name="email" placeholder="可用于登录找回密码和接收通知">
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

    <div class="space-20"></div>

    <div class="clear"></div>
</div>
</@override>

<@override name="script">
<script>
    $(function () {
        // 动态高度设定
        $(".register-panel").css({"minHeight": $(window).height() - 330});

        // 表单校验
        var $form = $('#register-form');
        var $btn = $("#submitBtn");

        var $validate = $form.validate({
            rules: {
                email: {
                    required: true,
                    isEmail: true,
                    remote: {
                        url: "${ctx}/api/validate/email",
                        type: 'post',
                        data: {
                            'email': function () {
                                return $('#email').val()
                            }
                        }
                    }
                },
                password: {
                    required: true,
                    isPassword: true
                },
                rePassword: {
                    required: true,
                    equalTo: "#password"
                },
                code: {
                    required: true,
                    isCode: true
                }
            },
            submitHandler: function (form, event) {
                event.preventDefault();
                $btn.button('loading');
                $(form).ajaxSubmit({
                    dataType: 'json',
                    success: function (response) {
                        if (response.respCo === '0000') {
                            window.location.href = ctx + "/register/success";
                        } else {
                            $.growl.error({
                                title: '错误',
                                message: response.respMsg
                            });
                        }
                        $btn.button('reset');
                    },
                    error: function () {
                        $btn.button('reset');
                        $.growl.error({
                            title: '错误',
                            message: '网络错误，请稍后再试'
                        });
                    }
                });
            },
            errorPlacement: function (error, element) {
                error.appendTo(element.parent());
            },
            errorElement: "div",
            errorClass: "error"
        });


        /**
         * 重置
         */
        $("#reset").click(function () {
            $validate.resetForm();
        });

        var time = 60;

        // 发送验证码
        var $sendSmsBtn = $("#sendBtn");
        $sendSmsBtn.click(function () {
            $form.validate().element($("#email"));

            var isValid = $form.validate().valid();
            if (!isValid) {
                return false;
            }

            var email = $('#email').val();
            $sendSmsBtn.button('loading');
            $sendSmsBtn.addClass('sending');
            $.post("${ctx}/api/email/register", {email: email}, function (response) {
                if ("0000" === response.respCo) {
                    $.growl.notice({
                        title: '消息',
                        message: '获取成功'
                    });
                    var t = setInterval(function () {
                        time--;
                        $sendSmsBtn.html(time + "秒");
                        $sendSmsBtn.removeClass('sending');
                        if (time === 0) {
                            clearInterval(t);
                            $sendSmsBtn.html("获取");
                            $sendSmsBtn.button('reset');
                            time = 60;
                        }
                    }, 1000)
                } else {
                    $sendSmsBtn.button('reset');
                    $sendSmsBtn.removeClass('sending');
                    $.growl.error({
                        title: '错误',
                        message: response.respMsg
                    });
                }
            });

            return false;
        });
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
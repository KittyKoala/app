$(function () {
    /**
     * 提交
     */
    $(document).on("click", "[data-type='submit']", function (e) {
        e.preventDefault();
        $(this).parents("form").submit();
        return false;
    });

    /**
     * 重置
     */
    $(document).on("click", "[data-type='reset']", function (e) {
        $(this).parents("form").validate().resetForm();
    });

    // 显示/隐藏回调顶部按钮
    window.onscroll = function () {
        if (document.documentElement.scrollTop + document.body.scrollTop > 600) {
            document.getElementById("back-top").style.display = "block";
        } else {
            document.getElementById("back-top").style.display = "none";
        }
    };

    // 回到顶部
    $("#back-top").click(function () {
        scrollTo(0, 0);
    })
});

/**
 * 按钮加载中/重置
 *
 * @param type
 */
$.fn.button = function loading(type) {
    var $btn = $(this);
    if (type === 'loading') {
        $btn.data('temp', $btn.html());
        $btn.html($btn.data('loading-text'));
        $btn.attr('disabled', 'disabled');
    } else if (type === 'reset') {
        $btn.html($btn.data('temp'));
        $btn.removeAttr('disabled');

    }
}

// 提示信息
var Message = {
    success: function (message) {
        alert(message);
    },

    warning: function (message) {
        alert(message);
    },

    error: function (message) {
        alert(message);
    },

    info: function (message) {
        alert(message);
    }
};

/**
 * 提交表单
 *
 * @param $form 表单
 * @param $btn 提交按钮
 * @param success 成功回调
 * @param failure 失败回调
 */
function formSubmit($form, $btn, success, failure) {
    $form.ajaxSubmit({
        dataType: 'json',
        success: function (response) {
            if (response.respCo === '0000') {
                if (success) {
                    success(response);
                }
            } else {
                Message.error(response.respMsg);
                if (failure) {
                    failure(response);
                }
            }
            if ($btn) {
                $btn.button('reset');
            }
        },
        error: function () {
            Message.error("服务器内部错误，请稍后再试。");
            if ($btn) {
                $btn.button('reset');
            }
            if (failure) {
                failure();
            }
        }
    });
}

/**
 * 返回
 */
function goBack(url){
    if (url) {
        window.location.href = url;
    }
    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){ // IE
        if(history.length > 0){
            window.history.go( -1 );
        }else{
            window.location.href = ctx + "/";
        }
    }else{ //非IE浏览器
        if (navigator.userAgent.indexOf('Firefox') >= 0 ||
            navigator.userAgent.indexOf('Opera') >= 0 ||
            navigator.userAgent.indexOf('Safari') >= 0 ||
            navigator.userAgent.indexOf('Chrome') >= 0 ||
            navigator.userAgent.indexOf('WebKit') >= 0){

            if(window.history.length > 1){
                window.history.go( -1 );
            }else{
                window.location.href = ctx + "/";
            }
        }else{ //未知的浏览器
            window.history.go( -1 );
        }
    }
}

$(function () {

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
        $.growl.notice({
            title: '消息',
            message: message
        })
    },

    warning: function (message) {
        $.growl.warning({
            title: '警告',
            message: message
        })
    },

    error: function (message) {
        $.growl.error({
            title: '错误',
            message: message
        })
    },

    info: function (message) {
        $.growl({
            title: '消息',
            message: message
        })
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
            if (response.respCo == '0000') {
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
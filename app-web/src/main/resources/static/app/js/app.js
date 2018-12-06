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
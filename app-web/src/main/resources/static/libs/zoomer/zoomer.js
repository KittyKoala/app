/**
 * 图片缩放
 *
 * @author kangyonggan
 * @since 2017/3/21
 */
(function ($) {
    /**
     * 总入口
     *
     * @param $target
     * @param settings
     */
    function Zoomer($target, settings) {
        // 把settings的属性合并到defaults，并且不改变defaults
        settings = $.extend(true, $.fn.zoomer.defaults, settings);

        // 模态框图层
        var $overlay = $();
        // 全部图片
        var $allImgs = $target.find("img");

        // 修改光标
        $allImgs.css("cursor", "zoom-in");

        // 绑定点击事件
        $allImgs.click(function () {
            showModal($(this));
        });

        /**
         * 显示模态框
         *
         * @param $img
         */
        function showModal($img) {
            $overlay.remove();
            var src = $img.data('origin');
            if (!src) {
                src = $img.attr('src');
            }

            $overlay = $('<div>' +
                '<div style="text-align: center;color: #ddd;position: absolute;left: 0;right: 0;top: 40%;">加载中...</div>' +
                '<img src="' + src + '" style="max-width: 100%;max-height: 100%;display: none"/></div>').css({
                position: "fixed",
                left: 0,
                top: 0,
                bottom: 0,
                right: 0,
                color: '#fff',
                zIndex: 9999,
                cursor: 'zoom-out',
                background: 'rgba(0, 0, 0, 0.4)',
                textAlign: "center"
            });
            $("body").append($overlay);

            // 图片加载完成事件
            $overlay.find("img").load(function () {
                $(this).parents("div").find("div").css({"display": "none"});
                $(this).css({"display": "inline-block"});
            });

            // 图片加载失败事件
            $overlay.find("img").error(function () {
                if (settings.failure && typeof settings.failure === 'function') {
                    settings.failure($(this), $allImgs, $target, $overlay);
                }
                $(this).parents("div").find("div").html("加载失败！");
            });

            // 给模态框绑定点击事件
            $overlay.click(function () {
                $overlay.remove();
            });
        }
    }

    /**
     * 图片缩放
     *
     * @param option
     * @returns {*}
     */
    $.fn.zoomer = function (option) {
        return this.each(function () {
            $(this).data('zoomer', new Zoomer($(this), option));
        });
    };

    /**
     * 默认配置
     *
     * @type {{failure: null}}
     */
    $.fn.zoomer.defaults = {
        // 图片加载失败回调
        failure: null
    }
})(window.jQuery);
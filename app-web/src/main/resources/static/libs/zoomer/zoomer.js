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

        // 给每个img一个下标
        for (var i = 0; i < $allImgs.length; i++) {
            $($allImgs[i]).data("index", i);
        }

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
            var index = $img.data("index");

            $overlay = $('<div>' +
                '<a href="javascript:" class="zoomer-prev-img" data-index="' + index + '" style="display: none; position: absolute;z-index: 1;text-decoration: none">' +
                '<i class="fa fa-angle-left" style="color: #fff;display: none"></i></a>' +
                '<a href="javascript:" class="zoomer-next-img" data-index="' + index + '" style="display: none; position: absolute;z-index: 1;text-decoration: none">' +
                '<i class="fa fa-angle-right" style="color: #fff;display: none"></i></a>' +
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
                // 图片距离顶部的距离
                var top = ($(window).height() - $(this).height()) / 2;
                // 图片距离左边的距离
                var left = ($(window).width() - $(this).width()) / 2;

                // 上一张
                $(this).parents("div").find(".zoomer-prev-img").css({
                    "display": "inline-block",
                    "top": top + "px",
                    "left": left + "px",
                    "bottom": top + "px",
                    "width": $(this).width() / 3 + "px"
                }).hover(function () {
                    $(this).css({
                        background: "-webkit-linear-gradient(left, rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0))",
                        background: "-o-linear-gradient(right, rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0))",
                        background: "-moz-linear-gradient(right, rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0))",
                        background: "linear-gradient(to right, rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0))"
                    })
                    $(this).find("i").css({
                        "display": "block"
                    })
                }, function () {
                    $(this).css({
                        "background": "rgba(0, 0, 0, 0.0)"
                    })
                    $(this).find("i").css({
                        "display": "none"
                    })
                });
                var height = $(this).parents("div").find(".zoomer-prev-img").height();
                $(this).parents("div").find(".zoomer-prev-img i").css({
                    "marginTop": height / 2 - 20
                });

                // 下一张
                $(this).parents("div").find(".zoomer-next-img").css({
                    "display": "inline-block",
                    "top": top + "px",
                    "right": left + "px",
                    "bottom": top + "px",
                    "width": $(this).width() / 3 + "px"
                }).hover(function () {
                    $(this).css({
                        background: "-webkit-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.3))",
                        background: "-o-linear-gradient(right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.3))",
                        background: "-moz-linear-gradient(right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.3))",
                        background: "linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.3))"
                    })
                    $(this).find("i").css({
                        "display": "block"
                    })
                }, function () {
                    $(this).css({
                        "background": "rgba(0, 0, 0, 0.0)"
                    })
                    $(this).find("i").css({
                        "display": "none"
                    })
                });

                height = $(this).parents("div").find(".zoomer-next-img").height();
                $(this).parents("div").find(".zoomer-next-img i").css({
                    "marginTop": height / 2 - 20
                });

                $(this).parents("div").find("div").css({"display": "none"});
                $(this).css({"display": "inline-block", "marginTop": top + "px"});
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

            // 上一张事件
            $overlay.find(".zoomer-prev-img").click(function () {
                var index = $(this).data("index");
                if (index === 0) {
                    $overlay.remove();
                    return;
                }
                showModal($($allImgs[index - 1]));
                return false;
            });

            // 下一张事件
            $overlay.find(".zoomer-next-img").click(function () {
                var index = $(this).data("index");
                if (index === $allImgs.length - 1) {
                    $overlay.remove();
                    return;
                }
                showModal($($allImgs[index + 1]));
                return false;
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
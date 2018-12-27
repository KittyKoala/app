$(function () {
    /**
     * 日期时间格式化
     *
     * @param fmt
     * @returns {*}
     */
    Date.prototype.format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,                 //月份
            "d+": this.getDate(),                    //日
            "H+": this.getHours(),                   //小时
            "m+": this.getMinutes(),                 //分
            "s+": this.getSeconds(),                 //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    };

    /**
     * art日期时间格式化
     */
    template.helper('datetimeFormat', function (date) {
        var d = new Date();
        d.setTime(date);
        return d.format("yyyy-MM-dd HH:mm:ss");
    });

    /**
     * art布尔格式化
     */
    template.helper('yesNoFormat', function (val) {
        return val === 1 ? "是" : "否";
    });

    /**
     * art日期格式化
     */
    template.helper('dateFormat', function (date) {
        var d = new Date();
        d.setTime(date);
        return d.format("yyyy-MM-dd");
    });

    /**
     * art时间格式化
     */
    template.helper('timeFormat', function (date) {
        var d = new Date();
        d.setTime(date);
        return d.format("HH:mm:ss");
    });

    /**
     * art日期时间自定义格式化
     */
    template.helper('format', function (date, format) {
        var d = new Date();
        d.setTime(date);
        return d.format(format);
    });

    /**
     * art长度限定
     */
    template.helper('limit', function (str, len) {
        return str.substring(0, len);
    });

    /**
     * art数字转百分比
     */
    template.helper('percentage', function (num) {
        if (isNaN(num)) {
            num = 0;
        }
        return (Math.round(num * 10000) / 100).toFixed(2) + '%';
    });

    /**
     * art数字保留N位小数
     */
    template.helper('precision', function (num, n) {
        if (isNaN(num)) {
            num = 0;
        }
        return num.toFixed(n);
    });

    // 查询
    $(document).on("click", "[data-type='submit']", function (e) {
        e.preventDefault();
        var $this = $(this);
        var tableId = $this.data("table-id");
        var $form = $(this).parents("form");

        if (tableId) {
            var $table = $("#" + tableId);

            var options = $table.bootstrapTable('getOptions');
            if (!options.originUrl) {
                options.originUrl = options.url;
            }
            options.url = options.originUrl + "?" + $this.parents("form").serialize();
            options.pageNumber = 1;
            $table.bootstrapTable('refreshOptions', options);
            $table.bootstrapTable("refresh");
            return false;
        } else {
            $form.submit();
        }
    });

    // 清除
    $(document).on("click", "[data-type='reset']", function (e) {
        e.preventDefault();
        var $this = $(this);
        var $form = $this.parents("form");

        $form.find("input").val("");
        $form.find("select").val("");
        $form.find(".chosen-select").trigger("chosen:updated");
        return false;
    });

    // 提示框
    $.messager.model = {
        cancel: {text: "<i class='ace-icon fa fa-times'></i>取消", classed: "btn-default"},
        ok: {text: "<i class='ace-icon fa fa-check'></i>确定"}
    };

    // 弹确认框。<@c.link type="confirm" title="" .../>
    $(document).on("click", "[data-type='confirm']", function (e) {
        e.preventDefault();
        var $this = $(this);
        var $table = $(this).parents("table");

        $.messager.confirm("提示", "确定" + $this.attr("title") + "吗?", function () {
            $.get($this.attr('href')).success(function (res) {
                if (res.respCo === '0000') {
                    Message.success(res.respMsg);
                    $table.bootstrapTable("refresh");
                } else {
                    Message.error(res.respMsg);
                }
            }).error(function () {
                Message.error("网络错误，请稍后重试");
            })
        });
        return false;
    });
});

// 提示信息
var last_gritter;
var showMessage = function (type, message) {
    if (last_gritter) {
        $.gritter.remove(last_gritter);
    }
    last_gritter = $.gritter.add({
        title: '消息',
        text: message,
        time: 1500,
        class_name: type
    });
};

var Message = {
    success: function (message) {
        showMessage('gritter-success', message);
    },

    warning: function (message) {
        showMessage('gritter-warning', message);
    },

    error: function (message) {
        showMessage('gritter-error', message);
    },

    info: function (message) {
        showMessage('gritter-info', message);
    }
};
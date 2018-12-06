<div class="navbar">
    <div class="inner">
        <a href="${ctx}/" class="logo">
        ${appName}
        </a>

        <ul class="navbar-menus">
            <li data-pathname="">
                <a href="${ctx}/">首页</a>
            </li>
            <li>
                <a href="https://blog.kangyonggan.com" target="_blank">站长博客</a>
            </li>
            <li data-pathname="works">
                <a href="${ctx}/works">作品案例</a>
            </li>
            <li data-pathname="order">
                <a href="${ctx}/order">找我合作</a>
            </li>
            <li data-pathname="tools">
                <a href="javascript:">
                    常用工具<i class="fa fa-small fa-chevron-down"></i>
                </a>
                <ul class="sec-menus hidden">
                    <li>
                        <a href="#">XML格式化</a>
                    </li>
                    <li>
                        <a href="#">JSON格式化</a>
                    </li>
                    <li>
                        <a href="#">
                            SQL格式化
                            <i class="fa fa-small fa-right fa-caret-right"></i>
                        </a>
                        <ul class="sub-menus hidden">
                            <li>
                                <a href="#">MySQL</a>
                            </li>
                            <li>
                                <a href="#">SQLServer</a>
                            </li>
                            <li>
                                <a href="#">Oracle</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">身份证校验</a>
                    </li>
                    <li>
                        <a href="#">生成身份证</a>
                    </li>
                </ul>
            </li>
        </ul>

        <ul class="navbar-menus pull-right">
            <li data-pathname="login">
                <a href="${ctx}/login">登录</a>
            </li>
            <li data-pathname="register">
                <a href="${ctx}/register">注册</a>
            </li>
        </ul>
    </div>
</div>
<div style="height: 70px;"></div>

<style>
    .navbar {
        position: fixed;
        left: 0;
        right: 0;
        top: 0;
        z-index: 2;
        color: #fff;
        height: 70px;
        background: rgba(0, 0, 0, 0.88);
        font-size: 1rem;
        font-weight: 400;
    }

    .navbar a.logo {
        float: left;
        font-size: 20px;
        color: #fff;
    }

    .navbar a {
        display: inline-block;
        height: 70px;
        line-height: 70px;
        color: #ccc;
        text-decoration: none;
        text-align: center;
        letter-spacing: 3px;
    }

    .navbar a:hover {
        color: #fff;
    }

    .navbar .navbar-menus {
        float: left;
        list-style: none;
        margin-top: 0;
        padding-left: 0;
        margin-left: 50px;
    }

    .navbar .navbar-menus > li {
        float: left;
        margin-left: 20px;
        position: relative;
    }

    .navbar .navbar-menus > li.active a:after {
        content: "";
        position: absolute;
        bottom: 15px;
        left: 0;
        right: 0;
        margin: 0 auto;
        width: 4px;
        height: 4px;
        border-radius: 4px;
        background: #fff;
    }

    .navbar .sec-menus {
        position: absolute;
        left: 0;
        top: 70px;
        width: 150px;
        list-style: none;
        margin-top: 0;
        padding-left: 0;
        background: rgba(0, 0, 0, 0.88);
    }

    .navbar .sec-menus li {
        border-bottom: 1px solid #777;
        position: relative;
    }

    .navbar .sec-menus li a {
        display: inline-block;
        height: 36px;
        line-height: 36px;
        width: 120px;
        text-align: left;
        padding: 2px 15px;
        font-size: 13px;
        letter-spacing: 2px;
    }

    .navbar .sub-menus {
        position: absolute;
        left: 150px;
        top: 0;
        width: 150px;
        list-style: none;
        margin-top: 0;
        padding-left: 0;
        background: rgba(0, 0, 0, 0.88);
        border-left: 1px solid #777;
    }

    .navbar .sub-menus > li {
        border-bottom: 1px solid #777;
    }

    .navbar .sub-menus > li a {
        display: inline-block;
        height: 36px;
        line-height: 36px;
        width: 120px;
        text-align: left;
        padding: 2px 15px;
        font-size: 13px;
        letter-spacing: 2px;
    }

    .fa-small {
        font-size: 10px;
    }

    .fa-right {
        float: right;
        margin-top: 15px;
    }
</style>

<script>
    /**
     * 显示/隐藏 导航条
     *
     * @param type
     */
    function navbar(type) {
        var $navbar = $(".navbar");
        $navbar.stop();
        if (type === 'show') {
            $navbar.find(".inner").show();
            $navbar.animate({height: 70});
        } else if (type === 'hide') {
            $navbar.find(".inner").hide();
            $navbar.animate({height: 0});
        }
    }

    $(function () {
        // 滚动方向，0：下，1：上
        var scrollDirection = 1;

        // 上下滚动事件
        var scrollFunc = function (e) {
            e = e || window.event;
            if (e.wheelDelta) {
                //第一步：先判断浏览器IE，谷歌滑轮事件
                if (e.wheelDelta > 5) {
                    //当滑轮向上滚动时
                    if (scrollDirection === 0) {
                        navbar('show');
                        scrollDirection = 1;
                    }
                }
                if (e.wheelDelta < 0) {
                    //当滑轮向下滚动时
                    if (document.documentElement.scrollTop + document.body.scrollTop > 70) {
                        if (scrollDirection === 1) {
                            navbar('hide');
                            scrollDirection = 0;
                        }
                    }
                }
            } else if (e.detail) {
                //Firefox滑轮事件
                if (e.detail > 5) {
                    //当滑轮向上滚动时
                    if (scrollDirection === 0) {
                        navbar('show');
                        scrollDirection = 1;
                    }
                }
                if (e.detail < 0) {
                    //当滑轮向下滚动时
                    if (scrollDirection === 1) {
                        navbar('hide');
                        scrollDirection = 0;
                    }
                }
            }
        };

        //给页面绑定滑轮滚动事件
        if (document.addEventListener) {
            //firefox
            document.addEventListener('DOMMouseScroll', scrollFunc, false);
        }

        //滚动滑轮触发scrollFunc方法  //ie 谷歌
        window.onmousewheel = document.onmousewheel = scrollFunc;

        // 显示二级菜单
        $(".sec-menus").parent("li").hover(function () {
            $(this).find(".sec-menus").show();
            var $fa = $(this).find("i.fa-chevron-down");
            $fa.removeClass("fa-chevron-down");
            $fa.addClass("fa-chevron-up");
        }, function () {
            $(this).find(".sec-menus").hide();
            var $fa = $(this).find("i.fa-chevron-up");
            $fa.removeClass("fa-chevron-up");
            $fa.addClass("fa-chevron-down");
        });

        // 显示子菜单
        $(".sub-menus").parent("li").hover(function () {
            $(this).find(".sub-menus").show();
        }, function () {
            $(this).find(".sub-menus").hide();
        })

        // 激活导航条
        var pathname = window.location.pathname;
        pathname = pathname.substring(1);
        if (pathname) {
            var pathIndex = pathname.indexOf("/");
            if (pathIndex > 0) {
                pathname = pathname.substring(0, pathname);
            }
            var hashIndex = pathname.indexOf("?");
            if (hashIndex > 0) {
                pathname = pathname.substring(0, hashIndex);
            }
        }
        $(".navbar-menus > li").removeClass("active");
        $(".navbar-menus > li[data-pathname=" + pathname + "]").addClass("active");
    })
</script>

<div class="navbar navbar-height">
    <div class="inner">
        <a href="${ctx}/" class="logo">
        ${appName}
        </a>

        <ul class="navbar-menus hidden-sm">
            <li data-pathname="">
                <a href="${ctx}/">首页</a>
            </li>
        <@categories>
            <#list _categories as category>
                <#include "categories.ftl"/>
            </#list>
        </@categories>
        </ul>


        <div class="show-sm">
            <a href="javascript:" class="pull-right" id="drop-menu">
                <i class="fa fa-align-justify"></i>
            </a>
            <ul class="hidden" id="sm-menus">
                <li data-pathname="">
                    <a href="${ctx}/">首页</a>
                </li>
            <@categoriesDrop>
                <#list _categories as category>
                    <#assign href=ctx + category.url/>
                    <#if category.url?starts_with('/')>
                        <#assign href=ctx + category.url/>
                    <#else>
                        <#assign href=category.url/>
                    </#if>
                    <li>
                        <a href="${href}" <#if category.isBlank==1>target="_blank"</#if>>
                        ${category.categoryName}
                        </a>
                    </li>
                </#list>
            </@categoriesDrop>
            </ul>
        </div>

    <@app.guest>
        <ul class="navbar-menus pull-right hidden-sm">
            <li data-pathname="login">
                <a href="${ctx}/login">登录</a>
            </li>
        </ul>
    </@app.guest>
    <@app.user>
        <ul class="navbar-menus pull-right hidden-sm">
            <li data-pathname="user">
                <a href="javascript:">
                    <@app.user property='name' default='大佬'/><i class="fa fa-small fa-chevron-down"></i>
                </a>
                <ul class="sec-menus hidden">
                    <@app.hasMenu code="DASHBOARD">
                        <li>
                            <a href="${ctx}/dashboard">工作台</a>
                        </li>
                    </@app.hasMenu>
                    <li>
                        <a href="${ctx}/logout">安全退出</a>
                    </li>
                </ul>
            </li>
        </ul>
    </@app.user>
    </div>
</div>
<div class="navbar-height"></div>

<style>
    .navbar {
        position: fixed;
        left: 0;
        right: 0;
        top: 0;
        z-index: 2;
        color: #fff;
        background: rgba(0, 0, 0, 0.88);
        font-size: 1rem;
        font-weight: 400;
        padding: 0 50px;
    }

    .navbar-height {
        height: 60px;
    }

    .navbar a.logo {
        float: left;
        font-size: 20px;
        color: #fff;
    }

    .navbar a {
        display: inline-block;
        height: 60px;
        line-height: 60px;
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

    .navbar .navbar-menus > li.active > a:after {
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
        top: 60px;
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

    .navbar .sec-menus li:last-child {
        border-bottom: 0;
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

    .navbar #sm-menus {
        list-style: none;
        margin-top: 0;
        padding-left: 0;
        position: absolute;
        left: 0;
        right: 0;
        top: 45px;
        background: rgba(0, 0, 0, 0.8);
    }

    .navbar #sm-menus li a {
        display: block;
        text-align: left;
        text-indent: 5px;
        border-bottom: 1px solid rgba(255, 255, 255, 0.2);
    }

    /*中屏*/
    @media (max-width: 992px) {
        .navbar {
            padding: 0 20px;
        }
    }

    /*小屏*/
    @media (max-width: 768px) {
        .navbar {
            padding: 0 8px;
        }

        .navbar-height {
            height: 45px;
        }

        .navbar a {
            height: 45px;
            line-height: 45px;
        }

        #drop-menu {
            display: block;
            width: 45px;
            height: 45px;
            line-height: 45px;
        }

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
            $navbar.css("overflow", "visible");
            $navbar.animate({height: 60});
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
        if (pathname.indexOf("/") >= 0) {
            pathname = pathname.substring(0, pathname.indexOf("/"));
        }
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

        $("#drop-menu").toggle(function () {
            $("#sm-menus").removeClass("hidden");
        }, function () {
            $("#sm-menus").addClass("hidden");
        });
    })
</script>

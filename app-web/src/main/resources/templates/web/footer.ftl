<div class="footer">
    <div class="left hidden-sm">
        <div class="title">关于作者</div>
        <div class="desc">
            鄙人姓康，名永敢，字无心，而立之年，面如冠玉，唇红齿白...<br/>
            生性豁达，擅撸码，喜网游，三教九流，四诗五经，爱好甚广...<br/>
            冲天之志，荒于手，废于心，不望红绿，吾长安好，吾子安好...<br/>
        </div>
        <div class="space-10"></div>
    </div>
    <div class="center hidden-md">
        <div class="title">联系方式</div>
        <div class="contacts">
            <ul>
                <li>
                        <span title="微信">
                            <i class="fa fa-weixin"></i> Brave_Kang
                        </span>

                    <span title="QQ">
                            <i class="fa fa-qq"></i> 316071722
                        </span>
                </li>
                <li>
                        <span title="手机">
                            <i class="fa fa-phone"></i> 18516690317
                        </span>

                    <span title="邮箱">
                            <i class="fa fa-envelope-o"></i> java@kangyonggan.com
                        </span>
                </li>
            </ul>
        </div>
    </div>
    <div class="right">
        <div class="title">友情链接</div>
        <ul class="links">
            <li>
                <a href="https://ckfksc.com" target="_blank">疯狂赛车</a>
            </li>
            <li>
                <a href="http://ckssss.com" target="_blank">生生世世</a>
            </li>
            <li>
                <a href="https://houbb.github.io/" target="_blank">侯宾宾博客</a>
            </li>
            <li>
                <a href="http://www.ssme.gov.cn/" target="_blank">上海市小办</a>
            </li>
            <li>
                <a href="http://tzmh.shmh.gov.cn/" target="_blank">投资闵行网</a>
            </li>
        </ul>
    </div>

    <div class="space-10"></div>

    <div class="bottom">
        Copyright &copy; 2015-${.now?string('yyyy')}. <a href="http://www.miitbeian.gov.cn" target="_blank" class="hidden-sm"
                                                         style="color: #ddd">皖ICP备18009089号.</a>
        <a href="https://blog.kangyonggan.com/atom.xml" target="_blank" class="rss">
            <i class="fa fa-rss"></i>
        </a>
        <a href="https://github.com/kangyonggan/" target="_blank" class="rss">
            <i class="fa fa-github"></i>
        </a>
        <a href="javascript:" id="weixin" class="rss">
            <i class="fa fa-weixin"></i>
            <img src="${ctx}/app/images/weixin.png" class="hidden"/>
        </a>
        <a href="javascript:" id="alipay" class="rss">
            <i class="fa fa-heart"></i>
            <img src="${ctx}/app/images/alipay.png" class="hidden"/>
        </a>
    </div>
</div>

<a href="#" class="toTop hidden" id="back-top">
    <i class="fa fa-angle-up"></i>
</a>

<style>
    .footer {
        position: relative;
        padding: 0 50px;
        background: #000;
        color: #d4d4d4;
        font-size: 14px;
    }

    .footer a {
        color: #fff;
        text-decoration: none;
    }

    .footer .left {
        width: 31%;
        float: left;
        margin-top: 20px;
    }

    .footer .center {
        margin-left: 20px;
        width: 30%;
        float: left;
        margin-top: 20px;
    }

    .footer .right {
        margin-left: 20px;
        width: 30%;
        float: left;
        margin-top: 20px;
    }

    .footer .title {
        line-height: 40px;
        display: inline-block;
        font-size: 15px;
        color: #fff;
        border-bottom: 2px solid #fff;
    }

    .footer .desc {
        margin-top: 20px;
        line-height: 2;
        font-size: 13px;
        max-height: 102px;
        overflow: hidden;
    }

    .footer .bottom {
        border-top: 1px solid #444;
        padding-top: 25px;
        color: #d4d4d4;
    }

    .footer .contacts ul {
        list-style: none;
        padding-left: 0;
    }

    .footer .contacts ul li span {
        display: inline-block;
        line-height: 40px;
        min-width: 112px;
    }

    .footer .links {
        list-style: none;
        padding-left: 0;
    }

    .footer .links li {
        float: left;
    }

    .footer .links li a {
        display: inline-block;
        width: 94px;
        line-height: 40px;
        color: #ddd;
    }

    .footer .rss {
        font-size: 16px;
        color: #fff;
        float: right;
        margin-right: 20px;
    }

    .toTop {
        position: fixed;
        width: 35px;
        height: 35px;
        bottom: 70px;
        right: 35px;
        line-height: 35px;
        border-radius: 35px;
        text-align: center;
        background: #FFF;
    }

    .toTop:hover {
        background: #000;
        color: #fff;
    }

    #weixin img {
        position: absolute;
        left: 0;
        bottom: 100px;
        right: 0;
        width: 260px;
        height: 340px;
        margin: 0 auto;
        z-index: 1;
    }

    #alipay img {
        position: absolute;
        left: 0;
        bottom: 100px;
        right: 0;
        width: 300px;
        height: 300px;
        margin: 0 auto;
        z-index: 2;
    }

    @media (max-width: 1200px) {
        .hidden-md {
            display: none;
        }
        .footer .left {
            width: 50%;
        }
        .footer .right {
            width: 40%;
        }
    }

    /*中屏*/
    @media (max-width: 992px) {
        .footer {
            padding: 0 20px;
        }
    }

    /*小屏*/
    @media (max-width: 768px) {
        .footer {
            padding: 0 8px;
        }

        .footer .right {
            width: 90%;
            height: 160px;
        }
    }
</style>

<script>
    $(function () {
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
            navbar('show');
        });

        // 显示微信公共号的二维码
        $("#weixin").toggle(function () {
            $(this).find("img").removeClass("hidden");
        }, function () {
            $(this).find("img").addClass("hidden");
        });

        // 显示微信公共号的二维码
        $("#alipay").toggle(function () {
            $(this).find("img").removeClass("hidden");
        }, function () {
            $(this).find("img").addClass("hidden");
        });
    })
</script>

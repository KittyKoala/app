<div class="footer">
    <div class="inner">
        <div class="left">
            <div class="title">关于作者</div>
            <div class="desc">
                康永敢一直努力的向全栈看齐，多年的实战经验可轻松的为中小企业提供系统级解决方案，比如：后管系统、充值系统、门户网站、公共号、小程序等，并长期提供有保障的维护及售后。
            </div>
            <div class="space-10"></div>
            <div class="contacts">
                <a href="#" title="微信">
                    <i class="fa fa-weixin"></i>
                </a>
                <a href="#" title="QQ">
                    <i class="fa fa-qq"></i>
                </a>
                <a href="#" title="手机">
                    <i class="fa fa-phone"></i>
                </a>
                <a href="#" title="邮箱">
                    <i class="fa fa-envelope-o"></i>
                </a>
                <a href="#" title="项目">
                    <i class="fa fa-github"></i>
                </a>
            </div>
        </div>
        <div class="center">
            <div class="title">网站目录</div>
            <ul class="links">
                <li>
                    <a href="#">首页</a>
                </li>
                <li>
                    <a href="#">博客</a>
                </li>
                <li>
                    <a href="#">小说</a>
                </li>
                <li>
                    <a href="#">小棉袄</a>
                </li>
                <li>
                    <a href="#">作品</a>
                </li>
                <li>
                    <a href="#">工具</a>
                </li>
            </ul>
        </div>
        <div class="right">
            <div class="title">友情链接</div>
            <ul class="links">
                <li>
                    <a href="#">侯宾宾</a>
                </li>
                <li>
                    <a href="#">疯狂赛车</a>
                </li>
                <li>
                    <a href="#">市小办</a>
                </li>
                <li>
                    <a href="#">投资闵行网</a>
                </li>
            </ul>
        </div>

        <div class="space-10"></div>

        <div class="bottom">
            Copyright &copy; 2015-${.now?string('yyyy')} . <a href="http://www.miitbeian.gov.cn" target="_blank" style="color: #ddd">皖ICP备18009089号</a>.
        </div>
    </div>
</div>

<a href="#" class="toTop hidden" id="back-top">
    <i class="fa fa-angle-up"></i>
</a>

<style>
    .footer {
        background: #000;
        color: #d4d4d4;
        font-size: 14px;
    }

    .footer a {
        color: #fff;
        text-decoration: none;
    }

    .footer .left {
        width: 400px;
        float: left;
        margin-top: 20px;
    }

    .footer .center {
        margin-left: 20px;
        width: 300px;
        float: left;
        margin-top: 20px;
    }

    .footer .right {
        margin-left: 20px;
        width: 284px;
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
        padding-top: 20px;
        color: #d4d4d4;
    }

    .footer .contacts a {
        margin-right: 20px;
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
        width: 70px;
        line-height: 35px;
        color: #ddd;
    }

    .toTop {
        position: fixed;
        width: 35px;
        height: 35px;
        bottom: 25px;
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
        })
    })
</script>

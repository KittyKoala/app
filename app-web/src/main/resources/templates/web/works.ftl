<@override name="style">
<style>
    dl, dd, dt {
        padding: 0;
        margin: 0;
        width: 500px;
    }

    dl {
        margin-top: 40px;
        float: left;
    }

    .even {
        margin-left: 24px;
    }

    dl img {
        width: 500px;
        height: 300px;
    }

    dt {
        text-align: center;
        color: #777;
    }
</style>
</@override>

<@override name="main">
<div class="inner">
    <a href="https://ckfksc.com" target="_blank">
        <dl>
            <dd>
                <img src="${ctx}/app/images/works/ckfksc.png"/>
            </dd>
            <dt>疯狂赛车怀旧版</dt>
        </dl>
    </a>
    <a href="http://www.ssme.gov.cn" target="_blank">
        <dl class="even">
            <dd>
                <img src="${ctx}/app/images/works/ssme.png"/>
            </dd>
            <dt>上海市企业服务云</dt>
        </dl>
    </a>
</div>
</@override>

<@extends name="layout.ftl"/>
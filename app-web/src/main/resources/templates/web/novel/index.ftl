<#assign title="小说"/>

<@override name="style">
<style>
    .novel-list, .novel-list dl, .novel-list dt, .novel-list dd {
        margin: 0;
        padding: 0;
    }

    .novel-list {
        list-style: none;
        margin-top: 20px;
    }

    .novel-list li {
        float: left;
        width: 340px;
        margin-bottom: 20px;
    }

    .novel-list dd {
        float: left;
    }

    .novel-list dt {
        float: left;
        width: 190px;
        margin-left: 10px;
    }

    .novel-list a {
        color: rgb(111, 120, 167);
        text-decoration: none;
    }

    .novel-list .author {
        font-size: 13px;
        float: right;
        color: rgb(179, 179, 179);
    }

    .novel-list .summary {
        height: 127px;
        overflow: hidden;
        font-size: 13px;
        line-height: 20px;
        text-indent: 2em;
        padding: 7px 0px 0px;
    }

    .novel-list img {
        width: 124px;
        height: 154px;
        background-color: rgb(255, 255, 255);
        border-width: 1px;
        border-style: solid;
        border-color: rgb(221, 221, 221);
        border-image: initial;
        padding: 1px;
    }
</style>
</@override>

<@override name="main">
    <@panel>
    <ul class="novel-list">
        <#list 1..11 as i>
            <li>
                <dl>
                    <dd>
                        <a href="${ctx}/novel/123432">
                            <img src="${ctx}/app/images/demo.jpg"/>
                        </a>
                    </dd>
                    <dt>
                        <div>
                            <a href="${ctx}/novel/123432">逆天邪神</a>
                            <span class="author">火星引力</span>
                        </div>
                        <div class="summary">
                            大墟的祖训说，天黑，别出门。大墟残老村的老弱病残们从江边捡到了一个婴儿，取名秦牧，含辛茹苦将他养大。这一天夜幕降临，黑暗笼罩大墟，秦牧走出了家门…
                        </div>
                    </dt>
                </dl>
            </li>
        </#list>
        <div class="clear"></div>
    </ul>
    </@panel>
</@override>

<@extends name="../layout.ftl"/>
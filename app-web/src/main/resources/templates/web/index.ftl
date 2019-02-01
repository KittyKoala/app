<@override name="main">
    <@panel style="background: #000 url('${ctx}/app/images/index.jpg');background-size: cover;" class="hidden-sm">
        <#include "audio.ftl"/>
    </@panel>
    <div class="show-sm">
        <img src="${ctx}/app/images/index.jpg" style="width: 100%"/>
    </div>
</@override>

<@override name="script">
    <script>
        $("body").css({"background": "#000"});
    </script>
</@override>

<@extends name="layout.ftl"/>
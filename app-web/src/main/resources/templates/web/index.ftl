<@override name="main">
    <@panel style="background: #000 url('${ctx}/app/images/index.jpg');background-size: cover;">
        <#include "audio.ftl"/>
    </@panel>
</@override>

<@override name="script">
    <script>
        $("body").css({"background": "#000"});
    </script>
</@override>

<@extends name="layout.ftl"/>
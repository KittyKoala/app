<#assign title="首页"/>

<@override name="main">
<div class="space-12"></div>
<div class="col-xs-12">
    <div class="alert alert-block alert-success">
        <button type="button" class="close" data-dismiss="alert">
            <i class="ace-icon fa fa-times"></i>
        </button>

        <i class="ace-icon fa fa-check green"></i>

        欢迎登录<strong class="green">${appName}</strong>工作台。
    </div>

    <div class="center" id="skins">
        <span class="btn btn-app btn-sm btn-primary no-hover" style="margin-right: 20px;">
            <span class="line-height-1 bigger-120"> 商务蓝 </span>

            <br>
            <a class="line-height-1 smaller-75" href="javascript:" data-skin="no-skin" style="color: #fff"> 切换 </a>
        </span>

        <span class="btn btn-app btn-sm btn-inverse no-hover" style="margin-right: 20px;">
            <span class="line-height-1 bigger-120"> 炫酷黑 </span>

            <br>
            <a class="line-height-1 smaller-75" href="javascript:" data-skin="skin-1" style="color: #fff"> 切换 </a>
        </span>

        <span class="btn btn-app btn-sm btn-pink no-hover" style="margin-right: 20px;">
            <span class="line-height-1 bigger-120"> 少女粉 </span>

            <br>
            <a class="line-height-1 smaller-75" href="javascript:" data-skin="skin-2" style="color: #fff"> 切换 </a>
        </span>

        <span class="btn btn-app btn-sm btn-grey no-hover">
            <span class="line-height-1 bigger-120"> 深空灰 </span>

            <br>
            <a class="line-height-1 smaller-75" href="javascript:" data-skin="skin-3" style="color: #fff"> 切换 </a>
        </span>
    </div>
</div>
</@override>

<@override name="script">
<script>
    $('#DASHBOARD').addClass('active');

    $("#skins a").click(function () {
        var skin = $(this).data("skin");
        $.post(ctx + "/dashboard/changeSkin", {skin: skin}, function (response) {
            if ("0000" == response.respCo) {
                window.location.reload();
            } else {
                Message.error(response.respMsg);
            }
        });
    });
</script>
</@override>

<@extends name="layout.ftl"/>
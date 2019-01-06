<div class="navbar">
    <a href="${backUrl!'${ctx}/wap'}">
        <i class="fa fa-angle-left"></i>
        返回
    </a>

    ${title}
</div>

<style>
    .navbar {
        color: #ddd;
        border-bottom: 1px solid #ccc;
        padding: 0 15px;
        line-height: 40px;
        text-align: center;
        position: relative;
        font-size: 16px;
    }
    .navbar a {
        position: absolute;
        left: 15px;
        top: 0;
        color: #ddd;
        line-height: 40px;
        text-decoration: none;
    }
    .navbar a i {
        font-size: 20px;
    }
</style>
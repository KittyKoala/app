<#assign title="生成GIF"/>

<@override name="style">
<style>

</style>
</@override>

<@override name="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name=title/>
        </@breadcrumbs>

    <div class="border">
        <button id="btn">生成gif</button>
        <canvas width="480" height="480" id="canvas">

        </canvas>
        <img src="${ctx}/app/images/gif/10.png" id="img">
        <img src="" id="result">
    </div>
    </@panel>
</@override>

<@override name="script">
<script src="${ctx}/libs/gif/gif.js"></script>
<script>
    $(function () {
        var gif;
        var oImg = document.getElementById("img");
        var canvas = document.getElementById('canvas');
        var ctx = canvas.getContext('2d');
        $("#btn").click(function () {
            gif = new GIF({
                workers: 4,
                quality: 30,
                workerScript: "${ctx}/libs/gif/gif.worker.js"
            });
            gif.on("finished", function (blob) {
                var file = new FileReader();
                file.readAsDataURL(blob);
                file.onload = function () {
                    document.getElementById("result").setAttribute("src", file.result)
                }
            });

            var imgList = [
                '${ctx}/app/images/gif/10.png',
                '${ctx}/app/images/gif/11.png',
                '${ctx}/app/images/gif/12.png'
            ];
            var imgObjList = [], count = 0;

            for (var i = 0; i < imgList.length; i++) {
                var tmpImg = new Image();
                tmpImg.src = imgList[i];
                imgObjList.push(tmpImg);
                tmpImg.onload = function () {
                    count++;
                    if (count === imgList.length) {
                        generateGif(imgObjList);
                    }
                }
            }
        });

        function generateGif(imgObjList) {

            for (var i = 0; i < imgObjList.length; i++) {
                ctx.save();
                ctx.drawImage(oImg, 50, 100, 220, 220);
                ctx.drawImage(imgObjList[i], 0, 0, canvas.width, canvas.height);
                ctx.restore();
                gif.addFrame(canvas, {copy: true, delay: 300});
                ctx.clearRect(0, 0, canvas.width, canvas.height)
            }
            gif.render();
        }
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
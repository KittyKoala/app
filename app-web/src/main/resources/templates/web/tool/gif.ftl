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
        <input type="file" id="img1" data-title="第一张"/>
        <input type="file" id="img2" data-title="我第二"/>
        <button id="btn">生成gif</button>

        <canvas width="400" height="300" id="canvas"></canvas>

        <img src="" id="result">
    </div>
    </@panel>
</@override>

<@override name="script">
<script src="${ctx}/libs/gif/gif.js"></script>
<script>
    $(function () {
        var gif;
        var canvas = document.getElementById('canvas');
        var ctx = canvas.getContext('2d');

        var imgList = [];
        $("#img1").change(function () {
            var pic = this.files[0];
            var r = new FileReader();
            r.readAsDataURL(pic);

            r.onload = function () {
                imgList[0] = r.result;
            }
        });
        $("#img2").change(function () {
            var pic = this.files[0];
            var r = new FileReader();
            r.readAsDataURL(pic);

            r.onload = function () {
                imgList[1] = r.result;
            }
        });

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
                    document.getElementById("result").setAttribute("src", file.result);
                }
            });

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
                ctx.drawImage(imgObjList[i], 0, 0, canvas.width, canvas.height);
                ctx.font = "25px 隶书";
                ctx.fillStyle = "Green";
                ctx.fillText($(imgObjList[i]).data("title") ,30,130);

                ctx.restore();
                gif.addFrame(canvas, {copy: true, delay: 1000});
                ctx.clearRect(0, 0, canvas.width, canvas.height)
            }
            gif.render();
        }
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
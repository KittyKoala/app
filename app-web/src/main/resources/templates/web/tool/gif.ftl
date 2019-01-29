<#assign title="生成GIF"/>

<@override name="style">
<style>
    .form input[type=text] {
        background: #fff;
        border: 1px solid #aaa;
        color: #333;
    }

    .imgText {
        width: 195px !important;
        position: absolute !important;
        bottom: 0;
        left: 0;
    }

    .form .btn {
        border: 1px solid #666;
        color: #333;
    }

    .frame {
        margin: 10px;
        float: left;
        width: 230px;
        height: 210px;
        cursor: pointer;
        position: relative;
    }

    .frame img {
        width: 230px;
        height: 160px;
    }

    .frame .file-label {
        border: 1px dashed #ccc;
        border-radius: 8px;
        width: 230px;
        height: 160px;
        text-align: center;
        line-height: 160px;
        color: #999;
    }

    .frame .file-label:hover {
        color: #a72c11;
        border: 1px dashed #a72c11;
    }

    .frame div.text {
        position: absolute;
        bottom: 60px;
        left: 10px;
        width: 210px;
        overflow: hidden;
        font-size: 14px;
        text-align: center;
    }

    .frame .font-color {
        position: absolute;
        display: inline-block;
        bottom: 0px;
        right: 0;
        width: 30px;
        height: 30px;
        background: rgba(0, 0, 0, 0.7);
        border: 1px solid #333;
    }

    .frame .black {
        color: rgba(0, 0, 0, 0.8);
    }

    .frame .white {
        color: rgba(255, 255, 255, 0.8);
    }

    .frame .bg-black {
        background: rgba(0, 0, 0, 0.8);
    }

    .frame .bg-white {
        background: rgba(255, 255, 255, 0.8);
    }

    .more {
        margin: 10px;
        float: left;
        width: 230px;
        height: 160px;
        border: 1px dashed #ccc;
        border-radius: 8px;
        cursor: pointer;
        text-align: center;
        line-height: 160px;
    }

    .more:hover {
        border: 1px dashed #a72c11;
    }

    .more i {
        font-size: 30px;
        color: #333;
    }

    #result {
        margin: 20px;
    }

    .num {
        width: 60px;
        height: 30px;
        line-height: pxpx;
        box-sizing: border-box;
        outline: none;
        font-size: 14px;
        padding: 2px 5px;
        margin-right: 10px;
        margin-top: 5px;
        background: #fff;
        border: 1px solid #aaa;
        color: #333;
    }
</style>
</@override>

<@override name="main">
<div class="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name=title/>
        </@breadcrumbs>

        <div class="border">
            <div class="form">
                <div id="frame-list">
                    <div class="frame">
                        <img class="hidden" data-type="changeImg"/>
                        <div class="file-label" data-type="changeImg">第1帧</div>
                        <div class="text black" data-type="changeImg"></div>
                        <input type="file" data-index="0" class="hidden" data-type="frameImg"/>
                        <input type="text" class="imgText" data-index="0" placeholder="给这张图配段文字吧"
                               data-type="frameText"/>
                        <a class="font-color" data-index="0" data-type="frameColor" title="切换配字颜色"></a>
                    </div>
                </div>
                <div class="more">
                    <i class="fa fa-plus"></i>
                </div>
                <div class="clear"></div>
                <div>
                    GIF宽：<input type="number" class="num" id="imgWidth" value="230"/> GIF高：<input type="number"
                                                                                                  class="num"
                                                                                                  id="imgHeight"
                                                                                                  value="160"/>
                    <div style="height: 10px;"></div>
                    <button class="btn" id="submit" data-loading-text="正在生成...">
                        <i class="fa fa-cloud-download"></i>
                        生成GIF
                    </button>
                    <button class="btn" id="reset">
                        <i class="fa fa-undo"></i>
                        重置
                    </button>
                </div>

                <canvas class="hidden" id="canvas"></canvas>

                <img src="" id="result">
            </div>
        </div>
    </@panel>
</div>
</@override>

<@override name="script">
<script src="${ctx}/libs/gif/gif.js"></script>
<script>
    $(function () {
        // 生成gif的图片集合
        var data = {
            textList: [],
            colorList: [],
            imgList: []
        }, tempData = {
            textList: [],
            colorList: [],
            imgList: []
        }, total = 1;

        /**
         * 点击上传图片
         */
        $(document).on("click", "[data-type='changeImg']", function (e) {
            e.preventDefault();
            $(this).parents(".frame").find("input[type=file]").click();
            return false;
        });
        /**
         * 输入文字
         */
        $(document).on("keyup", "[data-type='frameText']", function () {
            $(this).parents(".frame").find(".text").html($(this).val());
            var index = $(this).data("index");
            data.textList[index] = $(this).val();
        });
        /**
         * 改变字体颜色
         */
        $(document).on("click", "[data-type='frameColor']", function () {
            var $text = $(this).parents(".frame").find(".text");
            var index = $(this).data("index");
            if ($text.hasClass("black")) {
                $text.removeClass("black");
                $text.addClass("white");
                $(this).removeClass("bg-black");
                $(this).addClass("bg-white");
                data.colorList[index] = "White";
            } else {
                $text.removeClass("white");
                $text.addClass("black");
                $(this).removeClass("bg-white");
                $(this).addClass("bg-black");
                data.colorList[index] = "Black";
            }
        });
        /**
         * 图片改变事件
         */
        $(document).on("change", "[data-type='frameImg']", function (e) {
            e.preventDefault();
            var pic = this.files[0];
            // 校验
            if (!validPic(pic)) {
                return;
            }

            var $frame = $(this).parents(".frame");

            // 图片预览
            var file = new FileReader();
            file.readAsDataURL(pic);

            var index = $(this).data("index");

            file.onload = function () {
                $frame.find("img").attr("src", this.result).show();
                $frame.find(".file-label").hide();
                data.imgList[index] = this.result;
            };
            return false;
        });

        /**
         * 文件校验
         *
         * @param pic
         * @returns {boolean}
         */
        function validPic(pic) {
            if (!pic) {
                return false;
            }

            // 格式校验
            var ext = pic.name.substring(pic.name.lastIndexOf(".") + 1).toLowerCase();
            if ("jpeg,jpg,png".indexOf(ext) === -1) {
                alert("请选择 png、jpeg、jpg 格式的图片文件");
                return false;
            }
            return true;
        }

        /**
         * 添加一帧
         */
        $(".more").click(function () {
            total++;
            $("#frame-list").append('<div class="frame">\n' +
                    '                    <img class="hidden" data-type="changeImg"/>\n' +
                    '                    <div class="file-label" data-type="changeImg">第' + total + '帧</div>\n' +
                    '                    <div class="text black" data-type="changeImg"></div>\n' +
                    '                    <input type="file" data-index="' + (total - 1) + '" class="hidden" data-type="frameImg"/>\n' +
                    '                    <input type="text" class="imgText" data-index="' + (total - 1) + '" placeholder="给这张图配段文字吧" data-type="frameText"/>\n' +
                    '                    <a class="font-color" data-index="' + (total - 1) + '" data-type="frameColor" title="切换配字颜色"></a>\n' +
                    '                </div>');
        });

        var gif;
        var canvas = document.getElementById('canvas');
        var ctx = canvas.getContext('2d');

        /**
         * 开始生成
         */
        $("#submit").click(function () {
            var imgWidth = $("#imgWidth").val();
            var imgHeight = $("#imgHeight").val();

            if (imgHeight < 100 || imgWidth < 100) {
                alert("GIF宽度/高度不得小于100");
                return;
            }

            if (imgHeight > 1000 || imgWidth > 1000) {
                alert("GIF宽度/高度不得大于1000");
                return;
            }

            var $btn = $(this);

            $btn.button('loading');

            var tempTotal = 0;
            for (var i = 0; i < data.imgList.length; i++) {
                if (data.imgList[i]) {
                    tempData.imgList[tempTotal] = data.imgList[i];
                    tempData.colorList[tempTotal] = data.colorList[i];
                    tempData.textList[tempTotal++] = data.textList[i];
                }
            }

            if (tempTotal === 0) {
                alert("请选择图片");
                $btn.button('reset');
                return;
            }

            // 设置大小
            $("#result").css({
                "width": imgWidth + "px",
                "height": imgHeight + "px"
            });
            $("#canvas").attr("width", imgWidth);
            $("#canvas").attr("height", imgHeight);

            gif = new GIF({
                workers: total,
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

            for (var i = 0; i < tempData.imgList.length; i++) {
                var tmpImg = new Image();
                tmpImg.src = tempData.imgList[i];
                imgObjList.push(tmpImg);
                tmpImg.onload = function () {
                    count++;
                    if (count === tempData.imgList.length) {
                        generateGif(imgObjList);
                    }
                }
            }

            $btn.button('reset');
        });

        /**
         * 生成gif
         *
         * @param imgObjList
         */
        function generateGif(imgObjList) {
            for (var i = 0; i < imgObjList.length; i++) {
                ctx.save();
                ctx.drawImage(imgObjList[i], 0, 0, canvas.width, canvas.height);

                var title = tempData.textList[i];

                if (title) {
                    var imgWidth = $("#imgWidth").val();
                    var imgHeight = $("#imgHeight").val();
                    ctx.font = "14px 宋体";
                    ctx.fillStyle = tempData.colorList[i];
                    ctx.textAlign = "center";
                    ctx.fillText(title, imgWidth / 2, imgHeight - 10);
                }

                ctx.restore();
                gif.addFrame(canvas, {copy: true, delay: 1500});
                ctx.clearRect(0, 0, canvas.width, canvas.height)
            }
            gif.render();
        }

        /**
         * 重置
         */
        $("#reset").click(function () {
            window.location.reload();
        });
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
<#assign title="猜数字"/>

<@override name="style">
<style>
    .border {
        text-align: center;
    }

    .game .btn {
        border: 1px solid #666;
        color: #333;
        margin-top: 15px;
    }

    .game input[type=number] {
        width: 260px;
        height: 34px;
        line-height: 34px;
        box-sizing: border-box;
        outline: none;
        font-size: 14px;
        padding: 4px 10px;
        background: #fff;
        border: 1px solid #aaa;
        color: #333;
    }

    .result {
        margin-top: 20px;
        width: 100%;
        color: #333333;
        border-collapse: collapse;
        border: 1px solid #ccc;
    }

    .result tr td, .result tr th {
        border: 1px solid #ccc;
        padding: 8px;
        font-size: 14px;
    }

    .result th {
        background: #f2f2f2;
    }

    .intro {
        text-align: left;
        font-size: 14px;
        color: #777;
    }
</style>
</@override>

<@override name="main">
    <@panel>
        <@breadcrumbs>
            <@breadcrumb name=title/>
        </@breadcrumbs>

    <div class="border">
        <div class="game">
            <input type="number" id="num" placeholder="请输入4位不重复的数字"/>

            <button id="guess" class="btn">
                猜一次(8)
            </button>
            <button id="giveUp" class="btn">
                放弃本局
            </button>
            <button id="reset" class="btn" data-loading-text="正在生成...">
                重新开局
            </button>
        </div>

        <table class="result">
            <thead>
            <tr>
                <th>猜测次数</th>
                <th>猜测数字</th>
                <th>点评</th>
            </tr>
            </thead>
            <tbody id="guess-list">
            </tbody>
        </table>

        <div class="intro">
            <h3>游戏规则 | Introduce</h3>
            <ol>
                <li>开局后，系统会随机生成不重复的4个数字（0~9），玩家可以猜8次，若数字和位置都相同则为A，若数字相同但位置不同则为B，每次猜过之后系统都会给出几个A和几个B。</li>
                <li>例：正确答案是2534，玩家猜2305，系统则会给出1A2B。表示有1个数字和位置都正确，有2个数字正确但位置不正确。</li>
                <li>本游戏可锻炼大脑的推理逻辑，适当游戏益闹，过度游戏伤身。</li>
            </ol>
        </div>
    </div>

    </@panel>
</@override>

<@override name="script">
<script>
    $(function () {
        /**
         * 正确答案
         */
        var answer;

        /**
         * 是否已经游戏结束
         */
        var isGameOver = 0;

        /**
         * 剩余猜测次数
         */
        var times = 8;

        /**
         * 已经回答过的数字
         */
        var oldNums = [];

        $("#reset").click(reset);

        /**
         * 重新开局
         */
        function reset() {
            // 清空
            isGameOver = 0;
            times = 8;
            oldNums = [];
            $("#num").val("");
            $("#guess").html("猜一次(8)");
            $("#guess-list").empty();
            $("#guess-list").append("<tr><td colspan='3'>还没有猜测</td></tr>");

            $.get("${ctx}/game/genNum", function (response) {
                if (response.respCo === '0000') {
                    answer = response.num;
                } else {
                    alert(response.respMsg);
                }
            })
        }

        /**
         * 猜一次
         */
        $("#guess").click(function () {
            if (isGameOver) {
                alert("游戏已经结束，请重新开局");
                return;
            }
            var num = $("#num").val();
            if (!num || num.trim().length !== 4 || !isNum(num)) {
                alert('请输入4位不重复的数字');
                return;
            }
            if (isRepeatNum(num)) {
                alert('这是一个已经猜测过的错误数字');
                return;
            }

            // 第一次清空"还没有猜测"
            if (times === 8) {
                $("#guess-list").empty();
            }
            times--;
            $("#num").val("");
            oldNums[7 - times] = num;
            $("#guess").html("猜一次(" + times + ")");

            // 判断是否为正确答案
            if (num === answer) {
                $("#guess-list").append("<tr><td>" + (8 - times) + "</td><td>" + num + "</td><td>正确</td></tr>");

                isGameOver = 1;
                return;
            }

            // 获取几A几B
            var result = getResult(num);

            // 追加错误结果
            $("#guess-list").append("<tr><td>" + (8 - times) + "</td><td>" + num + "</td><td>" + result + "</td></tr>");

            if (times <= 0) {
                gameOver();
            }
        });

        /**
         * 初始化时开局
         */
        reset();

        /**
         * 放弃本局
         */
        $("#giveUp").click(function () {
            if (isGameOver) {
                alert("游戏已经结束，请重新开局");
                return;
            }
            if (times === 8) {
                $("#guess-list").empty();
            }
            gameOver();
        });

        /**
         * 游戏结束
         */
        function gameOver() {
            isGameOver = 1;
            // 显示正确答案
            $("#guess-list").append("<tr><td colspan='3'>回答失败！正确答案是：" + answer + "</td></tr>");
        }

        /**
         * 判断是否是不重复的数字
         *
         * @param num
         */
        function isNum(num) {
            for (var i = 0; i < num.length - 1; i++) {
                for (var j = i + 1; j < num.length; j++) {
                    if (num[i] === num[j]) {
                        return false;
                    }
                }
            }

            return true;
        }

        /**
         * 判断是否是已经回答过的数字
         *
         * @param num
         */
        function isRepeatNum(num) {
            for (var i = 0; i < oldNums.length; i++) {
                if (oldNums[i] === num) {
                    return true;
                }
            }

            return false;
        }

        /**
         * 获取几A几B
         *
         * @param num
         */
        function getResult(num) {
            var numA = 0;
            var numB = 0;
            for (var i = 0; i < num.length; i++) {
                var targetNum = num[i];
                for (var j = 0; j < answer.length; j++) {
                    // 先判断数字是否正确
                    if (answer[j] === targetNum) {
                        // 再判断位置是否正确
                        if (i === j) {
                            numA++;
                        } else {
                            numB++;
                        }
                    }
                }
            }

            return numA + "A" + numB + "B";
        }
    })
</script>
</@override>

<@extends name="../layout.ftl"/>
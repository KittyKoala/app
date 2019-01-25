<audio id="bg-audio" src="${ctx}/app/audio/bg.mp3" autoplay="autoplay"></audio>

<a id="audioBtn" href="javascript:">
    <i class="fa fa-music faa-bounce animated"></i>
</a>

<style>
    #audioBtn {
        position: fixed;
        right: 30px;
        top: 72px;
        font-size: 25px;
        color: ${color!'#ff5151'};
    }
</style>

<script>
    $(function () {
        var audio = document.getElementById("bg-audio");

        $("#audioBtn").click(function () {
            if (audio.paused) {
                audio.play();
                $("#audioBtn").css({"color": "${color!'#ff5151'}"});
            } else {
                audio.pause();
                $("#audioBtn").css({"color": "${color!'#ccc'}"});
            }
        });
    })
</script>
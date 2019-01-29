<audio id="bg-audio" src="${ctx}/app/audio/bg.mp3" loop="loop"></audio>

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
                $("#audioBtn i").addClass("animated");
            } else {
                audio.pause();
                $("#audioBtn").css({"color": "${color!'#ccc'}"});
                $("#audioBtn i").removeClass("animated");
            }
        });
    })
</script>

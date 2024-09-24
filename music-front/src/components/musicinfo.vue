<template>
    <div class="music-info" v-if="isDefaultView">
        <!-- 音乐播放信息 -->
        <!--左侧音乐封面-->
        <div class="music-img">
            <img :src="this.$store.state.music.icon_url">
        </div>
        <!--右侧歌词-->
        <div class="music-lyric">
            <!--歌名-->
            <div class="music-title">
                {{ this.$store.state.music.title }}
            </div><br />
            <!--作者-->
            <div class="music-artilst">
                {{ this.$store.state.music.artist }}
            </div><br />
            {{ lyric }}
        </div>
    </div>
    <div class="music-info" v-else-if="!isDefaultView">
        <!-- 音乐播放信息 -->
        <!--左侧音乐封面-->
        <div class="music-img">
            <img src="../image/unicorn.jpeg">
        </div>
        <!--右侧歌词-->
        <div class="music-lyric">
            <!--歌名-->
            <div class="music-title">
                请选择音乐
            </div><br />
            <!--作者-->
            <div class="music-artilst">
                无
            </div><br />
            {{ lyric }}
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            lyric: "",
            isDefaultView: false, // 是否显示默认视图
            audio: new Audio(),
        }
    },
    methods: {
        // 通过vuex得到音乐id然后请求歌词
        getLyric() {
            this.lyric = "暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词暂无歌词";
        },
        // 把播放时间转换为00:00的结构
        formatTime(seconds) {
            const minutes = Math.floor(seconds / 60);
            const secondsLeft = Math.floor(seconds % 60);
            return `${String(minutes).padStart(2, '0')}:${String(secondsLeft).padStart(2, '0')}`;
        },
    },
    created() {
        this.audio = this.$store.state.audio
        this.getLyric()
        console.log("获取音乐信息：", this.$store.state.music)
        if (this.$store.state.music) {
            this.isDefaultView = true
        } else {
            console.log("未获取到音乐信息,显示默认视图")
        }
    },
    mounted() {

        // 监听音频播放状态变化
        this.audio.addEventListener('timeupdate', () => {
            this.currentTime = this.formatTime(this.audio.currentTime);
            // 保存到vux
            this.$store.commit("saveCurrentTime", this.$store.state.audio.currentTime)
            // console.log("当前音乐播放时间:", this.currentTime)
        });

        // 监听音频播放结束
        this.audio.addEventListener('ended', () => {
            console.log('音频播放结束');
            this.currentTime = 0;
        });
    },
}
</script>

<style>
.music-info {
    display: flex;
    flex-direction: row;
    /* 水平排列 */
    padding: 40px;
    box-sizing: border-box;
    align-items: center;
    /* 垂直居中 */
    justify-content: space-between;
    /* 左右对齐 */
    width: calc(100% - 20px);
    /* 减去padding的总和 */
    max-width: 1300px;
    /* 最大宽度限制 */
    margin: 0 auto;
    /* 居中 */
}

.music-img {
    position: relative;
    flex: 0 0 auto;
    /* 不允许收缩或增长 */
    margin-right: 400px;
    /* 右边距 */
    transition: transform 0.5s ease-in-out;
    /* 过渡效果 */
}

.music-img:hover img {
    transform: scale(1.1);
    /* 鼠标悬停时放大 */
}

.music-img img {
    width: 450px;
    /* 图片宽度 */
    height: 300px;
    object-fit: cover;
    /* 图像填充方式 */
    border-radius: 10px;
    /* 圆角 */
}

.music-lyric {
    flex: 1;
    /* 允许伸展以填充剩余空间 */
    font-size: 16px;
    /* 字体大小 */
    line-height: 1.5em;
    /* 行高 */
    overflow-y: scroll;
    /* 启用垂直滚动 */
    -ms-overflow-style: none;
    /* IE and Edge */
    scrollbar-width: none;
    /* Firefox */
}

.music-lyric::-webkit-scrollbar {
    display: none;
    /* Safari 和 Chrome */
}

.music-title {
    flex: 1;
    font-size: 36px;
    line-height: 3.5em;
    margin-bottom: 130px;
}

.music-artilst {
    flex: 1;
    font-size: 26px;
    line-height: 2.5em;
}

/* 响应式设计 */
@media (max-width: 768px) {
    .music-info {
        flex-direction: column;
        /* 小屏幕垂直堆叠 */
        align-items: center;
        /* 内容居中 */
    }

    .music-img {
        margin-right: 0px;
        margin-left: 60px;
        margin-bottom: 20px;
        /* 底边距 */
    }

    .music-img img {
        width: 240px;
        /* 图片宽度 */
        height: 200px;
        object-fit: cover;
        /* 图像填充方式 */
        border-radius: 10px;
        /* 圆角 */
    }
}
</style>
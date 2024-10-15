<template>
    <div class="recommendations">
        <div class="recommendations-header">
            推荐音乐
        </div>
        <div class="recommendations-body">
            <div class="recommendation-card" v-for="(recommendation) in recommendations" :key="recommendation.id"
                @click="handleCardClick(recommendation.id)">
                <div class="recommendation-cover">
                    <img :src="recommendation.icon_url" alt="">
                </div>
                <div class="recommendation-info">
                    <div class="recommendation-title">{{ recommendation.title }}</div>
                    <div class="recommendation-artist">{{ recommendation.artist }}</div>
                    <button class="play-button" @click.stop="handlePlayClick(recommendation.id)">
                        <div v-if="!recommendation.playing">
                            <transition name="fade">
                                <svg t="1725370962119" class="p-icon" viewBox="0 0 1024 1024" version="1.1"
                                    xmlns="http://www.w3.org/2000/svg" p-id="3529" width="60" height="60">
                                    <path
                                        d="M512 58.632258c140.890839 1.263484 261.144774 36.612129 338.299871 115.072C928.755613 250.851097 964.104258 371.109161 965.367742 512c-1.263484 140.890839-36.612129 261.144774-115.067871 338.299871-77.155097 78.455742-197.409032 113.804387-338.299871 115.067871-140.890839-1.263484-261.148903-36.612129-338.295742-115.067871C95.244387 773.144774 59.895742 652.890839 58.632258 512c1.263484-140.890839 36.612129-261.148903 115.072-338.295742C250.851097 95.244387 371.109161 59.895742 512 58.632258z"
                                        fill="#6EDBA2" p-id="3530"></path>
                                    <path
                                        d="M385.515355 364.791742c6.561032-44.531613 38.660129-71.89471 73.377032-49.589677 61.419355 39.064774 133.326452 97.094194 205.563871 147.563354 42.244129 29.588645 42.244129 68.884645 0 98.465033-72.237419 50.47329-144.144516 108.498581-205.563871 147.559225-34.716903 22.305032-66.816-5.062194-73.377032-49.589677a1204.327226 1204.327226 0 0 1 0-294.408258z"
                                        fill="#FFFFFF" opacity=".8" p-id="3531"></path>
                                </svg>
                            </transition>
                        </div>
                        <!--暂停播放按钮-->
                        <transition name="fade">
                            <div v-if="recommendation.playing">
                                <svg t="1726187728687" class="p-icon" viewBox="0 0 1024 1024" version="1.1"
                                    xmlns="http://www.w3.org/2000/svg" p-id="7795" width="60" height="60">
                                    <path d="M512 483.4m-380 0a380 380 0 1 0 760 0 380 380 0 1 0-760 0Z" fill="#31BC69"
                                        p-id="7796">
                                    </path>
                                    <path
                                        d="M595.5 588.4h-167c-11.8 0-21.5-9.7-21.5-21.5v-167c0-11.8 9.7-21.5 21.5-21.5h167c11.8 0 21.5 9.7 21.5 21.5v167c0 11.8-9.7 21.5-21.5 21.5z"
                                        fill="#FFFFFF" p-id="7797"></path>
                                </svg>
                            </div>
                        </transition>
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import api from '@/api/music'

import eventBus from '@/utils/eventBus';

export default {
    data() {
        return {
            recommendations: [
            ],
            audio: new Audio(),
            // 记录正在播放的音乐id
            musicid: null,
            // 当前音乐播放时间
            currentTime: 0,
            // 当前播放音乐总时长
            duration: 0,
        };
    },
    methods: {
        handleCardClick(id) {
            console.log(id);
            // 根据id出现对应的音乐信息然后切换到播放页面

            // 先暂时模拟从前端查询到音乐信息
            // var music = { id: 1, title: '音乐标题1', artist: '艺术家1', url: '../music/11.稻香.wav', iconurl: "https://awsimage-1.oss-cn-hangzhou.aliyuncs.com/image-20240920091824796.png", fine_picture_url: "https://awsimage-1.oss-cn-hangzhou.aliyuncs.com/image-20240920091824796.png", playing: false }
            var music = this.recommendations[id ? this.recommendations.findIndex(item => item.id === id) : index]
            // 保存音乐 audio到vuex
            this.$store.commit("saveMusicInfo", music)
            // 如果不在播放音乐则更换进度条小图标
            if (!this.$store.state.playstatus) {
                this.$store.commit("saveMusicIcon", this.recommendations[id ? this.recommendations.findIndex(item => item.id === id) : index].icon_url)
            }
            // 如果还没选择音乐则设置改音乐为要播放的音乐
            // 设置音频的 src 并保存到 Vuex
            if (!this.$store.state.audio.src) {
                this.audio.src = music.url;
                this.$store.dispatch('setAudioSrc', music.url);
                this.$store.commit("saveMusicId", id)
                this.$store.commit("saveCurrentTime", 0)
                // 设置播放时长
                this.duration = this.formatTime(this.audio.duration)
                // 把推荐歌单保存到vux的当前歌单里
                this.$store.commit("saveMusicList", this.recommendations);
            }
            var tableroute = [
                { isIndex: false },
                { isMusicInfo: true },
                { isCcollect: false },
                { isUpload: false}
            ]
            this.$store.commit("saveTableRoute", tableroute)
        },
        async getRecommedations() {
            await api.getRecommendationsMusic().then(response => {
                // console.log(response.data)
                this.recommendations = response.data.data;
                this.initPlay();
            }).catch(error => {
                console.error(error);
            });

        },
        handlePlayClick(id) {
            // console.log(id)
            // 找到对应的音乐卡片
            const index = this.recommendations.findIndex(item => item.id === id);

            // 如果音乐id不是之前保存的或者音乐id为null则先暂停播放音乐并让所有按钮图标样式归位
            if (this.$store.state.musicid !== id || this.$store.state.musicid == null) {
                // this.audio.pause();
                // this.$store.commit("savePlayStatus", false);
                this.$store.commit("saveMusicIcon", this.recommendations[id ? this.recommendations.findIndex(item => item.id === id) : index].icon_url)
                // 保存音乐 audio到vuex
                this.audio.src = this.recommendations[index].url;
                this.$store.commit("saveAudio", this.audio)
                this.$store.commit("saveMusicId", id)

                this.recommendations[this.musicid ? this.recommendations.findIndex(item => item.id === this.musicid) : index].playing = false;
                // 设置播放时长
                this.duration = this.formatTime(this.audio.duration)
                // console.log("得到初始化的时长:", this.duration)
            }
            // 改变对应的音乐卡片按钮的svg播放图标
            this.recommendations[index].playing = !this.recommendations[index].playing;

            if (this.recommendations[index].playing) {
                // 开始播放音频
                // this.audio.src = require(`@/music/${id}.wav`);
                // 根据id查询要播放音乐的url
                // 把播放的音乐保存到vuex
                this.$store.commit("saveMusicInfo", this.recommendations[index])
                // this.$store.commit("saveAudio", this.audio)
                this.$store.commit("saveMusicIcon", this.recommendations[this.musicid ? this.recommendations.findIndex(item => item.id === this.musicid) : index].icon_url)
                this.$store.commit("saveMusicId", id)
                this.$store.commit("savePlayStatus", true);
                // this.audio.play();
                this.$store.state.audio.play()
                // 保存音乐id
                this.musicid = id
                this.updatePlayStyle(true);
                // 把推荐歌单保存到vux的当前歌单里
                this.$store.commit("saveMusicList", this.recommendations);
            } else {
                // 暂停播放
                // this.audio.pause();
                this.$store.state.audio.pause()
            }
            // 改变进度条播放按钮样式
            this.$store.commit("savePlayStatus", this.recommendations[index].playing);
        },
        // 把播放时间转换为00:00的结构
        formatTime(seconds) {
            const minutes = Math.floor(seconds / 60);
            const secondsLeft = Math.floor(seconds % 60);
            return `${String(minutes).padStart(2, '0')}:${String(secondsLeft).padStart(2, '0')}`;
        },
        // 从vuex里读取当前播放音乐的id，以便重新进入这个页面的时候改变对应的播放按钮样式
        initPlay() {
            if (this.$store.state.musicid && this.$store.state.playstatus) {
                console.log("开始改变样式")
                // 修改对应的播放按钮样式
                const index = this.recommendations.findIndex(item => item.id === this.$store.state.musicid);
                // 改变对应的音乐卡片按钮的svg播放图标

                this.recommendations[index].playing = true;

            }
        },
        updatePlayStyle(newVal) {
            // 修改对应的播放按钮样式
            const index = this.recommendations.findIndex(item => item.id === this.$store.state.musicid);
            // 改变对应的音乐卡片按钮的svg播放图标
            this.recommendations[index].playing = newVal;
        },
        handleAudioEnd(event) {
            console.log('Audio has ended.');
            // 执行你想在移除音频结束监听器后做的操作
        }
    },
    created() {
        this.getRecommedations();
        this.audio = this.$store.state.audio;
        // console.log(this.recommendations)
        // this.initPlay()
    },
    watch: {
        // 全局播放状态
        "$store.state.playstatus": {
            deep: true,
            handler: function (newVal, oldVal) {
                this.updatePlayStyle(newVal)
            }
        },
        
        "$store.state.updateCurrentTime": {
            deep: true,
            handler: function (newVal, oldVal) {
                if (newVal) {
                    // 修改音乐播放进度
                    // console.log("修改播放进度")
                    this.audio.currentTime = newVal
                    this.$store.commit("saveAudio", this.audio)
                }
            }
        },

    },
    mounted() {
        //  接收到进度条组件的下一首请求，修改卡片播放按钮样式
        eventBus.$on('changeIndex', target => {
            this.updatePlayStyle(true);
            this.recommendations[target].playing = false;
        })

        //  接收到进度条组件的上一首请求，修改卡片播放按钮样式
        eventBus.$on('changeUpIndex', target => {
            this.updatePlayStyle(true);
            this.recommendations[target].playing = false;
        })



        // 监听音频播放状态变化
        this.audio.addEventListener('timeupdate', () => {
            // this.currentTime = this.formatTime(this.audio.currentTime);
            // 保存到vux
            this.$store.commit("saveCurrentTime", this.$store.state.audio.currentTime)
            // console.log("当前音乐播放时间:",this.currentTime)
        });

        // 监听音频播放结束
        this.audio.addEventListener('ended', async () => {
            console.log('音频播放结束');
            // 播放下一首
            const currentIndex = this.$store.state.musicList.findIndex(item => item.id === this.$store.state.musicid);
            let index = parseInt(currentIndex + 1);
            if (this.$store.state.musicList[index]) {
                this.audio.src = this.$store.state.musicList[index].url;
                // 设置播放时长
                this.duration = this.formatTime(this.audio.duration)
                this.$store.commit("saveAudio", this.audio)
                this.$store.commit("saveMusicInfo", this.$store.state.musicList[index]);
                this.$store.commit("saveCurrentTime", 0)
                await this.$store.state.audio.pause();

                this.$store.state.audio.play()
                // 重置播放图标


                // 保存音乐id
                this.musicid = this.$store.state.musicList[index].id;
                this.$store.commit("saveMusicId", this.musicid)
                // 改变对应的音乐卡片按钮的svg播放图标
                this.recommendations[currentIndex].playing = false;
                // 更换进度条小图标
                this.$store.commit("saveMusicIcon", this.$store.state.musicList[index].icon_url)
                this.updatePlayStyle(true);
            } else if (this.$store.state.musicList == this.recommendations) {
                this.$notify({
                    title: '歌单已没有下一首歌曲',
                    message: '歌单已没有下一首歌曲',
                    type: 'warning'
                });
            }
        });
    },
    // 在页面离开时
    beforeDestroy() {
        if (this.$store.state.musicList == this.recommendations) {
            console.log("当前歌单为推荐歌单");
        } else {
            // 如果当前歌单不是收藏歌单
            // 卸载音频结束监听器
            this.audio.removeEventListener('ended', this.handleAudioEnd);
        }
    }
};  
</script>

<style scoped>
.recommendations {
    display: flex;
    flex-direction: column;
    padding: 40px;
    box-sizing: border-box;
    overflow-y: auto;
    align-items: flex-start;
    /* 如果内容过多，允许滚动 */
    width: calc(100% - 10%);
    /* 在大屏幕下占据90%的宽度，与侧边栏对齐 */
    margin-left: 10%;
    /* 在大屏幕下与侧边栏对齐 */

}

.recommendations-header {
    font-size: 24px;
    margin-top: 80px;
}


.recommendation-info {
    display: flex;
    flex-direction: column;
}

.recommendation-title {
    font-size: 18px;
    margin-bottom: 5px;
}

.recommendation-artist {
    font-size: 14px;
    color: #666;
}

.recommendations-body {
    display: flex;
    flex-wrap: wrap;
    margin-top: 20px;
    align-items: flex-start;
    /* 添加此行 */
    margin: 20px 0;
}

.recommendation-card {

    padding: 20px;
    /* border-bottom: 1px solid #eee; */
}

.recommendation-cover {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 280px;
    height: 200px;
    margin-right: 20px;
    overflow: hidden;
}

.recommendation-cover img {
    width: 100%;
    height: 80%;
}

.recommendation-card:hover {
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transform: translateY(-2px);
    transition: all 0.2s ease-out;
}



/* 音乐卡片播放按钮样式 */
.play-button {
    position: relative;
    bottom: 40px;
    right: -80px;
    padding: 5px;
    background-color: transparent;
    /* 使背景透明 */
    border: none;
    cursor: pointer;
    transition: transform 0.3s;
}

.play-button svg {
    width: 54px;
    height: 54px;
    fill: #1db954;
    /* 图标颜色 */
    transition: fill 0.3s;
}

.play-button:hover {
    transform: scale(1.1);
    /* 悬停时放大图标 */
}

.play-button:hover svg {
    fill: #1ed760;
    /* 悬停时图标颜色变化 */
}

/* 按钮切换动画 */
.fade-enter-active,
.fade-leave-active {
    transition: all 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
    opacity: 0;
}

/* 响应式调整 */
@media screen and (max-width: 768px) {

    .recommendations-header {
        font-size: 24px;
        margin-top: 20px;
    }


    .recommendation-artist {
        font-size: 12px;
        /* 减小字体大小 */
    }

    .recommendations-body {
        margin-top: 0;
        /* 清除顶部间距 */
    }


    .play-button {
        position: relative;
        bottom: 40px;
        right: -60px;
        padding: 5px;
        background-color: transparent;
        /* 使背景透明 */
        border: none;
        cursor: pointer;
        z-index: 1;
        transition: transform 0.3s;
    }

    .play-button svg {
        width: 40px;
        height: 40px;
        fill: #1db954;
        /* 图标颜色 */
        transition: fill 0.3s;
    }
}
</style>
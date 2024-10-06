<template>
    <div class="music-collect">
        <h1>我的音乐收藏</h1>
        <ul class="music-list">
            <li v-for="(song, index) in songs" :key="song.id" class="music-item">
                <div class="music-infos">
                    <div class="small-icons">
                        <img :src="song.icon_url">
                    </div>
                    <span class="music-name">{{ song.title }}</span>
                    <span class="music-artist">— {{ song.music_artist }}</span>
                </div>
                <button @click="playSong(song.id)" class="play-buttons">
                    <div v-if="!song.playing">
                        <svg t="1725370962119" class="p-iconsss" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="3529" width="60" height="60">
                            <path
                                d="M512 58.632258c140.890839 1.263484 261.144774 36.612129 338.299871 115.072C928.755613 250.851097 964.104258 371.109161 965.367742 512c-1.263484 140.890839-36.612129 261.144774-115.067871 338.299871-77.155097 78.455742-197.409032 113.804387-338.299871 115.067871-140.890839-1.263484-261.148903-36.612129-338.295742-115.067871C95.244387 773.144774 59.895742 652.890839 58.632258 512c1.263484-140.890839 36.612129-261.148903 115.072-338.295742C250.851097 95.244387 371.109161 59.895742 512 58.632258z"
                                fill="#6EDBA2" p-id="3530"></path>
                            <path
                                d="M385.515355 364.791742c6.561032-44.531613 38.660129-71.89471 73.377032-49.589677 61.419355 39.064774 133.326452 97.094194 205.563871 147.563354 42.244129 29.588645 42.244129 68.884645 0 98.465033-72.237419 50.47329-144.144516 108.498581-205.563871 147.559225-34.716903 22.305032-66.816-5.062194-73.377032-49.589677a1204.327226 1204.327226 0 0 1 0-294.408258z"
                                fill="#FFFFFF" opacity=".8" p-id="3531"></path>
                        </svg>
                    </div>

                    <!--暂停播放按钮-->
                    <div v-else>
                        <svg t="1726187728687" class="p-iconss" viewBox="0 0 1024 1024" version="1.1"
                            xmlns="http://www.w3.org/2000/svg" p-id="7795" width="60" height="60">
                            <path d="M512 483.4m-380 0a380 380 0 1 0 760 0 380 380 0 1 0-760 0Z" fill="#31BC69"
                                p-id="7796">
                            </path>
                            <path
                                d="M595.5 588.4h-167c-11.8 0-21.5-9.7-21.5-21.5v-167c0-11.8 9.7-21.5 21.5-21.5h167c11.8 0 21.5 9.7 21.5 21.5v167c0 11.8-9.7 21.5-21.5 21.5z"
                                fill="#FFFFFF" p-id="7797"></path>
                        </svg>
                    </div>
                </button>
            </li>
        </ul>

    </div>
</template>

<script>
import collectapi from '@/api/collect'
import eventBus from '@/utils/eventBus';
export default {
    data() {
        return {
            songs: [],
            audio: new Audio(),
            isLoding: false,
        }
    },
    methods: {
        // 发送请求，获取用户收藏数据
        async handleGetCollectList() {
            const savedUserInfo = sessionStorage.getItem('userInfo');
            if (savedUserInfo) {
                // 从session里取出用户uid
                const uid = JSON.parse(savedUserInfo).uid;
                // console.log(uid)
                await collectapi.get_collect(uid).then(response => {
                    this.songs = response.data.data;
                    // console.log(response);
                    // 为每个song添加一个playing字段默认为false
                    // 使用 map 函数遍历数组并返回新的数组
                    this.songs = this.songs.map(song => ({
                        ...song, // 复制原有对象
                        playing: false // 添加新的字段
                    }));
                    this.initPlay();
                    this.isLoding = true;
                }).catch(error => {
                    console.error(error);
                });
            } else {
                this.$notify({
                    title: '请先登录',
                    message: '请先登录',
                    type: 'warning'
                });
            }
        },
        playSong(id) {
            // 这里可以调用播放歌曲的方法或者API
            // 找到对应的音乐卡片
            const index = this.songs.findIndex(item => item.id === id);
            // console.log(index)
            // 如果音乐id不是之前保存的或者音乐id为null
            if (this.$store.state.musicid !== id || this.$store.state.musicid == null) {
                this.$store.commit("saveMusicIcon", this.songs[index].icon_url)
                this.audio.src = this.songs[index].url;
                this.$store.commit("saveAudio", this.audio)
                this.$store.commit("saveMusicId", id)

                this.songs[index].playing = false;
                // 设置播放时长
                this.duration = this.formatTime(this.audio.duration)
                // 把收藏表单存放到vuex的当前表单
                this.$store.commit("saveMusicList", this.songs);
            }
            // 改变对应的音乐卡片按钮的svg播放图标
            this.songs[index].playing = !this.songs[index].playing;

            if (this.songs[index].playing) {
                // 开始播放音频
                // 把播放的音乐保存到vuex
                this.$store.commit("saveMusicInfo", this.songs[index])
                this.$store.commit("saveMusicIcon", this.songs[index].icon_url)
                this.$store.commit("saveMusicId", id)
                this.$store.commit("savePlayStatus", true);
                this.$store.state.audio.play()
            } else {
                // 暂停播放
                this.$store.state.audio.pause()
            }
            // 改变进度条播放按钮样式
            this.$store.commit("savePlayStatus", this.songs[index].playing);
        },
        // 把播放时间转换为00:00的结构
        formatTime(seconds) {
            const minutes = Math.floor(seconds / 60);
            const secondsLeft = Math.floor(seconds % 60);
            return `${String(minutes).padStart(2, '0')}:${String(secondsLeft).padStart(2, '0')}`;
        },
        updatePlayStyle(newVal) {
            // 修改对应的播放按钮样式
            const index = this.songs.findIndex(item => item.title === this.$store.state.music.title);
            // 改变对应的音乐卡片按钮的svg播放图标
            this.songs[index].playing = newVal;
        },
        // 从vuex里读取当前播放音乐的id，以便重新进入这个页面的时候改变对应的播放按钮样式
        initPlay() {
            if (this.$store.state.musicid && this.$store.state.playstatus) {
                // 修改对应的播放按钮样式
                const index = this.songs.findIndex(item => item.title === this.$store.state.music.title);
                // 改变对应的音乐卡片按钮的svg播放图标
                this.songs[index].playing = true;

            }
        },
        handleAudioEnd(event) {
            console.log('Audio has ended.');
            // 执行你想在移除音频结束监听器后做的操作
        }
    },
    created() {
        this.audio = this.$store.state.audio;
        this.handleGetCollectList();
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
            this.songs[target].playing = false;
        })

        //  接收到进度条组件的上一首请求，修改卡片播放按钮样式
        eventBus.$on('changeUpIndex', target => {
            this.updatePlayStyle(true);
            this.songs[target].playing = false;
        })

        // 监听音频播放状态变化
        this.audio.addEventListener('timeupdate', () => {
            this.currentTime = this.formatTime(this.audio.currentTime);
            // 保存到vux
            this.$store.commit("saveCurrentTime", this.$store.state.audio.currentTime)
            // console.log("当前音乐播放时间:", this.currentTime)
        });
        // 监听音频播放结束
        this.audio.addEventListener('ended', async () => {
            // console.log('音频播放结束');
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
                this.songs[currentIndex].playing = false;
                // 更换进度条小图标
                this.$store.commit("saveMusicIcon", this.$store.state.musicList[index].icon_url)
                this.updatePlayStyle(true);
            } else if (this.$store.state.musicList == this.songs){
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
        if (this.$store.state.musicList == this.songs) {
            console.log("当前歌单为收藏歌单");
        } else {
            // 如果当前歌单不是收藏歌单
            // 卸载音频结束监听器
            this.audio.removeEventListener('ended',this.handleAudioEnd);
        }
    }
}
</script>

<style scoped>
.music-collect {

    display: flex;
    flex-direction: column;
    overflow-y: auto;
    width: calc(100% - 10%);
    margin-left: 10%;

}

.music-list {
    list-style: none;
    padding: 0;
    margin: 0;
}

.music-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 15px 0;
    border-bottom: 1px solid #e0e0e0;
    transition: background-color 0.3s ease-in-out;
}

.music-item:hover {
    background-color: #f0f0f0;
}

.music-infos {
    display: flex;
    align-items: center;
}

.music-name {
    font-size: 18px;
    font-weight: bold;
    padding-left: 60px;
}

.music-artist {
    color: #6c757d;
    font-size: 14px;
    padding-left: 60px;
}

.play-buttons {
    position: relative;
    background-color: transparent;
    border: none;
    right: 80px;
    transition: transform 0.3s;
}

.play-buttons svg {
    width: 54px;
    height: 54px;
    fill: #1db954;
    /* 图标颜色 */
    transition: fill 0.3s;
}

.play-buttons:hover {
    transform: scale(1.2);
}

.play-buttons:focus {
    color: #45a049;
}



.small-icons {
    display: inline-block;
    /* 使容器成为内联块级元素，方便控制大小 */
    text-align: center;
    /* 文本居中对齐 */
    width: auto;
    /* 设置容器的宽度 */
    height: 60px;
    /* 设置容器的高度 */
    overflow: hidden;
    /* 超出部分隐藏 */
    /* 圆形显示 */
    background-color: #f0f0f0;
    /* 背景颜色，可选 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    /* 添加阴影效果 */
}

/* 歌曲封面图片 */
.small-icons img {
    display: block;
    /* 使图片成为块级元素 */
    width: 100%;
    /* 图片宽度占满容器 */
    height: 100%;
    /* 自动调整高度，保持原始宽高比 */
    object-fit: cover;
    /* 保持原始宽高比的同时覆盖整个容器 */
}
</style>
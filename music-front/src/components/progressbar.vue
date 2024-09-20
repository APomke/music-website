<template>
    <!--音乐播放进度-->
    <div class="progressbar-container">
        <!--歌曲封面小图标-->
        <div class="small-icon">
            <img :src="this.$store.state.musicicon">
        </div>
        <div class="time-display" v-if="this.$store.state.audio.currentTime">
            <!--当前播放时间-->
            <span class="current-time">{{ formatTime(this.$store.state.currentTime) }}</span>
        </div>
        <div class="time-display" v-if="!this.$store.state.audio.currentTime">
            <!--当前播放时间-->
            <span class="current-time">00:00</span>
        </div>
        <div class="back" @click="backbutton">
            <transition>
                <svg t="1726792478717" class="icon" viewBox="0 0 1024 1024" version="1.1"
                    xmlns="http://www.w3.org/2000/svg" p-id="7302" width="35" height="35">
                    <path
                        d="M172.032 69.632c19.968 0 35.84 15.872 35.84 35.84v802.304c0 19.968-15.872 35.84-35.84 35.84s-35.84-15.872-35.84-35.84V105.472c0-19.968 16.384-35.84 35.84-35.84z"
                        fill="#65D5A5" p-id="7303"></path>
                    <path
                        d="M225.28 571.904l2.048 2.048 527.36 378.368 3.584 2.048c41.984 22.528 69.632 16.384 84.992 6.656 23.04-13.824 34.816-41.472 34.816-82.944V135.168c0-52.736-25.6-74.752-40.96-82.944-10.752-5.632-22.016-8.704-34.304-8.704-15.872 0-32.256 5.12-47.104 14.848L229.376 436.224l-4.096 3.584c-35.84 36.352-35.84 95.744 0 132.096z"
                        fill="#65D5A5" p-id="7304"></path>
                </svg>
            </transition>
        </div>
        <!-- 音乐播放进度 -->
        <div class="play-btn" @click="playbutton">
            <!--开始播放按钮-->
            <transition name="fade">
                <div v-if="!this.$store.state.playstatus">
                    <svg t="1725370962119" class="p-icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="3529" width="60" height="60">
                        <path
                            d="M512 58.632258c140.890839 1.263484 261.144774 36.612129 338.299871 115.072C928.755613 250.851097 964.104258 371.109161 965.367742 512c-1.263484 140.890839-36.612129 261.144774-115.067871 338.299871-77.155097 78.455742-197.409032 113.804387-338.299871 115.067871-140.890839-1.263484-261.148903-36.612129-338.295742-115.067871C95.244387 773.144774 59.895742 652.890839 58.632258 512c1.263484-140.890839 36.612129-261.148903 115.072-338.295742C250.851097 95.244387 371.109161 59.895742 512 58.632258z"
                            fill="#6EDBA2" p-id="3530"></path>
                        <path
                            d="M385.515355 364.791742c6.561032-44.531613 38.660129-71.89471 73.377032-49.589677 61.419355 39.064774 133.326452 97.094194 205.563871 147.563354 42.244129 29.588645 42.244129 68.884645 0 98.465033-72.237419 50.47329-144.144516 108.498581-205.563871 147.559225-34.716903 22.305032-66.816-5.062194-73.377032-49.589677a1204.327226 1204.327226 0 0 1 0-294.408258z"
                            fill="#FFFFFF" opacity=".8" p-id="3531"></path>
                    </svg>
                </div>
            </transition>
            <!--暂停播放按钮-->
            <transition name="fade">
                <div v-if="this.$store.state.playstatus">
                    <svg t="1726187728687" class="p-icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="7795" width="60" height="60">
                        <path d="M512 483.4m-380 0a380 380 0 1 0 760 0 380 380 0 1 0-760 0Z" fill="#31BC69" p-id="7796">
                        </path>
                        <path
                            d="M595.5 588.4h-167c-11.8 0-21.5-9.7-21.5-21.5v-167c0-11.8 9.7-21.5 21.5-21.5h167c11.8 0 21.5 9.7 21.5 21.5v167c0 11.8-9.7 21.5-21.5 21.5z"
                            fill="#FFFFFF" p-id="7797"></path>
                    </svg>
                </div>
            </transition>
        </div>
        <!--下一首按钮-->
        <div class="next" @click="nextbutton">
            <transition>
                <svg t="1726791438733" class="icon" viewBox="0 0 1024 1024" version="1.1"
                    xmlns="http://www.w3.org/2000/svg" p-id="5234" width="35" height="35">
                    <path
                        d="M852.992 956.928c-20.48 0-36.864-16.384-36.864-36.864V103.424c0-20.48 16.384-36.864 36.864-36.864s36.864 16.384 36.864 36.864v816.64c0 20.48-16.384 36.864-36.864 36.864z"
                        fill="#65D5A5" p-id="5235"></path>
                    <path
                        d="M799.232 445.44l-2.048-2.048L260.608 58.368l-3.584-2.048c-43.008-23.04-70.656-16.384-86.528-6.656-23.552 14.336-35.84 42.496-35.84 84.48V890.88c0 53.76 26.112 75.776 41.472 84.48 10.752 5.632 22.528 8.704 34.816 8.704 15.872 0 32.768-5.12 48.128-14.848L795.136 583.68l4.096-3.584c36.352-37.376 36.352-97.792 0-134.656z"
                        fill="#65D5A5" p-id="5236"></path>
                </svg>
            </transition>
        </div>
        <div class="time-display" v-if="this.$store.state.audio.duration">
            <!--音乐总时长-->
            <span class="total-time">{{ formatTime(this.$store.state.audio.duration) }}</span>
        </div>
        <div class="time-display" v-if="!this.$store.state.audio.duration">
            <!--音乐总时长-->
            <span class="total-time">00:00</span>
        </div>
        <!--进度条-->
        <div class="block">
            <el-slider v-model="$store.state.currentTime" :show-tooltip="false" class="jindu"
                :max="$store.state.audio.duration" @change="updateCurrentTime"></el-slider>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            // playstatus: false,
            value1: 0,
            currentTime: 0,
            duration: 0

        }
    },
    // 组件的逻辑部分  
    methods: {
        playbutton() {
            // 必须要读取到vuex里的音乐信息才能点击播放按钮
            console.log(this.$store.state.music)
            if (this.$store.state.music) {
                this.$store.commit("savePlayStatus", this.$store.state.playstatus == true ? false : true);
                // this.$store.state.playstatus = this.playstatus == true ? false : true
                if (this.$store.state.playstatus) {
                    console.log("播放")
                    // console.log(this.$store.state.music.id)
                    // 修改音乐信息
                    this.$store.commit("updateMusicState", true)
                } else {
                    console.log("暂停")
                    this.$store.commit("updateMusicState", false)
                }
                console.log(this.$store.state.playstatus)
            }

        },
        // 把播放时间转换为00:00的结构
        formatTime(seconds) {
            const minutes = Math.floor(seconds / 60);
            const secondsLeft = Math.floor(seconds % 60);
            return `${String(minutes).padStart(2, '0')}:${String(secondsLeft).padStart(2, '0')}`;
        },
        updateCurrentTime(val) {
            console.log(val)
            // 修改音乐播放进度
            // this.$store.state.currentTime = val;
            this.$store.commit("saveUpdateCurrentTime", val)
        },
        backbutton() {
            console.log("点击了上一首按钮")
        },
        nextbutton() {
            console.log("点击了下一首按钮")
            console.log(this.$store.state.musicicon)
        }
    }
}  
</script>

<style>
.progressbar-container {
    display: flex;
    padding: 20px;
    box-sizing: border-box;
    position: fixed;
    bottom: 0;
    width: 100%;
    background-color: #fff;
    box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
    animation: slideInUp 0.5s ease-out forwards;
    /* 添加动画 */
    justify-content: center;
    /* 使内容（包括播放按钮）水平居中 */
    align-items: center;
    /* 使内容（包括播放按钮）垂直居中，如果只需要按钮居中，可以将此属性放在 .play-btn 上 */
}

/* 歌曲封面小图标容器 */
.small-icon {
    display: inline-block;
    /* 使容器成为内联块级元素，方便控制大小 */
    text-align: center;
    /* 文本居中对齐 */
    width: 60px;
    /* 设置容器的宽度 */
    height: 60px;
    /* 设置容器的高度 */
    overflow: hidden;
    /* 超出部分隐藏 */
    border-radius: 50%;
    /* 圆形显示 */
    background-color: #f0f0f0;
    /* 背景颜色，可选 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    /* 添加阴影效果 */
}

/* 歌曲封面图片 */
.small-icon img {
    display: block;
    /* 使图片成为块级元素 */
    width: 100%;
    /* 图片宽度占满容器 */
    height: 100%;
    /* 自动调整高度，保持原始宽高比 */
    object-fit: cover;
    /* 保持原始宽高比的同时覆盖整个容器 */
}

.play-btn {
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    width: 60px;
    /* 确保宽度一致 */
    height: 60px;
    /* 确保高度一致 */
    margin: 0 auto;
}

/*下一首按钮*/
.next {
    display: flex;
    justify-self: center;
    align-items: center;
    margin-right: 30%;
}

/*上一首按钮*/
.back {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 30%;
}

.play-icon,
.pause-icon {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
}

.p-icon {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}


/* 定义入场动画 */
@keyframes slideInUp {
    from {
        transform: translateY(100%);
        opacity: 0;
    }

    to {
        transform: translateY(0);
        opacity: 1;
    }
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

/*定义进度条样式*/
.block {
    position: absolute;
    width: 80%;
    bottom: -5px;
    color: chartreuse;
    margin: 0 10px;
    /* 添加一些左右间距 */
}

/* 时间显示 */
.time-display {
    display: flex;
    gap: 10px;
    /* 添加一些间距 */
    align-items: center;
    font-size: 14px;
    color: #666;
}

.time-display .current-time {
    order: 1;
    /* 确保当前时间在左边 */
    margin-left: 95px;
    /* 左侧间距 */
}

.time-display .total-time {
    order: 3;
    /* 确保总时间在右边 */
    margin-right: 150px;
    /* 右侧间距 */
}

.time-display span {
    font-family: monospace;
    font-size: 14px;
    padding: 5px;
    /*border: 1px solid #ddd;*/
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.time-display span:hover {
    background-color: #e0e0e0;
}


.jindu {
    .el-slider__button {
        width: 10px;
        height: 10px;
        border: 2px solid #000000;
        background-color: #000000;
        border-radius: 50%;
        transition: .2s;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
    }

    .el-slider__runway {
        width: 100%;
        height: 3px;
        margin: 16px 0;
        background-color: #E4E7ED;
        border-radius: 3px;
        position: relative;
        cursor: pointer;
        vertical-align: middle;
    }

    .el-slider__bar {
        height: 4px;
        background-color: #000000;
        border-top-left-radius: 3px;
        border-bottom-left-radius: 3px;
        position: absolute;
    }
}

/* 响应式调整 */
@media screen and (max-width: 768px) {
    .time-display .current-time {
        order: 1;
        /* 确保当前时间在左边 */
        margin-left: 20px;
        /* 左侧间距 */
    }

    .time-display .total-time {
        order: 3;
        /* 确保总时间在右边 */
        margin-right: 20px;
        /* 右侧间距 */
    }

    /*下一首按钮*/
    .next {
        display: flex;
        justify-self: center;
        align-items: center;
        gap: 10px;
        margin-right: 20px;
    }

    /*上一首按钮*/
    .back {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 10px;
        margin-left: 20px;
    }

    /* 歌曲封面小图标容器 */
    .small-icon {
        display: inline-block;
        /* 使容器成为内联块级元素，方便控制大小 */
        text-align: center;
        /* 文本居中对齐 */
        width: 0px;
        /* 设置容器的宽度 */
        height: 0px;
        /* 设置容器的高度 */
        overflow: hidden;
        /* 超出部分隐藏 */
        border-radius: 50%;
        /* 圆形显示 */
        background-color: #f0f0f0;
        /* 背景颜色，可选 */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        /* 添加阴影效果 */
    }

    /* 歌曲封面图片 */
    .small-icon img {
        display: block;
        /* 使图片成为块级元素 */
        width: 100%;
        /* 图片宽度占满容器 */
        height: 100%;
        /* 自动调整高度，保持原始宽高比 */
        object-fit: cover;
        /* 保持原始宽高比的同时覆盖整个容器 */
    }
}
</style>
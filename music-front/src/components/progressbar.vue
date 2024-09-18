<template>
    <!--音乐播放进度-->
    <div class="progressbar-container">
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
        <!--进度条-->
        <div class="block">
            <el-slider v-model="value1" :show-tooltip="false" class="jindu"></el-slider>
        </div>
    </div>
</template>

<script>
import recommendations from './recommendations.vue';
export default {
    data() {
        return {
            // playstatus: false,
            value1: 0,
        }
    },
    // 组件的逻辑部分  
    methods: {
        playbutton() {
            // 必须要读取到vuex里的音乐信息才能点击播放按钮
            if (this.$store.state.music) {
                this.$store.commit("savePlayStatus", this.$store.state.playstatus == true ? false : true);
                // this.$store.state.playstatus = this.playstatus == true ? false : true
                if (this.$store.state.playstatus) {
                    console.log("播放")
                    // console.log(this.$store.state.music.id)
                    // 修改音乐信息
                    this.$store.commit("updateMusicState",true)
                } else {
                    console.log("暂停")
                    this.$store.commit("updateMusicState",false)
                }
                console.log(this.$store.state.playstatus)
            }

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
</style>
<template>
    <div class="recommendations">
        <div class="recommendations-header">
            推荐音乐
        </div>
        <div class="recommendations-body">
            <div class="recommendation-card" v-for="(recommendation, index) in recommendations" :key="`card-${index}`"
                @click="handleCardClick(index)">
                <div class="recommendation-cover">
                    <img src="../image/daox.png" alt="">
                </div>
                <div class="recommendation-info">
                    <div class="recommendation-title">{{ recommendation.title }}</div>
                    <div class="recommendation-artist">{{ recommendation.artist }}</div>
                </div>
                <button class="play-button" @click.stop="handlePlayClick(index)">
                    <svg t="1725370962119" class="p-icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="3529" width="60" height="60">
                        <path
                            d="M512 58.632258c140.890839 1.263484 261.144774 36.612129 338.299871 115.072C928.755613 250.851097 964.104258 371.109161 965.367742 512c-1.263484 140.890839-36.612129 261.144774-115.067871 338.299871-77.155097 78.455742-197.409032 113.804387-338.299871 115.067871-140.890839-1.263484-261.148903-36.612129-338.295742-115.067871C95.244387 773.144774 59.895742 652.890839 58.632258 512c1.263484-140.890839 36.612129-261.148903 115.072-338.295742C250.851097 95.244387 371.109161 59.895742 512 58.632258z"
                            fill="#6EDBA2" p-id="3530"></path>
                        <path
                            d="M385.515355 364.791742c6.561032-44.531613 38.660129-71.89471 73.377032-49.589677 61.419355 39.064774 133.326452 97.094194 205.563871 147.563354 42.244129 29.588645 42.244129 68.884645 0 98.465033-72.237419 50.47329-144.144516 108.498581-205.563871 147.559225-34.716903 22.305032-66.816-5.062194-73.377032-49.589677a1204.327226 1204.327226 0 0 1 0-294.408258z"
                            fill="#FFFFFF" opacity=".8" p-id="3531"></path>
                    </svg>
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import api from '@/api/music'
export default {
    data() {
        return {
            recommendations: [
                { title: '音乐标题1', artist: '艺术家1' },
                { title: '音乐标题2', artist: '艺术家2' },
                { title: '音乐标题1', artist: '艺术家1' },
                { title: '音乐标题2', artist: '艺术家2' },
                { title: '音乐标题1', artist: '艺术家1' },
                { title: '音乐标题2', artist: '艺术家2' },
                { title: '音乐标题1', artist: '艺术家1' },
                { title: '音乐标题2', artist: '艺术家2' },
                { title: '音乐标题1', artist: '艺术家1' },
                { title: '音乐标题2', artist: '艺术家2' }
                // 添加更多推荐音乐  
            ],
        };
    },
    methods: {
        handleCardClick(index) {
            console.log(index);
        },
        getRecommedations() {
            api.getRecommendationsMusic().then(response => {
                this.recommendations = response.data
            }).catch(error => {
                console.error(error);
            });

        }
    },
    created() {
        this.getRecommedations()
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
    height: auto;
}

.recommendation-card:hover {
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transform: translateY(-2px);
    transition: all 0.2s ease-out;
}



/* 音乐卡片播放按钮样式 */
.play-button {
    position: absolute;
    bottom: 40px;
    right: 40px;
    padding: 5px;
    background-color: transparent; /* 使背景透明 */
    border: none;
    cursor: pointer;
    z-index: 1;
    transition: transform 0.3s;
}

.play-button svg {
    width: 54px;
    height: 54px;
    fill: #1db954; /* 图标颜色 */
    transition: fill 0.3s;
}

.play-button:hover {
    transform: scale(1.1); /* 悬停时放大图标 */
}

.play-button:hover svg {
    fill: #1ed760; /* 悬停时图标颜色变化 */
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
        position: absolute;
        bottom: 40px;
        right: 60px;
        padding: 5px;
        background-color: transparent; /* 使背景透明 */
        border: none;
        cursor: pointer;
        z-index: 1;
        transition: transform 0.3s;
    }

    .play-button svg {
        width: 40px;
        height: 40px;
        fill: #1db954; /* 图标颜色 */
        transition: fill 0.3s;
    }
}

</style>
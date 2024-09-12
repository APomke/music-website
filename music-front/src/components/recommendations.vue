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
            </div>
        </div>
    </div>
</template>

<script>
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
    width: calc(100% - 10%); /* 在大屏幕下占据90%的宽度，与侧边栏对齐 */
    margin-left: 10%; /* 在大屏幕下与侧边栏对齐 */

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

/* 响应式调整 */
@media screen and (max-width: 768px) {

    .recommendations-header {
        font-size: 24px;
        margin-top: 20px;
    }


    .recommendation-artist {
        font-size: 12px; /* 减小字体大小 */
    }

    .recommendations-body {
        margin-top: 0; /* 清除顶部间距 */
    }
}
</style>
<template>
    <div class="music-collect">
        <h1>我的音乐收藏</h1>
        <ul class="music-list">
            <li v-for="(song, index) in songs" :key="index" class="music-item">
                <div class="music-infos">
                    <div class="small-icons">
                        <img :src="song.iconurl">
                    </div>
                    <span class="music-name">{{ song.title }}</span>
                    <span class="music-artist">— {{ song.artist }}</span>
                </div>
                <button @click="playSong(index)" class="play-buttons">
                    <svg t="1725370962119" class="p-icons" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="3529" width="60" height="60">
                        <path
                            d="M512 58.632258c140.890839 1.263484 261.144774 36.612129 338.299871 115.072C928.755613 250.851097 964.104258 371.109161 965.367742 512c-1.263484 140.890839-36.612129 261.144774-115.067871 338.299871-77.155097 78.455742-197.409032 113.804387-338.299871 115.067871-140.890839-1.263484-261.148903-36.612129-338.295742-115.067871C95.244387 773.144774 59.895742 652.890839 58.632258 512c1.263484-140.890839 36.612129-261.148903 115.072-338.295742C250.851097 95.244387 371.109161 59.895742 512 58.632258z"
                            fill="#6EDBA2" p-id="3530"></path>
                        <path
                            d="M385.515355 364.791742c6.561032-44.531613 38.660129-71.89471 73.377032-49.589677 61.419355 39.064774 133.326452 97.094194 205.563871 147.563354 42.244129 29.588645 42.244129 68.884645 0 98.465033-72.237419 50.47329-144.144516 108.498581-205.563871 147.559225-34.716903 22.305032-66.816-5.062194-73.377032-49.589677a1204.327226 1204.327226 0 0 1 0-294.408258z"
                            fill="#FFFFFF" opacity=".8" p-id="3531"></path>
                    </svg>
                </button>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    data() {
        return {
            songs: [
                { id: 1, title: '音乐标题1', artist: '艺术家1', url: '../music/11.稻香.wav', iconurl: "https://awsimage-1.oss-cn-hangzhou.aliyuncs.com/image-20240920091824796.png", isPlaying: false },
                { id: 2, title: '音乐标题2', artist: '艺术家2', isPlaying: false },
                // 添加更多歌曲...
            ]
        }
    },
    methods: {
        playSong(index) {
            console.log(`Playing song at index ${index}`);
            // 这里可以调用播放歌曲的方法或者API
        }
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

.music-collect {
    font-family: 'Roboto', sans-serif;
    display: flex;
    flex-direction: column;
    overflow-y: auto;
    /*align-items: flex-start;*/
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
    background-color: transparent;
    border: none;
    cursor: pointer;
    outline: none;
    padding: 0;
    color: #4CAF50;
    font-size: 20px;
    transition: transform 0.3s ease-in-out;
    padding-right: 150px;
}

.play-button:hover {
    transform: scale(1.2);
}

.play-button:focus {
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
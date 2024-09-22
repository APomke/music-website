<template>
    <div class="music-collect">
        <h1>我的音乐收藏</h1>
        <ul class="music-list">
            <li v-for="(song, index) in songs" :key="index" class="music-item">
                <div class="music-info">
                    <span class="music-name">{{ song.name }}</span>
                    <span class="music-artist">— {{ song.artist }}</span>
                    <span class="music-artist">— {{ song.artist }}</span>
                    <span class="music-artist">— {{ song.artist }}</span>
                    <span class="music-artist">— {{ song.artist }}</span>
                    <span class="music-artist">— {{ song.artist }}</span>
                </div>
                <button @click="playSong(index)" class="play-button">
                    <i class="fas fa-play"></i>
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
                { name: '歌名1', artist: '艺术家1' },
                { name: '歌名2', artist: '艺术家2' },
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
    align-items: flex-start;
    width: calc(100% - 10%);
    margin-left: 10%;
    max-width: 800px;
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

.music-info {
    display: flex;
    align-items: center;
}

.music-name {
    font-size: 18px;
    font-weight: bold;
}

.music-artist {
    color: #6c757d;
    font-size: 14px;
    margin-left: 5px;
}

.play-button {
    background-color: transparent;
    border: none;
    cursor: pointer;
    outline: none;
    padding: 0;
    color: #4CAF50;
    font-size: 20px;
    transition: transform 0.3s ease-in-out;
}

.play-button:hover {
    transform: scale(1.2);
}

.play-button:focus {
    color: #45a049;
}
</style>
<template>
    <div class="sidebar">
        <div class="sidebar-header">
            <div class="sidebar-ico">
                <transition name="icon">
                    <svg t="1725364993851" class="s-icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="3233" width="100%" height="100%">
                        <path
                            d="M626.2784 818.9952c0 113.21344-117.26848 205.0048-261.9392 205.0048C219.66848 1024 102.4 932.2496 102.4 818.9952c0-113.21344 117.26848-205.0048 261.9392-205.0048 48.7424 0 94.37184 10.40384 133.44768 28.54912V123.6992L880.64 40.96v195.87072L625.41824 291.2256v510.5664c0.57344 5.65248 0.90112 11.38688 0.90112 17.2032z"
                            fill="#FFBB2D" p-id="3234"></path>
                    </svg>
                </transition>
                <div class="index-title">音乐</div>
            </div>
        </div>
        <ul class="sidebar-options">
            <li @click="selectOption('推荐')">推荐</li>
            <li @click="selectOption('播放')">播放</li>
            <li @click="selectOption('收藏')">收藏</li>
            <li @click="selectOption('上传')">上传</li>
        </ul>
    </div>
</template>

<script>
export default {
    methods: {
        selectOption(option) {
            // console.log(`Selected option: ${option}`);
            if (option == "播放") {
                // console.log("点击了播放页面")
                var tableroute = [
                    { isIndex: false },
                    { isMusicInfo: true },
                    { isCcollect: false},
                    {isUpload: false}
                ]
                this.$store.commit("saveTableRoute", tableroute)
            } else if(option == "推荐") {
                var tableroute = [
                    { isIndex: true },
                    { isMusicInfo: false },
                    { isCcollect: false},
                    {isUpload: false}
                ]
                this.$store.commit("saveTableRoute", tableroute)
            } else if(option == "收藏"){
                // console.log("点击了收藏页面")
                var tableroute = [
                    { isIndex: false },
                    { isMusicInfo: false },
                    { isCcollect: true},
                    {isUpload: false}
                ]
                this.$store.commit("saveTableRoute", tableroute)
            }else if(option == "上传"){
                // console.log("点击了上传页面")
                var tableroute = [
                    { isIndex: false },
                    { isMusicInfo: false },
                    { isCcollect: false},
                    {isUpload: true}
                ]
                this.$store.commit("saveTableRoute", tableroute)
            }
            
            // 这里可以添加更多的逻辑，比如根据选项进行路由跳转或显示不同的内容等  
        }
    }
}
</script>

<style scoped>
.sidebar {
    /* 使用 flex 布局，让 svg 元素自适应高度 */
    display: flex;
    flex-direction: column;
    width: 10%;
    background-color: white;
    border-right: 1px solid #ddd;
    height: 100vh;
    box-sizing: border-box;
    position: fixed;
    top: 0;
    left: 0;
    padding: 20px;
    /* 保持一致的内边距 */
    transition: width 0.3s ease;
    /* 平滑过渡 */
}

.sidebar-header {
    font-size: 35px;
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    border-bottom: 1px solid #eee;
    width: 100%;

    .sidebar-ico {
        display: flex;
        align-items: center;
    }
}

.sidebar-ico svg {
    margin-right: 10px;
    /* 增加间距以提高可读性 */
}

.sidebar-options {
    list-style-type: none;
    padding: 0;
    margin: 0;
    overflow-y: auto;
    /* 如果选项过多，允许在侧边栏内滚动 */
}

.sidebar-options li {
    padding: 15px 20px;
    margin-bottom: 5px;
    color: #333;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s;
    font-size: 16px;
    /* 调整字体大小以适应小屏幕 */
}

.sidebar-options li:hover {
    background-color: #f5f5f5;
}

/* 响应式调整 */
@media screen and (max-width: 768px) {
    .sidebar {
        width: 20%;
        /* 在小屏幕上宽度为100%，占据整个宽度 */
        height: 100vh;
        /* 高度自动适应内容 */
        position: static;
        /* 移除固定定位 */
        border-right: none;
        /* 移除右边框 */
        border-bottom: 1px solid #ddd;
        /* 添加底部边框 */
        padding: 10px;
        /* 减少内边距 */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        /* 添加阴影效果 */
        position: fixed;
        top: 0;
        left: 0;
    }

    .sidebar-header {
        font-size: 24px;
        /* 减小字体大小以适应小屏幕 */
        margin-bottom: 10px;
        /* 减少底部间距 */
    }

    .sidebar-ico svg {
        width: 32px;
        /* 减小图标大小 */
        height: 32px;
        margin-right: 5px;
        /* 减少图标与文本之间的间距 */
    }

    .sidebar-options {
        padding-bottom: 10px;
        /* 为底部边框留出空间 */
    }

    .sidebar-options li {
        padding: 10px 15px;
        /* 减少内边距 */
        font-size: 14px;
        /* 减小字体大小 */
    }
}
</style>
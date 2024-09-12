<template>
    <!--音乐播放进度-->
    <div class="progressbar-container">
        <!-- 音乐播放进度 -->
        <div class="play-btn">
            <svg t="1725370962119" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
                p-id="3529" width="60" height="60">
                <path
                    d="M512 58.632258c140.890839 1.263484 261.144774 36.612129 338.299871 115.072C928.755613 250.851097 964.104258 371.109161 965.367742 512c-1.263484 140.890839-36.612129 261.144774-115.067871 338.299871-77.155097 78.455742-197.409032 113.804387-338.299871 115.067871-140.890839-1.263484-261.148903-36.612129-338.295742-115.067871C95.244387 773.144774 59.895742 652.890839 58.632258 512c1.263484-140.890839 36.612129-261.148903 115.072-338.295742C250.851097 95.244387 371.109161 59.895742 512 58.632258z"
                    fill="#6EDBA2" p-id="3530"></path>
                <path
                    d="M385.515355 364.791742c6.561032-44.531613 38.660129-71.89471 73.377032-49.589677 61.419355 39.064774 133.326452 97.094194 205.563871 147.563354 42.244129 29.588645 42.244129 68.884645 0 98.465033-72.237419 50.47329-144.144516 108.498581-205.563871 147.559225-34.716903 22.305032-66.816-5.062194-73.377032-49.589677a1204.327226 1204.327226 0 0 1 0-294.408258z"
                    fill="#FFFFFF" opacity=".8" p-id="3531"></path>
            </svg>
        </div>
    </div>
</template>

<script>
export default {
    // 组件的逻辑部分  
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
    /* 如果不需要额外的边距，可以删除或调整以下属性 */
    margin: 0 auto;
    /* 额外的水平居中，如果 .progressbar-container 已经足够，则不需要 */
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
</style>
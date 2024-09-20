<template>
    <div class="music-collect">音乐收藏</div>
</template>

<script>

</script>

<style>
.music-collect {
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

</style>
<template>
    <div class="container">
        <div class="layout-top">
            <!--上搜索音乐-->
            <search_components />
        </div>
        <div class="layout-left">
            <sidebar_components />
        </div>
        <div class="layout-right">
            <recommendations />
        </div>
        <div class="layout-under">
            <progressbar />
        </div>
    </div>
</template>

<script>
import search_components from './search_components.vue'
import sidebar_components from './sidebar_components.vue'
import recommendations from './recommendations.vue'
import progressbar from './progressbar.vue'

export default {
    components: {
        search_components,
        sidebar_components,
        recommendations,
        progressbar
    }
}
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    position: relative;

    /* 搜索组件 css */
    .layout-top {
        
    }

    /* 左侧选项卡 css */
    .layout-left {

    }

    /* 音乐推荐 css */
    .layout-right {
    }

    /* 音乐播放进度 css */
    .layout-under {
        /* position: absolute; */
        display: flex;
        /* flex-direction: column; */
        justify-content: center;
    }
}
</style>

<template>
    <div class="container">
        <div class="layout-top" v-if="this.$store.state.tableroute[0].isIndex">
            <!--上搜索音乐-->
            <search_components />
        </div>
        <div class="layout-left">
            <sidebar_components />
        </div>
        <div class="layout-right" v-if="this.$store.state.tableroute[0].isIndex">
            <recommendations />
        </div>
        <div class="layout-right" v-if="this.$store.state.tableroute[1].isMusicInfo">
            <musicinfo />
        </div>
        <div class="layout-right" v-if="this.$store.state.tableroute[2].isCcollect">
            <musiccollect />
        </div>
        <div class="layout-under">
            <progressbar />
        </div>
    </div>
</template>

<script>

import search_components from '../components/search_components.vue'
import sidebar_components from '../components/sidebar_components.vue'
import recommendations from '../components/recommendations.vue'
import progressbar from '../components/progressbar.vue'
import musicinfo from '../components/musicinfo.vue'
import musiccollect from '../components/musiccollect.vue'


export default {
    components: {
        search_components,
        sidebar_components,
        recommendations,
        progressbar,
        musicinfo,
        musiccollect
    },
    methods: {
    },
    created() {
        // console.log(this.$store.state.tableroute[0].isIndex)
    }
}

</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    position: relative;

    /* 音乐播放进度 css */
    .layout-under {
        /* position: absolute; */
        display: flex;
        /* flex-direction: column; */
        justify-content: center;
    }
}
</style>

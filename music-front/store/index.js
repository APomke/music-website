
import Vue from "vue"
import Vuex from "vuex"
 
Vue.use(Vuex);
 
export default new Vuex.Store({
    state:{
        // 播放按钮状态
        playstatus: false,
        // 正在播放的音乐信息
        music: null
    },
    mutations:{
        // 保存播放按钮状态
        savePlayStatus(state,playstatus){
            state.playstatus = playstatus
        },
        // 保存正在播放的音乐信息
        saveMusicInfo(state,music) {
            state.music = music
        },
        // 修改音乐播放状态
        updateMusicState(state,isPlaying) {
            state.music.isPlaying = isPlaying
        }
    }
})

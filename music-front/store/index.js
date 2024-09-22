
import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        // 页面切换菜单
        tableroute: [
            {isIndex: true},
            {isMusicInfo: false},
            {isCcollect: false}
        ],
        // 播放按钮状态
        playstatus: false,
        // 正在播放的音乐信息
        musicid: null,
        music: null,
        // 音乐对象
        audio: new Audio(),
        // 当前播放时间
        currentTime: 0,
        // 要修改播放进度的时间
        updateCurrentTime: 0,
        // 音乐封面小图标url
        musicicon: null
    },
    mutations: {
        // 保存进度条播放按钮状态
        savePlayStatus(state, playstatus) {
            state.playstatus = playstatus
        },
        // 保存正在播放的音乐信息
        saveMusicInfo(state, music) {
            state.music = music
        },
        // 修改音乐播放状态
        updateMusicState(state, isPlaying) {
            state.music.isPlaying = isPlaying
        },
        // 保存音乐对象
        saveAudio(state,audio) {
            state.audio = audio
        },
        // 保存当前音乐播放时间
        saveCurrentTime(state,currentTime) {
            state.currentTime = currentTime
        },
        saveUpdateCurrentTime(state,updateCurrentTime) {
            state.updateCurrentTime = updateCurrentTime
        },
        saveMusicIcon(state,url) {
            state.musicicon = url
        },
        saveTableRoute(state,tableroute) {
            state.tableroute = tableroute
        },
        saveMusicId(state,id){
            state.musicid = id
        }
    },
    actions: {
        // 设置音频对象的 src
        setAudioSrc({ commit }, src) {
          const audio = new Audio(src);
          commit("saveAudio", audio);
        }
      },
})

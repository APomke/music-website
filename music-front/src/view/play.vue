<template>
    <div class="container">
        <div class="login" @click="showLoginModal = true" v-if="!userInfo">
            <button>登录</button>
        </div>
        <div class="userinfo" v-if="userInfo">
            <img :src="userInfo.avatar_url" />
        </div>
        <transition name="fade">
            <div class="login-modal" v-if="showLoginModal">
                <div class="modal-content">
                    <span class="close" @click="showLoginModal = false">&times;</span>
                    <form @submit.prevent="handleLogin">
                        <h2>请输入用户名和密码</h2>
                        <input type="text" placeholder="用户名或邮箱" v-model="username" required>
                        <input type="password" placeholder="密码" v-model="password" required>
                        <button class="submit-btn" type="submit">登录</button>
                    </form>
                </div>
            </div>
        </transition>
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

import userapi from '@/api/user'


export default {
    components: {
        search_components,
        sidebar_components,
        recommendations,
        progressbar,
        musicinfo,
        musiccollect
    },
    data() {
        return {
            showLoginModal: false,
            username: null,
            password: null,
            userInfo: null
        }
    },
    methods: {
        handleLogin(data) {
            // 处理登录逻辑
            var user = {
                username: this.username,
                password: this.password
            }
            userapi.login(user).then(response => {
                this.userInfo = response.data.data
                // 将返回的用户信息保存到 sessionStorage
                sessionStorage.setItem('userInfo', JSON.stringify(response.data.data));
            }).catch(error => {
                console.error(error);
            });
            // 关闭模态框
            this.showLoginModal = false;
        },
        logout() {
            // 清除 userInfo
            sessionStorage.removeItem('userInfo');
        }
    },
    created() {
        // console.log(this.$store.state.tableroute[0].isIndex)
        const savedUserInfo = sessionStorage.getItem('userInfo');
        if (savedUserInfo) {
            this.userInfo = JSON.parse(savedUserInfo);
            console.log('从 sessionStorage 读取用户信息:', this.userInfo);
        }
    }
}

</script>

<style>
button[type="submit"] {
    background-color: #48c95e;
    color: white;
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s;
}

button[type="submit"]:hover {
    background-color: #2ab300;
}

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

    .login {
        display: flex;
        justify-content: end;
        padding: 10px;
        /* 可以调整padding以增加或减少按钮周围的空白 */
    }

    .userinfo {
        display: flex;
        justify-content: end;
        padding: 10px;
        img {
            width: 8%;
            height: 8%;
        }
    }

    .login button {
        appearance: none;
        /* 移除默认按钮样式 */
        background-color: #48c95e;
        /* 背景颜色 */
        border: none;
        /* 去除边框 */
        color: white;
        /* 文字颜色 */
        padding: 10px 20px;
        /* 内边距 */
        text-align: center;
        text-decoration: none;
        font-size: 16px;
        cursor: pointer;
        border-radius: 50px;
        /* 圆角 */
        transition: all 0.3s ease;
        /* 过渡动画 */
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        /* 阴影 */
    }

    .login button:hover {
        background-color: #2ab300;
        /* 悬停时改变背景颜色 */
        transform: translateY(-1px);
        /* 悬停时稍微提升按钮 */
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        /* 加深阴影 */
    }

    /* 添加登录模态框的样式 */
    .login-modal {
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.5);
        /* 半透明背景 */
    }

    .modal-content {
        background-color: #ffffff;
        margin: 15% auto;
        padding: 20px;
        border-radius: 10px;
        /* 较大的圆角 */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        /* 阴影效果 */
        width: 30%;
        max-width: 400px;
        /* 最大宽度限制 */
        text-align: center;
        /* 文本居中对齐 */
        border: none;
    }

    .close {
        color: #333;
        float: right;
        font-size: 24px;
        font-weight: bold;
        cursor: pointer;
        transition: color 0.3s;
    }

    .close:hover {
        color: #000;
    }

    /* 表单样式 */
    form {
        display: flex;
        flex-direction: column;
        gap: 15px;
        /* 输入框之间留空 */
    }

    input {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        outline: none;
        font-size: 16px;
    }

    input::placeholder {
        color: #999;
    }

    h2 {
        color: #333;
        margin-bottom: 20px;
    }

    /* 过渡动画 */
    .fade-enter-active,
    .fade-leave-active {
        transition: opacity 0.5s;
    }

    .fade-enter-from,
    .fade-leave-to {
        opacity: 0;
    }
    
}
</style>

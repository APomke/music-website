<template>
    <div class="container">
        <div class="login" @click="showLoginModal = true" v-if="!userInfo">
            <button>登录</button>
        </div>
        <div class="userinfo" v-if="userInfo" @mouseenter="showDropdown = true" @mouseleave="showDropdown = false">
            <img :src="userInfo.avatar_url" />
            <div class="dropdown" v-show="showDropdown">
                <div class="user-info">
                    <p>{{ userInfo.username }}</p>
                    <p>{{ userInfo.email }}</p>
                </div>
                <button @click="logout">退出登录</button>
            </div>
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
            userInfo: null,
            showDropdown: false
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
                if (response.data.code == 403) {
                    this.$notify.error({
                        title: '登录失败',
                        message: response.data.info
                    });
                } else {
                    const jwt = response.data.data
                    // 将返回的jwt保存到 sessionStorage
                    sessionStorage.setItem('jwt', JSON.stringify(response.data.data));
                    // 关闭模态框
                    this.showLoginModal = false;

                    // 通过jwt获取用户信息
                    userapi.getUserInfo().then(response => {
                        if (response.data.code == 403) {
                            this.$notify.error({
                                title: '获取用户信息失败',
                                message: response.data.info
                            });
                        } else {
                            this.userInfo = response.data.data;
                            this.$notify({
                                title: '登录成功',
                                message: "登录成功",
                                type: 'success'
                            });
                        }
                    })
                }

            }).catch(error => {
                console.error(error);

            });
        },
        logout() {
            // 清除 userInfo
            sessionStorage.removeItem('userInfo');
            this.$store.commit("saveUserInfo", null);
            // 重新加载网页或者让页面动态刷新
            // 重新加载网页
            location.reload();
        }
    },
    created() {
        // console.log(this.$store.state.tableroute[0].isIndex)
        const jwt = sessionStorage.getItem('jwt');
        if (jwt) {
            // 通过jwt获取用户信息
            userapi.getUserInfo().then(response => {
                if (response.data.code == 403) {
                    this.$notify.error({
                        title: '获取用户信息失败',
                        message: response.data.info
                    });
                } else {
                    this.userInfo = response.data.data;
                    // this.$notify({
                    //     title: '登录成功',
                    //     message: "登录成功",
                    //     type: 'success'
                    // });
                }
            })
        }
    },
    mounted() {

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


.dropdown {
    position: absolute;
    /* 绝对定位 */
    top: 220%;
    /* 在头像下方 */
    left: 50%;
    /* 居中对齐 */
    transform: translateX(-50%);
    /* 水平居中 */
    background-color: #fff;
    /* 背景颜色 */
    border: 1px solid #ccc;
    /* 边框 */
    border-radius: 5px;
    /* 圆角 */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    /* 阴影 */
    padding: 10px;
    min-width: 150px;
    /* 最小宽度 */
    display: flex;
    flex-direction: column;
    align-items: center;
    z-index: 1000;
    /* 确保下拉菜单位于最顶层 */
}

.dropdown .user-info {
    text-align: center;
    margin-bottom: 10px;
}

.dropdown button {
    background-color: #48c95e;
    color: white;
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s;
}

.dropdown button:hover {
    background-color: #319542;
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
        position: absolute;
        right: 90px;
        /* 距离右边的距离 */
        padding: 10px;
        /* 可以调整padding以增加或减少按钮周围的空白 */
    }

    .userinfo {
        position: absolute;
        top: 20px;
        /* 距离顶部的距离 */
        right: 90px;
        /* 距离右边的距离 */
        display: flex;
        justify-content: center;
        /* 水平居中 */
        align-items: center;
        /* 垂直居中 */
        width: 20px;
        /* 宽度 */
        height: 20px;
        /* 高度 */
        background-color: #f8f9fa;
        /* 背景颜色 */
        border: 1px solid #dee2e6;
        /* 边框 */
        border-radius: 50%;
        /* 圆形边框 */
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        /* 添加阴影 */
        transition: transform 0.3s, box-shadow 0.3s;
        /* 平滑过渡 */
        z-index: 999;
    }

    .userinfo:hover {
        transform: scale(1.05);
        /* 悬停时放大 */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        /* 悬停时加深阴影 */
    }

    .userinfo img {
        border-radius: 50%;
        /* 使头像呈圆形 */
        width: 60px;
        /* 头像的宽度 */
        height: 60px;
        /* 头像的高度 */
        object-fit: cover;
        /* 保持图片的纵横比并覆盖整个区域 */
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        /* 添加阴影 */
        transition: transform 0.3s, box-shadow 0.3s;
        /* 平滑过渡 */
    }

    .userinfo img:hover {
        transform: scale(1.1);
        /* 悬停时放大 */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        /* 悬停时加深阴影 */
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
        justify-content: center;
        margin: 15% auto;
        padding: 20px;
        border-radius: 20px;
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

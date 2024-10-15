<template>
    <div class="upload-container" v-loading="loading" element-loading-text="正在上传中，请勿关闭网页"
        element-loading-spinner="el-icon-loading">
        <div class="upload-form">
            <el-form :model="form" ref="formRef" :rules="rules" label-position="top">
                <el-form-item prop="title">
                    <h2>歌名：</h2>
                    <el-input placeholder="请输入音乐名称" v-model="form.title"></el-input>
                </el-form-item>
                <el-form-item prop="artist">
                    <h2>作者：</h2>
                    <el-input placeholder="请输入音乐作者" v-model="form.artist"></el-input>
                </el-form-item>
                <h2>封面：</h2>
                <el-upload class="cover-upload" list-type="picture-card" :auto-upload="false"
                    :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :file-list="fileList"
                    ref="coverUpload" :action="''" :on-change="handleCoverChange">
                    <i slot="default" class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
                <el-button type="primary" @click="submitForm">提交</el-button>
            </el-form>
        </div>
        <!--上传音乐文件，显示在页面右边-->
        <div class="music-file-upload">
            <h2>音乐文件</h2>
            <el-upload class="upload-demo" drag multiple :on-change="beforeUploadMusic" :disabled="isUploadDisabled"
                ref="musicUpload" :auto-upload="false" :action="''">
                <div v-if="!isUploadDisabled">
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                    <div class="el-upload__tip" slot="tip">只能上传mp3/wav文件，且不超过50MB</div>
                </div>
                <div v-else>
                    <i class="el-icon-upload2"></i>
                    <div class="el-upload__text">文件已保存，请提交</div>
                </div>
            </el-upload>
        </div>
    </div>
</template>

<script>
import userapi from '@/api/user'
import musicapi from '@/api/music'
export default {
    data() {
        return {
            form: {
                title: '',
                artist: ''
            },
            rules: {
                title: [{ required: true, message: '请输入歌曲标题', trigger: 'blur' }],
                artist: [{ required: true, message: '请输入艺术家名称', trigger: 'blur' }]
            },
            fileList: [],
            musicFiles: [],
            dialogImageUrl: '',
            dialogVisible: false,
            isUploadDisabled: false, // 控制上传按钮是否禁用
            uid: null,
            audio: new Audio(),
            file: null,
            fileName: null,
            iconfile: null,
            iconFileName: null,
            loading: false,
        };
    },
    methods: {
        handleRemove(file) {
            const index = this.fileList.indexOf(file);
            if (index !== -1) {
                this.fileList.splice(index, 1);
            }
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        handleCoverChange(file, fileList) {
            // 当封面发生变化时，记录最新的文件信息
            this.iconfile = file.raw;

            this.fileList = fileList;
            this.iconFileName = file.name;
            this.form.coverFileName = file.name;
        },
        beforeUploadMusic(file) {
            this.file = file.raw;
            this.fileName = file.name;
            this.musicFiles.push(file.name); // 记录音乐文件名
            this.isUploadDisabled = true; // 文件上传后禁用上传组件
            this.filesize = file.size / 1024 / 1024
        },
        async uploadFileToOSS(uploadData, file) {
            const formData = new FormData();
            formData.append('key', `${uploadData.dir}/${file.name}`); // 上传路径和文件名
            formData.append('OSSAccessKeyId', uploadData.accessid);
            formData.append('policy', uploadData.policy);
            formData.append('signature', uploadData.signature);
            formData.append('success_action_status', '200'); // 成功状态码
            formData.append('file', file); // 文件对象

            const response = await fetch(uploadData.host, {
                method: 'POST',
                body: formData
            });

            if (response.status === 200 || response.status === 204) { // 根据OSS文档，可能是200或204
                // 构建文件的访问URL
                return Promise.resolve({ success: true }); // 上传成功
            } else {
                return Promise.reject(new Error('Failed to upload file.')); // 上传失败
            }
        },
        submitForm() {
            this.$refs.formRef.validate(valid => {
                this.loading = true;

                if (valid) {
                    var music = {
                        "title": this.form.title,
                        "artist": this.form.artist,
                        "file_size": this.filesize,
                        "upload_user_uid": this.uid
                    }

                    // console.log(music);
                    // 上传音乐文件
                    musicapi.uploadMusic(music, this.fileName,this.iconFileName).then(response => {
                        if (response.data.code == 200) {
                            // 获取到临时上传凭证后开始上传
                            const uploadData = response.data.data;

                            // 假设 `this.file` 是你要上传的文件
                            this.uploadFileToOSS(uploadData, this.file).then(() => {
                                this.loading = false;
                                console.log('音乐文件上传成功');
                                this.$notify({
                                    title: '音乐上传功',
                                    message: "音乐上传成功",
                                    type: 'success'
                                });

                            }).catch(error => {
                                console.error('上传失败:', error);
                                this.loading = false;
                            });

                            // 上传icon文件
                            this.uploadFileToOSS(uploadData, this.iconfile).then(() => {
                                console.log('封面上传成功');
                            }).catch(error => {
                                console.error('上传失败:', error);
                            });
                        }
                    })


                } else {
                    console.log('表单验证失败');
                    return false;
                }
            });

        },
        async getUserInfo() {
            // 通过jwt获取用户信息
            userapi.getUserInfo().then(async response => {
                if (response.data.code == 403) {
                    this.$notify.error({
                        title: '获取用户信息失败',
                        message: response.data.info
                    });
                } else {
                    this.uid = response.data.data.uid;
                    console.log("uid赋值成功", this.uid)
                }
            })
        },
        // 把播放时间转换为00:00的结构
        formatTime(seconds) {
            const minutes = Math.floor(seconds / 60);
            const secondsLeft = Math.floor(seconds % 60);
            return `${String(minutes).padStart(2, '0')}:${String(secondsLeft).padStart(2, '0')}`;
        },
    },
    created() {
        this.audio = this.$store.state.audio;
        const jwt = sessionStorage.getItem('jwt');
        // 如果用户已经登录
        if (jwt) {
            this.getUserInfo();
        } else {
            this.$notify({
                title: '请先登录',
                message: '请先登录',
                type: 'warning'
            });
        }
    },
    watch: {
        // 监控进度条播放按钮状态
        "$store.state.music.playing": {
            deep: true,//深度监听设置为 true
            handler: function (newVal, oldVal) {
                if (newVal) {
                    //播放音乐
                    this.$store.state.audio.play();
                    // 修改vuex里的音乐状态
                    this.$store.commit("updateMusicState", true)
                } else {
                    //暂停音乐
                    this.$store.state.audio.pause();
                    // 修改vuex里的音乐状态
                    this.$store.commit("updateMusicState", false)
                }
            }
        },
        "$store.state.updateCurrentTime": {
            deep: true,
            handler: function (newVal, oldVal) {
                if (newVal) {
                    // 修改音乐播放进度
                    // console.log("修改播放进度")
                    this.audio.currentTime = newVal
                    this.$store.commit("saveAudio", this.audio)
                }
            }
        },

    },
    mounted() {

        // 监听音频播放状态变化
        this.audio.addEventListener('timeupdate', () => {
            this.currentTime = this.formatTime(this.audio.currentTime);
            // 保存到vux
            this.$store.commit("saveCurrentTime", this.$store.state.audio.currentTime)
            // console.log("当前音乐播放时间:", this.currentTime)
        });

        // 监听音频播放结束
        this.audio.addEventListener('ended', () => {
            console.log('音频播放结束');
            this.currentTime = 0;
        });
    },
}
</script>

<style scoped>
.upload-container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
    overflow-y: auto;
    width: calc(100% - 10%);
    margin-left: 10%;
    padding: 20px;
}

.cover-upload {
    margin-bottom: 20px;
}

.upload-form {
    flex: 1;
    margin-right: 20px;
}

.music-file-upload {
    flex: 1;
    margin-left: 80px;
}
</style>
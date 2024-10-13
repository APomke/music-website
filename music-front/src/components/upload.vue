<template>
    <div class="upload-container">
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
            this.fileList = fileList;
            this.form.coverFileName = file.name;
        },
        beforeUploadMusic(file) {
            this.musicFiles.push(file.name); // 记录音乐文件名
            this.isUploadDisabled = true; // 文件上传后禁用上传组件
            this.filesize = file.size / 1024 / 1024
        },
        submitForm() {
            this.$refs.formRef.validate(valid => {
                if (valid) {
                    var music = {
                        "title": this.form.title,
                        "artist": this.form.artist,
                        "file_size": this.filesize,
                        "upload_user_uid": this.uid
                    }

                    console.log(music);

                    musicapi.uploadMusic(music).then(response => {
                        // console.log("判断音乐是否已被收藏", response)
                        if (response.data.code == 200) {
                            console.log(response.data.data);
                        }
                    })


                    // 手动上传封面文件
                    // this.$refs.coverUpload.submit();

                    // 手动上传音乐文件
                    // this.$refs.musicUpload.submit();

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
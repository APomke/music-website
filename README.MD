# 项目介绍

纯净的在线听歌的音乐网站

技术栈: SpringBoot + Vue

使用JWT作为网站的身份验证，使用对象存储来保存音乐文件（阿里云oss），使用element-ui作为ui库，数据库为mysql8.0

前后端分离项目

![image-20241009210931434](https://awsimage-1.oss-cn-hangzhou.aliyuncs.com/image-20241009210931434.png)

可自由上传播放音乐

可收藏指定音乐



使用对象存储来保存音乐文件（阿里云oss）可以节省服务器资源，关于文件的下载和上传都是由客户端（用户浏览器）去请求阿里云oss来完成



# 项目部署



## 前端

进入前端项目根目录

```
cd music-front
```

使用npm安装依赖

```
npm install
```

运行

```
npm run serve
```



## 后端

请先在自己的数据库中导入sql文件music.sql，该sql文件在resources文件夹下

进入后端项目里的application.yml配置文件

把数据库ip，用户名，密码更换为自己的

接下来请配置阿里云accessId，accessKey，endpoint，bucket

关于如何在阿里云获取这些

https://help.aliyun.com/zh/oss/use-cases/obtain-signature-information-from-the-server-and-upload-data-to-oss?spm=a2c4g.11186623.6.1739.3023691cicL1Bl






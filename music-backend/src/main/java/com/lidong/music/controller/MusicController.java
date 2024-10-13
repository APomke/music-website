
package com.lidong.music.controller;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lidong.music.entity.Music;
import com.lidong.music.service.MusicService;
import com.lidong.music.entity.ResponseVO;
import com.lidong.music.utils.OssTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MusicController {

//    @Autowired
//    private MusicService musicService;

    private static final Logger logger = LoggerFactory.getLogger(MusicController.class);

    private final MusicService musicService;
    private final OssTools ossTools;

    @Autowired
    public MusicController(MusicService musicService, OssTools ossTools) {
        this.musicService = musicService;
        this.ossTools = ossTools;
    }
    
    // 查询

    // 获取推荐音乐列表
    @GetMapping("/music/recommendations")
    public ResponseVO recommendations() {
        ResponseVO responseVO = new ResponseVO();
        List<Music> musicList = musicService.list();
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
//        System.out.println(musicList.toString());
//        System.out.println(responseVO.toString());
        return responseVO;
    }
    
    
    @GetMapping("/getMusicListById")
    public ResponseVO getMusicById(String id) {
        ResponseVO responseVO = new ResponseVO();
        
        Music musicList = musicService.getMusicById(id);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
        return responseVO;
    }
    
    
    @GetMapping("/getMusicListByTitle")
    public ResponseVO getMusicListByTitle(String title) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Music> musicList = musicService.getMusicListByTitle(title);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
        return responseVO;
    }
    
    
    @GetMapping("/getMusicListByArtist")
    public ResponseVO getMusicListByArtist(String artist) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Music> musicList = musicService.getMusicListByArtist(artist);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
        return responseVO;
    }
    
    
    @GetMapping("/getMusicListByUrl")
    public ResponseVO getMusicListByUrl(String url) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Music> musicList = musicService.getMusicListByUrl(url);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
        return responseVO;
    }
    
    
    @GetMapping("/getMusicListByIconUrl")
    public ResponseVO getMusicListByIconUrl(String icon_url) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Music> musicList = musicService.getMusicListByIconUrl(icon_url);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
        return responseVO;
    }
    
    
    @GetMapping("/getMusicListByLyricUrl")
    public ResponseVO getMusicListByLyricUrl(String lyric_url) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Music> musicList = musicService.getMusicListByLyricUrl(lyric_url);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
        return responseVO;
    }
    
    
    @GetMapping("/getMusicListByFileSize")
    public ResponseVO getMusicListByFileSize(String file_size) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Music> musicList = musicService.getMusicListByFileSize(file_size);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
        return responseVO;
    }
    
    
    @GetMapping("/getMusicListByLevel")
    public ResponseVO getMusicListByLevel(String level) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Music> musicList = musicService.getMusicListByLevel(level);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
        return responseVO;
    }
    
    
    @GetMapping("/getMusicListByCollectCount")
    public ResponseVO getMusicListByCollectCount(String collect_count) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Music> musicList = musicService.getMusicListByCollectCount(collect_count);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(musicList);
        return responseVO;
    }
    
    
    // 修改
    @PostMapping("/updateMusic")
    public ResponseVO updateMusic(Music music) {
        ResponseVO responseVO = new ResponseVO();
        int i = musicService.updateMusic(music);
        responseVO.setCode(200);
        responseVO.setInfo("修改成功！");
        responseVO.setStatus("success");
        return responseVO;
        
    }
    // 增加
    @PostMapping("/music/addMusic")
    public ResponseVO addMusic(@RequestBody Music music) {
        ResponseVO responseVO = new ResponseVO();

        try {
            // 调用音乐服务添加音乐
//            int result = musicService.addMusic(music);

            if (true) {
                // 获取OSS临时上传凭证
                JSONObject signature = ossTools.getSignature(music.getTitle());
                responseVO.setCode(200);
                responseVO.setInfo("增加成功！");
                responseVO.setStatus("success");
                responseVO.setData(signature);
            } else {
                responseVO.setCode(500);
                responseVO.setInfo("音乐添加失败！");
                responseVO.setStatus("error");
            }
        } catch (Exception e) {
            logger.error("Failed to add music: {}", e.getMessage(), e);
            responseVO.setCode(500);
            responseVO.setInfo("系统错误，请稍后再试！");
            responseVO.setStatus("error");
        }

        return responseVO;
    }
    // 删除
    @PostMapping("/deleteMusic")
    public ResponseVO deleteMusic(Music music) {
        ResponseVO responseVO = new ResponseVO();
        int i = musicService.deleteMusic(music.getId());
        responseVO.setCode(200);
        responseVO.setInfo("删除成功！");
        responseVO.setStatus("success");
        return responseVO;
    }
    
}

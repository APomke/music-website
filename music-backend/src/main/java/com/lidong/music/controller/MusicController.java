
package com.lidong.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lidong.music.entity.Music;
import com.lidong.music.service.MusicService;
import com.lidong.music.entity.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MusicController {

    @Autowired
    private MusicService musicService;
    
    // 查询
    
    
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
    @PostMapping("/addMusic")
    public ResponseVO addMusic(Music music) {
        ResponseVO responseVO = new ResponseVO();
        int i = musicService.addMusic(music);
        responseVO.setCode(200);
        responseVO.setInfo("增加成功！");
        responseVO.setStatus("success");
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

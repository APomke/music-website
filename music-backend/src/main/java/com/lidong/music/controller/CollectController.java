
package com.lidong.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lidong.music.entity.Collect;
import com.lidong.music.service.CollectService;
import com.lidong.music.entity.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;
    
    // 查询
    
    
    @GetMapping("/getCollectListById")
    public ResponseVO getCollectById(String id) {
        ResponseVO responseVO = new ResponseVO();
        
        Collect collectList = collectService.getCollectById(id);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(collectList);
        return responseVO;
    }
    
    
    @GetMapping("/getCollectListByUid")
    public ResponseVO getCollectListByUid(String uid) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Collect> collectList = collectService.getCollectListByUid(uid);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(collectList);
        return responseVO;
    }
    
    
    @GetMapping("/getCollectListByMusicTitle")
    public ResponseVO getCollectListByMusicTitle(String music_title) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Collect> collectList = collectService.getCollectListByMusicTitle(music_title);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(collectList);
        return responseVO;
    }
    
    
    @GetMapping("/getCollectListByMusicArtist")
    public ResponseVO getCollectListByMusicArtist(String music_artist) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Collect> collectList = collectService.getCollectListByMusicArtist(music_artist);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(collectList);
        return responseVO;
    }
    
    
    @GetMapping("/getCollectListByUrl")
    public ResponseVO getCollectListByUrl(String url) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Collect> collectList = collectService.getCollectListByUrl(url);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(collectList);
        return responseVO;
    }
    
    
    @GetMapping("/getCollectListByIconUrl")
    public ResponseVO getCollectListByIconUrl(String icon_url) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Collect> collectList = collectService.getCollectListByIconUrl(icon_url);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(collectList);
        return responseVO;
    }
    
    
    @GetMapping("/getCollectListByLevel")
    public ResponseVO getCollectListByLevel(String level) {
        ResponseVO responseVO = new ResponseVO();
        
        List<Collect> collectList = collectService.getCollectListByLevel(level);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(collectList);
        return responseVO;
    }
    
    
    // 修改
    @PostMapping("/updateCollect")
    public ResponseVO updateCollect(Collect collect) {
        ResponseVO responseVO = new ResponseVO();
        int i = collectService.updateCollect(collect);
        responseVO.setCode(200);
        responseVO.setInfo("修改成功！");
        responseVO.setStatus("success");
        return responseVO;
        
    }
    // 增加
    @PostMapping("/addCollect")
    public ResponseVO addCollect(Collect collect) {
        ResponseVO responseVO = new ResponseVO();
        int i = collectService.addCollect(collect);
        responseVO.setCode(200);
        responseVO.setInfo("增加成功！");
        responseVO.setStatus("success");
        return responseVO;
    }
    // 删除
    @PostMapping("/deleteCollect")
    public ResponseVO deleteCollect(Collect collect) {
        ResponseVO responseVO = new ResponseVO();
        int i = collectService.deleteCollect(collect.getId());
        responseVO.setCode(200);
        responseVO.setInfo("删除成功！");
        responseVO.setStatus("success");
        return responseVO;
    }
    
}

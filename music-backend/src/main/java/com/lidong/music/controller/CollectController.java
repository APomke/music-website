
package com.lidong.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.lidong.music.entity.Collect;
import com.lidong.music.entity.Music;
import com.lidong.music.entity.User;
import com.lidong.music.service.CollectService;
import com.lidong.music.entity.ResponseVO;
import com.lidong.music.utils.JwtTools;
import com.lidong.music.utils.StringTools;
import com.mysql.cj.util.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.lidong.music.utils.JwtTools.objectMapper;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @Autowired
    HttpServletRequest request;
    
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
    // 收藏音乐
    @PostMapping("/CollectMusic")
    public ResponseVO collectMusic(HttpServletRequest request,HttpSession session, @RequestBody Music music) throws JsonProcessingException {
        ResponseVO responseVO = new ResponseVO();
        // 把music对象转为collect对象

        // 从请求头中获取 JWT，并验证
        String header = request.getHeader("Authorization");
        String token = header.substring(7).replaceAll("^\"|\"$", ""); // 去除首尾双引号
        System.out.println("获取的jwt:" + token);
        // 验证 JWT
        Claims claims = JwtTools.validateToken(token);
        // uid 存储在 userJson 中
        JsonNode userNode = objectMapper.readTree(claims.getSubject());
        String uid = userNode.get("uid").asText();

        System.out.println("获取的音乐对象：" + music.toString());

        Collect collect = new Collect();
        collect.setId(StringTools.getRandomUUID());
        collect.setUid(uid);
        collect.setTitle(music.getTitle());
        collect.setUrl(music.getUrl());
        collect.setMusic_artist(music.getArtist());
        collect.setIcon_url(music.getIcon_url());
        collect.setLevel(music.getLevel());
        int i = collectService.addCollect(collect);
        System.out.printf(String.valueOf(i));
        if (i > 0) {
            responseVO.setCode(200);
            responseVO.setInfo("收藏成功");
        }else {
            responseVO.setCode(500);
            responseVO.setInfo("收藏失败");
        }
        return responseVO;
    }

    // 判断用户是否收藏指定音乐
    @GetMapping("/isCollect")
    public ResponseVO isCollect(HttpServletRequest request,String title) throws JsonProcessingException {
        ResponseVO responseVO = new ResponseVO();
//        System.out.println("获取的音乐为：" + title);

        // 从请求头中获取 JWT，并验证
        String header = request.getHeader("Authorization");
        String token = header.substring(7).replaceAll("^\"|\"$", ""); // 去除首尾双引号
        System.out.println("获取的jwt:" + token);
        // 验证 JWT
        Claims claims = JwtTools.validateToken(token);
        // uid 存储在 userJson 中
        JsonNode userNode = objectMapper.readTree(claims.getSubject());
        String uid = userNode.get("uid").asText();
//        System.out.println("获取的uid为："+uid);


        if (collectService.isCollect(title,uid)) {
            responseVO.setCode(200);
            responseVO.setInfo("音乐已被收藏");
        }else {
            responseVO.setCode(201);
            responseVO.setInfo("音乐未被收藏");
        }
        return responseVO;
    }
    // 取消收藏指定音乐
    @GetMapping("/unCollect")
    public ResponseVO unCollect(HttpServletRequest request,String music_title,HttpSession session) throws JsonProcessingException {
        ResponseVO responseVO = new ResponseVO();
        User userInfo = (User) session.getAttribute("userInfo");

        String header = request.getHeader("Authorization");
        String token = header.substring(7).replaceAll("^\"|\"$", ""); // 去除首尾双引号
        System.out.println("获取的jwt:" + token);
        // 验证 JWT
        Claims claims = JwtTools.validateToken(token);
        // uid 存储在 userJson 中
        JsonNode userNode = objectMapper.readTree(claims.getSubject());

        String uid = userNode.get("uid").asText();

        if (collectService.unCollect(music_title,uid)) {
            responseVO.setCode(200);
        }else {
            responseVO.setCode(500);
        }
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

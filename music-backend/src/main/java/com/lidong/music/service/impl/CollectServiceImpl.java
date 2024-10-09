
package com.lidong.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lidong.music.mapper.CollectMapper;
import com.lidong.music.entity.Collect;
import com.lidong.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    
    @Autowired
    private CollectMapper collectMapper;
    // 查询
    public Collect getCollect(int id) {
        return collectMapper.selectById(id);
    }

    //
    
    @Override
    
    public Collect getCollectById(String id) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        return collectMapper.selectById(queryWrapper);
    }
    
    
    @Override
    
    public List<Collect> getCollectListByUid(String uid) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        return collectMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Collect> getCollectListByMusicTitle(String music_title) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",music_title);
        return collectMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Collect> getCollectListByMusicArtist(String music_artist) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("musicartist",music_artist);
        return collectMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Collect> getCollectListByUrl(String url) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url",url);
        return collectMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Collect> getCollectListByIconUrl(String icon_url) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("iconurl",icon_url);
        return collectMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Collect> getCollectListByLevel(String level) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level",level);
        return collectMapper.selectList(queryWrapper);
    }
    
    
    // 修改
    @Override
    public int updateCollect(Collect collect) {
        return collectMapper.updateById(collect);
    }
    
    // 增加
    @Override
    public int addCollect(Collect collect) {
        return collectMapper.insert(collect);
    }
    
    // 删除
    @Override
    public int deleteCollect(String id) {
        return collectMapper.deleteById(id);
    }

    // 判断用户是否收藏该音乐
    @Override
    public boolean isCollect(String title, String uid) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title).eq("uid",uid);
        List<Collect> collects = collectMapper.selectList(queryWrapper);
        if (!collects.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    // 取消指定用户的指定收藏音乐
    @Override
    public boolean unCollect(String title, String uid) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title).eq("uid",uid);
        return collectMapper.delete(queryWrapper) > 0;
    }


}
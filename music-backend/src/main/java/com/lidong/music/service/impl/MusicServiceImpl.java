
package com.lidong.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lidong.music.mapper.MusicMapper;
import com.lidong.music.entity.Music;
import com.lidong.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {
    
    @Autowired
    private MusicMapper musicMapper;
    // 查询
    public Music getMusic(int id) {
        return musicMapper.selectById(id);
    }
    
    
    @Override
    
    public Music getMusicById(String id) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        return musicMapper.selectById(queryWrapper);
    }
    
    
    @Override
    
    public List<Music> getMusicListByTitle(String title) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        return musicMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Music> getMusicListByArtist(String artist) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("artist",artist);
        return musicMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Music> getMusicListByUrl(String url) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url",url);
        return musicMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Music> getMusicListByIconUrl(String icon_url) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("iconurl",icon_url);
        return musicMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Music> getMusicListByLyricUrl(String lyric_url) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lyricurl",lyric_url);
        return musicMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Music> getMusicListByFileSize(String file_size) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("filesize",file_size);
        return musicMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Music> getMusicListByLevel(String level) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level",level);
        return musicMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<Music> getMusicListByCollectCount(String collect_count) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("collectcount",collect_count);
        return musicMapper.selectList(queryWrapper);
    }
    
    
    // 修改
    @Override
    public int updateMusic(Music music) {
        return musicMapper.updateById(music);
    }
    
    // 增加
    @Override
    public int addMusic(Music music) {
        return musicMapper.insert(music);
    }
    
    // 删除
    @Override
    public int deleteMusic(String id) {
        return musicMapper.deleteById(id);
    }
}
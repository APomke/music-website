
package com.lidong.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lidong.music.entity.Music;
import java.util.List;

public interface MusicService extends IService<Music> {
    /**
    * @author admin
    * @version 1.0
    * @Description Service接口
    */
    // 查询

    
    Music getMusicById(String id);
    
    
    List<Music> getMusicListByTitle(String title);
    
    
    List<Music> getMusicListByArtist(String artist);
    
    
    List<Music> getMusicListByUrl(String url);
    
    
    List<Music> getMusicListByIconUrl(String icon_url);
    
    
    List<Music> getMusicListByLyricUrl(String lyric_url);
    
    
    List<Music> getMusicListByFileSize(String file_size);
    
    
    List<Music> getMusicListByLevel(String level);
    
    
    List<Music> getMusicListByCollectCount(String collect_count);
    
    // 修改
    int updateMusic(Music music);
    
    // 增加
    int addMusic(Music music);
    
    // 删除
    int deleteMusic(String id);
    
}
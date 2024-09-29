
package com.lidong.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lidong.music.entity.Collect;
import java.util.List;

public interface CollectService extends IService<Collect> {
    /**
    * @author admin
    * @version 1.0
    * @Description Service接口
    */
    // 查询

    
    Collect getCollectById(String id);
    
    
    List<Collect> getCollectListByUid(String uid);
    
    
    List<Collect> getCollectListByMusicTitle(String music_title);
    
    
    List<Collect> getCollectListByMusicArtist(String music_artist);
    
    
    List<Collect> getCollectListByUrl(String url);
    
    
    List<Collect> getCollectListByIconUrl(String icon_url);
    
    
    List<Collect> getCollectListByLevel(String level);
    
    // 修改
    int updateCollect(Collect collect);
    
    // 增加收藏音乐
    int addCollect(Collect collect);
    
    // 删除
    int deleteCollect(String id);

    // 判断用户是否已收藏该音乐
    boolean isCollect(String title,String uid);

    // 取消指定用户的指定收藏音乐
    boolean unCollect(String title, String uid);

    
}
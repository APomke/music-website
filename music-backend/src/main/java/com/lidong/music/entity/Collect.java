
package com.lidong.music.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collect {
    /**
    * @author admin
    * @version 1.0
    * @Description 实体类
    */
    
    private String id;
    private String uid;
    private String title;
    private String music_artist;
    private String url;
    private String icon_url;
    private String level;
}
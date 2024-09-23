
package com.lidong.music.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    /**
    * @author admin
    * @version 1.0
    * @Description 实体类
    */
    
    private String id;
    private String title;
    private String artist;
    private String url;
    private String icon_url;
    private String lyric_url;
    private String file_size;
    private String level;
    private String collect_count;
}
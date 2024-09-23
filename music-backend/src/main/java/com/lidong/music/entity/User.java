
package com.lidong.music.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
    * @author admin
    * @version 1.0
    * @Description 实体类
    */
    
    private String uid;
    private String username;
    private String password;
    private String email;
    private String sex;
    private String age;
    private String avatar_url;
}
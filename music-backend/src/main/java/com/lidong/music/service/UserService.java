
package com.lidong.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lidong.music.entity.User;
import java.util.List;

public interface UserService extends IService<User> {
    /**
    * @author admin
    * @version 1.0
    * @Description Service接口
    */
    // 查询
    
    List<User> getUserListByUid(String uid);
    
    
    List<User> getUserListByUsername(String username);
    
    
    List<User> getUserListByPassword(String password);
    
    
    List<User> getUserListByEmail(String email);
    
    
    List<User> getUserListBySex(String sex);
    
    
    List<User> getUserListByAge(String age);
    
    
    List<User> getUserListByAvatarUrl(String avatar_url);
    
    // 修改
    int updateUser(User user);
    
    // 增加
    int addUser(User user);
    
    // 删除
    int deleteUser(String id);
    
}
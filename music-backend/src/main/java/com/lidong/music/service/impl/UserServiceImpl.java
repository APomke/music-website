
package com.lidong.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lidong.music.mapper.UserMapper;
import com.lidong.music.entity.User;
import com.lidong.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    // 查询
    public User getUser(int id) {
        return userMapper.selectById(id);
    }
    
    
    @Override
    
    public List<User> getUserListByUid(String uid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        return userMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<User> getUserListByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<User> getUserListByPassword(String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("password",password);
        return userMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<User> getUserListByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        return userMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<User> getUserListBySex(String sex) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex",sex);
        return userMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<User> getUserListByAge(String age) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age",age);
        return userMapper.selectList(queryWrapper);
    }
    
    
    @Override
    
    public List<User> getUserListByAvatarUrl(String avatar_url) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("avatarurl",avatar_url);
        return userMapper.selectList(queryWrapper);
    }
    
    
    // 修改
    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }
    
    // 增加
    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }
    
    // 删除
    @Override
    public int deleteUser(String id) {
        return userMapper.deleteById(id);
    }
}

package com.lidong.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lidong.music.entity.User;
import com.lidong.music.service.UserService;
import com.lidong.music.entity.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    // 查询
    
    
    @GetMapping("/getUserListByUid")
    public ResponseVO getUserListByUid(String uid) {
        ResponseVO responseVO = new ResponseVO();
        
        List<User> userList = userService.getUserListByUid(uid);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(userList);
        return responseVO;
    }
    
    
    @GetMapping("/getUserListByUsername")
    public ResponseVO getUserListByUsername(String username) {
        ResponseVO responseVO = new ResponseVO();
        
        List<User> userList = userService.getUserListByUsername(username);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(userList);
        return responseVO;
    }
    
    
    @GetMapping("/getUserListByPassword")
    public ResponseVO getUserListByPassword(String password) {
        ResponseVO responseVO = new ResponseVO();
        
        List<User> userList = userService.getUserListByPassword(password);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(userList);
        return responseVO;
    }
    
    
    @GetMapping("/getUserListByEmail")
    public ResponseVO getUserListByEmail(String email) {
        ResponseVO responseVO = new ResponseVO();
        
        List<User> userList = userService.getUserListByEmail(email);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(userList);
        return responseVO;
    }
    
    
    @GetMapping("/getUserListBySex")
    public ResponseVO getUserListBySex(String sex) {
        ResponseVO responseVO = new ResponseVO();
        
        List<User> userList = userService.getUserListBySex(sex);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(userList);
        return responseVO;
    }
    
    
    @GetMapping("/getUserListByAge")
    public ResponseVO getUserListByAge(String age) {
        ResponseVO responseVO = new ResponseVO();
        
        List<User> userList = userService.getUserListByAge(age);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(userList);
        return responseVO;
    }
    
    
    @GetMapping("/getUserListByAvatarUrl")
    public ResponseVO getUserListByAvatarUrl(String avatar_url) {
        ResponseVO responseVO = new ResponseVO();
        
        List<User> userList = userService.getUserListByAvatarUrl(avatar_url);
        responseVO.setCode(200);
        responseVO.setInfo("查询成功！");
        responseVO.setStatus("success");
        responseVO.setData(userList);
        return responseVO;
    }
    
    
    // 修改
    @PostMapping("/updateUser")
    public ResponseVO updateUser(User user) {
        ResponseVO responseVO = new ResponseVO();
        int i = userService.updateUser(user);
        responseVO.setCode(200);
        responseVO.setInfo("修改成功！");
        responseVO.setStatus("success");
        return responseVO;
        
    }
    // 增加
    @PostMapping("/addUser")
    public ResponseVO addUser(User user) {
        ResponseVO responseVO = new ResponseVO();
        int i = userService.addUser(user);
        responseVO.setCode(200);
        responseVO.setInfo("增加成功！");
        responseVO.setStatus("success");
        return responseVO;
    }
    // 删除
    @PostMapping("/deleteUser")
    public ResponseVO deleteUser(User user) {
        ResponseVO responseVO = new ResponseVO();
        int i = userService.deleteUser(user.getUid());
        responseVO.setCode(200);
        responseVO.setInfo("删除成功！");
        responseVO.setStatus("success");
        return responseVO;
    }
    
}

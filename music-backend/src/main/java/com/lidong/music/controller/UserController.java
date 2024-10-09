
package com.lidong.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.lidong.music.entity.User;
import com.lidong.music.service.UserService;
import com.lidong.music.entity.ResponseVO;
import com.lidong.music.utils.JwtTools;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.lidong.music.utils.JwtTools.objectMapper;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    // 查询
    // 登录
    @PostMapping("/user/login")
    public ResponseVO login(@RequestBody User user, HttpSession session) throws JsonProcessingException {
        ResponseVO responseVO = new ResponseVO();
        List<User> userList = userService.getUserListByUsername(user.getUsername());
        if (!userList.isEmpty()) {
            User user1 = userList.get(0);
            if (user1.getPassword().equals(user.getPassword())) {
                responseVO.setCode(200);
                responseVO.setInfo("密码验证成功！");
                responseVO.setStatus("success");
                responseVO.setData(user1);
                // 将用户信息加密为jwt
                String jwt = JwtTools.generateToken(user1);
                System.out.println("生成的jwt：" + jwt);
                responseVO.setData(jwt);

            }
        }else {
            responseVO.setCode(403);
            responseVO.setInfo("密码验证失败！");
            responseVO.setStatus("success");
        }
        return responseVO;

    }

    @PostMapping("/user/getUserInfo")
    public ResponseVO getUserInfo(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ResponseVO responseVO = new ResponseVO();

        // 从请求头中获取 JWT，并验证
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7).replaceAll("^\"|\"$", ""); // 去除首尾双引号
//            System.out.println("获取的jwt:" + token);
            try {
                // 验证 JWT
                Claims claims = JwtTools.validateToken(token);

                // 如果 JWT 有效，解密 JWT 里存储的用户信息并且返回
                String userJson = claims.getSubject();
                JsonNode userNode = objectMapper.readTree(userJson);

                // 可以在这里进一步处理 userNode，例如转换为特定的对象

                // 设置响应信息
                responseVO.setCode(200);
                responseVO.setInfo("User info retrieved successfully");
                responseVO.setData(userNode); // 可以根据实际情况调整数据结构
            } catch (RuntimeException e) {
                // 如果 JWT 无效，返回未授权状态
                responseVO.setCode(403);
                responseVO.setInfo(e.getMessage());
            }
        } else {
            // 如果请求头中没有 JWT，返回未授权状态
            responseVO.setCode(403);
            responseVO.setInfo("Missing JWT in Authorization header");
        }

        return responseVO;
    }
    
    
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
    
    
    @GetMapping("/user/getUserListByUsername")
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

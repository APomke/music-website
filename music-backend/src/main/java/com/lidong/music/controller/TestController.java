package com.lidong.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lidong.music.annotation.Globallnterceptor;
import com.lidong.music.annotation.VerifyParam;
import com.lidong.music.enums.ResponseCodeEnum;
import com.lidong.music.enums.VerifyRegexEnum;
import com.lidong.music.exception.BusinessException;
import com.lidong.music.entity.simple;
import com.lidong.music.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SimpleService simpleService;

    @GetMapping("/test")
    public String test() {
        return "test successful";
    }

    @GetMapping("/queryTest")
    public String queryTest() {
        LambdaQueryWrapper<simple> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(simple::getAge,"18");
        simple one = simpleService.getOne(lambdaQueryWrapper);
        return one.toString();
    }

    @GetMapping("/updateSimple")
    public String updateSimple() {
        Integer i = simpleService.updateSimple("张三");
        if (i == 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @GetMapping("/passwordTest")
    @Globallnterceptor(checkParams = true,checkLogin = false)
    public String passwordTest(@VerifyParam(required = true,regex = VerifyRegexEnum.PASSWORD) String password) {
//        System.out.println(password);
        return "密码校验成功！";
    }

    @GetMapping("/errorTest")
    public String errorTest() {
        throw new BusinessException(ResponseCodeEnum.CODE_600);
    }
}

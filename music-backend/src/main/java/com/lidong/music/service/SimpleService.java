package com.lidong.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lidong.music.entity.simple;


public interface SimpleService extends IService<simple> {
    // 不使用mybatis-plus生成 自定义接口
    Integer updateSimple(String name);
}

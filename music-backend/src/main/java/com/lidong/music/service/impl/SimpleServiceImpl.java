package com.lidong.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lidong.music.mapper.SimpleMapper;
import com.lidong.music.entity.simple;
import com.lidong.music.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleServiceImpl extends ServiceImpl<SimpleMapper, simple> implements SimpleService {

    @Autowired
    private SimpleMapper testMapper;
    @Override
    public Integer updateSimple(String name) {
        return testMapper.updateSimple(name);
    }
}
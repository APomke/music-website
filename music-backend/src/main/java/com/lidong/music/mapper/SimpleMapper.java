package com.lidong.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lidong.music.entity.simple;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface SimpleMapper extends BaseMapper<simple> {
    // 不使用mybatis-plus生成 自定义接口
    Integer updateSimple(String name);

}

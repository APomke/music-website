package com.lidong.music.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {
    public static final String getNowDate(){
        // 获取当前日期时间
        Date currentDate = new Date();

        // 定义日期时间格式化对象
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 将当前日期时间格式化为字符串
        return dateFormat.format(currentDate);
    }
}

package com.lidong.music.utils;

import com.lidong.music.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtils {

    /**
     * 正则校验参数的合格性
     * @param regs 正则表达式
     * @param value 要验证的值
     * @return  校验结果
     */
    public static Boolean verify(String regs, String value){
        Pattern pattern = Pattern.compile(regs);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static Boolean verify(VerifyRegexEnum verifyRegexEnum, String value){
        return verify(verifyRegexEnum.getRegex(), value);
    }

}
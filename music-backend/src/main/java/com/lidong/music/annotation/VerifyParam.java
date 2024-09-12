package com.lidong.music.annotation;

import com.lidong.music.enums.VerifyRegexEnum;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.FIELD})//该注解是在方法前面加的
@Retention(RetentionPolicy.RUNTIME)//在执行时
public @interface VerifyParam {
    int max() default -1;
    int min() default -1;

    boolean required() default false;

    VerifyRegexEnum regex() default VerifyRegexEnum.NO;
}

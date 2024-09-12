package com.lidong.music.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

@Target({ElementType.METHOD})//该注解是在方法前面加的
@Retention(RetentionPolicy.RUNTIME)//在执行时
@Documented
@Mapping
public @interface Globallnterceptor {
    boolean checkParams() default false; //是否检验参数 默认false
    //是否登入拦截
    boolean checkLogin() default true;
    //校验是否是超级管理员
    boolean checkAdmin() default false;

}

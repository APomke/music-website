package com.lidong.music.enums;

public enum VerifyRegexEnum {
    NO("","不校验"),
    EMAIL("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$","邮箱"),
    PASSWORD("^(?=.*\\d)(?=.*[a-zA-Z])[\\da-zA-Z~!@#$号^&* ]{8,}$","只能是数字，字母，特殊字符 8-18位");

    private String regex;
    private String desc;

    VerifyRegexEnum(String regex, String desc) {
        this.regex = regex;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getRegex() {
        return regex;
    }
}

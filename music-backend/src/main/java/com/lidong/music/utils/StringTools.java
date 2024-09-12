package com.lidong.music.utils;

import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class StringTools {
    //生成随机数字
    public static final String getRandomNum(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    //生成userid
    public static final String getRandomUUID() {
        //生成一个16位长度的uuid
        return  UUID.randomUUID().toString().replaceAll("-", "");
    }
    private static final String key = "3112520587";
    //AES128非对称加密密码
    public static String encryptByAES128(String input) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    //AES128非对称解密密码
    public static String decryptByAES128(String encryptedInput) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedInput));
        return new String(decryptedBytes);
    }
    //MD5加密密码
    public static String encryptByMd5(String input) throws Exception {
        if (input == null) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    //判断路路经是否有非法字符
    public static boolean pathIsOk(String path) {
        if (path == null) {
            return false;
        }
        if (path.contains("../") || path.contains("..\\")) {
            return false;
        }
        return true;
    }
}

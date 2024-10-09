package com.lidong.music.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lidong.music.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

public class JwtTools {

    // jwt加密密钥
    private static final String FIXED_SECRET_STRING = "c2FtcGxlLXNlY3JldC10cmFuc3BhcmVuY2UtdG90MjU2";
    private static final Key SECRET = Keys.hmacShaKeyFor(Base64.getDecoder().decode(FIXED_SECRET_STRING));
    // jwt有效期，单位为毫秒
    private static final long JWT_TOKEN_VALIDITY = 1440 * 60 * 1000; // 24 小时
    public static final ObjectMapper objectMapper = new ObjectMapper();

    // 生成jwt
    public static String generateToken(User user) throws JsonProcessingException {
        Date now = new Date();
        Date validity = new Date(now.getTime() + JWT_TOKEN_VALIDITY);
        String userJson = objectMapper.writeValueAsString(user); // 将 User 对象转换为 JSON 字符串
        return Jwts.builder()
                .setSubject(userJson)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SECRET, SignatureAlgorithm.HS256)
                .compact();
    }

    // 解密jwt
    public static Claims validateToken(String token) {
        try {
            // 使用相同的密钥来验证 JWT 的签名
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("JWT 已过期", ex);
        } catch (UnsupportedJwtException ex) {
            throw new RuntimeException("JWT 格式不支持", ex);
        } catch (MalformedJwtException ex) {
            throw new RuntimeException("JWT 格式不正确", ex);
        } catch (SignatureException ex) {
            throw new RuntimeException("JWT 签名错误", ex);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("JWT 数据错误", ex);
        }
    }
}
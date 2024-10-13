package com.lidong.music.utils;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.lidong.music.config.OssConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class OssTools {

    private static final Logger logger = LoggerFactory.getLogger(OssTools.class);

    private final OssConfig ossConfig;
    private final OSSClient ossClient;

    @Autowired
    public OssTools(OssConfig ossConfig, OSSClient ossClient) {
        this.ossConfig = ossConfig;
        this.ossClient = ossClient;
    }

    public JSONObject getSignature(String title) {
        JSONObject returnJsonObject = null;
        try {
            String dir = title;
            String host = "https://" + ossConfig.getBucket() + "." + ossConfig.getEndpoint();
            // 失效时间（默认300s）
            long expireEndTime = System.currentTimeMillis() + ossConfig.getExpireTime() * 1000;
            Date expiration = new Date(expireEndTime);

            PolicyConditions policyConds = new PolicyConditions();
            // 策略限制上传文件大小
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            // 策略限制上传文件目录
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<>();
            respMap.put("accessid", ossConfig.getAccessId());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

            returnJsonObject = JSONObject.parseObject(JSONObject.toJSONString(respMap));
        } catch (Exception e) {
            logger.error("Failed to generate signature: {}", e.getMessage(), e);
        } finally {
            // 关闭OSSClient
            // 由于OSSClient由Spring管理，这里不需要手动关闭
        }
        return returnJsonObject;
    }
}
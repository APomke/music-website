package com.lidong.music.config;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssConfig {

    private String bucket;
    private String endpoint;
    private long expireTime;
    private String accessId;
    private String accessKey;

    // Getters and Setters
    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Bean
    public OSSClient ossClient() {
        OSSClient ossClient = (OSSClient) new OSSClientBuilder().build(endpoint, accessId, accessKey);
        // 在关闭客户端之前，确保在不再需要时调用 shutdown 方法
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }));
        return ossClient;
    }
}
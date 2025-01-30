package com.ellaskitchen.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
//    管理员工端生成jwt令牌的相关配置
    private String adminSecretKey;
    private long Ttl;
    private String adminTokenName;

//    管理用户端生成jwt令牌的相关配置
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

}

package com.dxxt.im.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public static String zookeeperUrl;
    public static String zookeeperPath;
    public static String imServerIp;
    public static int imServerPort;
    public static String redisIp;
    public static String jwtSecret;
    public static String redisChannel;

    @Value("${im.zookeeper.url}")
    public void setZookeeperUrl(String zookeeperUrl) {
        AppConfig.zookeeperUrl = zookeeperUrl;
    }

    @Value("${im.zookeeper.path}")
    public void setZookeeperPath(String zookeeperPath) {
        AppConfig.zookeeperPath = zookeeperPath;
    }

    @Value("${im.server.ip}")
    public void setImServerIp(String imServerIp) {
        AppConfig.imServerIp = imServerIp;
    }

    @Value("${im.server.port}")
    public void setImServerPort(int imServerPort) {
        AppConfig.imServerPort = imServerPort;
    }

    @Value("${im.redis.ip}")
    public void setRedisIp(String redisIp) {
        AppConfig.redisIp = redisIp;
    }

    @Value("${im.jwt.secret}")
    public void setJwtSecret(String jwtSecret) {
        AppConfig.jwtSecret = jwtSecret;
    }

    @Value("${im.redis.channel}")
    public void setRedisChannel(String redisChannel) {
        AppConfig.redisChannel = redisChannel;
    }

}

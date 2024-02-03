package com.juli.springbootinit.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {
    private Integer database;

    private String host;

    private Integer port;

    private String password;
    @Bean
    public RedissonClient redissonClient(){
        // 1. Create config object
        Config config = new Config();
        config.useSingleServer()
                .setDatabase(database)
//                .setAddress("redis://127.0.0.1:6379");
                .setAddress("redis://"+  host + ":" + port);
//                .setPassword(password);
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}

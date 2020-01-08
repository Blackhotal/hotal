package com.zxy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * HotalApplication
 * @author black
 */
@SpringBootApplication
@MapperScan("com.zxy.dao")
public class HotalApplication {



    public static void main(String[] args) {
        SpringApplication.run(HotalApplication.class, args);
    }

}

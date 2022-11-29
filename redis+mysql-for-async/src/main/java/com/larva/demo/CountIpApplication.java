package com.larva.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 郭景伟Larva
 * @Date 2022-10-25 9:53
 * @describe:
 **/

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class CountIpApplication {
    public static void main(String[] args) {
        SpringApplication.run(CountIpApplication.class,args);
    }
}

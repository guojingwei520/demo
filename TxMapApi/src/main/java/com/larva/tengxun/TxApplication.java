package com.larva.tengxun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-07 8:42
 * @describe:
 **/
@SpringBootApplication
@EnableSwagger2
@ServletComponentScan
public class TxApplication {
    public static void main(String[] args) {
        SpringApplication.run(TxApplication.class,args);
    }
}

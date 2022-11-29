package com.gjw.action;

import com.gjw.action.servics.ElasticsearchJdApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-01 16:10
 * @describe:
 **/
@SpringBootApplication
public class ESsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ESsearchApplication.class,args);
    }
}

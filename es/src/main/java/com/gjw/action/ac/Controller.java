package com.gjw.action.ac;

import com.gjw.action.servics.ElasticsearchJdApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-01 16:35
 * @describe:
 **/
@RestController
@RequestMapping("v1")
public class Controller {
    @Autowired
    ElasticsearchJdApplicationTests elas;
    //查是否存在es索引
    @RequestMapping("test")
    public Boolean test1(String index) throws IOException {
        return elas.testExistIndex(index);
    }
    //查询索引内容
    @RequestMapping("test2")
    public Object test1() throws IOException {
        return elas.testSearch();
    }

    //查询索引内容
    @RequestMapping("tests")
    public Object test2(Object ...objects) throws IOException {
        return elas.testSearch3(objects);
    }
}

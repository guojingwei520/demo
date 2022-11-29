package com.larva.demo.api;

import com.larva.demo.mapper.RedisToMysql;
import com.larva.demo.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 郭景伟Larva
 * @Date 2022-10-25 9:58
 * @describe:
 **/
@RestController
@RequestMapping("api")
public class Api {
    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    RedisToMysql redisToMysql;
    //向redis中插入个人信息：ip,phone
    @RequestMapping(value = "/notoken/insertip", method= RequestMethod.POST)
    public Object insertip(
            @RequestBody Object data,
            HttpServletResponse res) throws Exception{
        return redisService.saveIpInfo(data);
    }
}

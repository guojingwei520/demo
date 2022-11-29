package com.larva.demo.service.impl;

import com.larva.demo.mapper.RedisToMysql;
import com.larva.demo.service.mysqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @Date 2022-10-12 10:40
 **/
@Service
public class mysqlServiceImpl implements mysqlService {
    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    RedisToMysql redisToMysql;

    @Override
    public boolean saveIp(Object data) {
        return redisToMysql.saveIp(data);
    }

    @Override
    public boolean clearIp() {
        return redisToMysql.clearIp();
    }
}

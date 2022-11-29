package com.larva.demo.service.impl;

import com.larva.demo.service.redisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Lucas
 * @Date 2022-10-12 10:26
 **/
@Service
public class RedisServiceImpl implements redisService {
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public Object saveIpInfo(Object data) {
        Map map = (Map) data;
        boolean Y=this.getIpInfo(map.get("ip").toString());
        if (Y){
            return "已存在";
        }else {
            redisTemplate.opsForValue().set(map.get("ip"),map.get("phone"));
            return true;
        }
    }

    @Override
    public boolean getIpInfo(String ip) {
        Object Y = redisTemplate.opsForValue().get(ip);
        if (Y==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object getAllIpInfo() {
        Set<String> keys = redisTemplate.keys("*");
        List<Object> ips = new ArrayList<Object>();
        for (String key:keys){
            HashMap map = new HashMap();
            String value= (redisTemplate.boundValueOps(key).get()==null)?null:redisTemplate.boundValueOps(key).get().toString();
            map.put("ip",key);
            map.put("phone",value);
            ips.add(map);
        }
        return ips;
    }

    @Override
    public boolean clearRedis(String ip) {
        return redisTemplate.delete(ip);
    }

    @Override
    public Set<String> getAllkey() {
        return redisTemplate.keys("*");
    }
}

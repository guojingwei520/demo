package com.larva.demo.task;

import com.larva.demo.mapper.RedisToMysql;
import com.larva.demo.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ScheTask {
    @Autowired
    RedisToMysql redisToMysql;
    @Autowired
    RedisServiceImpl redisService;
    @Scheduled(cron = "0 0/2 * * * ?")//表示每隔5s执行一次从左到右依次是秒分时天月周年0表示不执行，站位用。。。
    public void task() {
        System.out.println("开始执行定时任务...");
        Map rs = new HashMap();
        rs.put("ips",redisService.getAllIpInfo());
        Set<String> ips=redisService.getAllkey();
        //将 Redis 里的访问数据同步到数据库里
        if (((List)rs.get("ips")).size()>0){
            redisToMysql.saveIp(rs);
            for (Object ip : ips){
                redisService.clearRedis(ip.toString());
                System.out.println("同步成功！");
            }
        }else {
            System.out.println("无可同步数据");
        }
    }
}
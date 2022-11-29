package com.larva.demo.service;

import java.util.List;
import java.util.Set;

/**
 * @author Lucas
 * @Date 2022-10-12 10:25
 **/
public interface redisService {
    /*保存用户访问ip信息*/
    Object saveIpInfo(Object data);

    /*查询某ip是否存在redis*/
    boolean getIpInfo(String ip);

    /*查询redis所有ip*/
    Object getAllIpInfo();
    /*清空redis*/
    boolean clearRedis(String ip);

    Set<String> getAllkey();
}

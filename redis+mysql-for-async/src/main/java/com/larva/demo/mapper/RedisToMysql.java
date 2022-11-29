package com.larva.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 郭景伟
 * @Date 2022-10-12 10:23
 **/
@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface RedisToMysql {
    /*保存来自redis的ip*/
    boolean saveIp(Object data);
    /*清空mysql里旧的ip*/
    boolean clearIp();

}

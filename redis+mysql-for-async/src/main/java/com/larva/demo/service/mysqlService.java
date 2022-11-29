package com.larva.demo.service;

/**
 * @author Lucas
 * @Date 2022-10-12 10:39
 **/
public interface mysqlService {
/*保存来自redis的ip*/
    boolean saveIp(Object data);
/*清空mysql里旧的ip*/
    boolean clearIp();
}

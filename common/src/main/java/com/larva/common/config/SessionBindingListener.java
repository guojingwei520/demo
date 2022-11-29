package com.larva.common.config;

import com.larva.common.utils.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-24 14:28
 * @describe: 监测ip在线数量
 **/
@WebListener //这个注解的作用是启动监听
public class SessionBindingListener implements HttpSessionBindingListener{
    private static Logger log = LoggerFactory.getLogger(SessionBindingListener.class);

    public SessionBindingListener() {

    }
    private Vector<Hashtable> container = new Vector<Hashtable>();
    public int getCount() {
        container.trimToSize();// 调整Vector listenUser的容量为Vector listenUser的大小
        return container.capacity();// 返回listenUser的容量
    }
    public boolean existIp(String ip) {
        container.trimToSize();
        boolean existUser = false;
        for (int i = 0; i < container.capacity(); i++) {
            if (ip.equals(container.get(i).get("ip").toString())) {
                existUser = true;
                break;
            }
        }
        return existUser;
    }
    public boolean deleteIp(String ip) {
        container.trimToSize();
        if (existIp(ip)) {
            int currUserIndex = -1;
            for (int i = 0; i < container.capacity(); i++) {
                if (ip.equals(container.get(i).get("ip").toString())) {
                    currUserIndex = i;
                    break;
                }
            }
            if (currUserIndex != -1) {
                container.remove(currUserIndex);
                container.trimToSize();
                return true;
            }
        }
        return false;
    }
    public Vector<Hashtable> getOnLineUser() {
        return container;
    }
    public void valueBound(HttpSessionBindingEvent e) {
        container.trimToSize();
        log.info("ip:"+e.getName() + "    登入到系统... " + (new Date()));
        if (getCount()==0){
            log.info("当前在线人数为： " + 1+"人");
        }else {
            log.info("当前在线人数为： " +getCount()+"人");
        }
    }
    public void valueUnbound(HttpSessionBindingEvent e) {
        container.trimToSize();
        String ip = e.getName();
        deleteIp(ip);
        log.info(ip + "    退出系统 " + (new Date()));
        log.info("      当前在线用户数为：" + getCount()+"人");
    }
    //传入request将ip信息保存到容器内。
    public  boolean addIpSession(HttpServletRequest request){
        String ip= IpUtil.getIpAddr(request);
        Hashtable map = new Hashtable();
        map.put("ip",ip);
        //判断是否已经存在ip，已经存在则刷新serssion时间。
        if (existIp(ip)){
            request.getSession().setAttribute(ip,SessionBindingListener.this);
            /*默认过期时间30分钟*/
            request.getSession().setMaxInactiveInterval(3);
            return true;
        }
        request.getSession().setAttribute(ip,SessionBindingListener.this);
        /*默认过期时间30分钟*/
        request.getSession().setMaxInactiveInterval(30*60);
        Vector<Hashtable> vt = getOnLineUser();
        vt.add(map);
        return true;
    }
}

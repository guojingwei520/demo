package com.Larva.demo.service.impl;

import com.Larva.demo.service.IService;
import com.larva.common.config.SessionBindingListener;
import com.larva.common.utils.IpUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-24 14:33
 * @describe:
 **/
@Service
public class ServiceImpl implements IService {
    public static SessionBindingListener listener=new SessionBindingListener();
    @Override
    public boolean Login(HttpServletRequest request) {
        try{
        listener.addIpSession(request);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public int getOnlinePeople() {
        int count = listener.getCount();
        return count;
    }

    @Override
    public boolean outLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取退出登录的ip
        String ip=IpUtil.getIpAddr(request);
        request.getSession().removeAttribute(ip);
        return true;
    }
}

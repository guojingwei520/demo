package com.Larva.demo.api;

import com.Larva.demo.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-24 14:31
 * @describe:
 **/
@RestController
@RequestMapping("api")
public class Api {
    @Autowired
    ServiceImpl service;
    @RequestMapping("api1")
    public boolean Login(HttpServletRequest request){
        return this.service.Login(request);
    }

    @RequestMapping("api2")
    public int getPeople(){
        return this.service.getOnlinePeople();
    }

    @RequestMapping("api3")
    public boolean outLogin(){
        return this.service.outLogin();
    }
}

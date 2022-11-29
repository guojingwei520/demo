package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @Date 2022-10-24 17:41
 **/
@Service
public class AsynchronousService {
    @Autowired
    AsyncImpl async;
    public Object springAsynchronousMethod()throws Exception{
        Long b = System.currentTimeMillis();
        this.async.async();
        Long e1 = System.currentTimeMillis();
        this.async.asyncs();
        Long e2 = System.currentTimeMillis();
        Long aa=e1-b;
        Long bb=e2-b;
        return aa+"***"+bb;
    }
}

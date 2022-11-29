package com.example.demo.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Lucas
 * @Date 2022-10-24 17:40
 **/
@Service
public class AsyncImpl{
    @Async
    public void async() throws Exception{
        Thread.sleep(1000);
        System.out.println("do it 1");
    }

    @Async
    public void asyncs() throws Exception{
        Thread.sleep(1200);
        System.out.println("do it 2");
    }
}

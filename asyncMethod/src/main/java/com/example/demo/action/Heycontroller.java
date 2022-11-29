package com.example.demo.action;

import com.example.demo.service.impl.AsynchronousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lucas
 * @Date 2022-10-12 11:18
 **/
@RestController
@RequestMapping("v1")
public class Heycontroller {
    @Autowired
    AsynchronousService asynchronousService;
    //异步方法实现
    @RequestMapping(value = "/notoken/sync", method= RequestMethod.GET)
    public ResponseEntity<?> Async() throws Exception{
        return ResponseEntity.ok(this.asynchronousService.springAsynchronousMethod());
    }
}

package com.larva.demo.api;

import com.larva.demo.mapper.Iuse;
import com.larva.demo.service.lamdaImpl;
import com.larva.demo.service.useServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 郭景伟Larva
 * @Date 2022-10-25 18:07
 * @describe:
 **/
@RestController
@RequestMapping("v1")
public class D {
    @Autowired
    useServiceImpl useService;

    @Autowired
    lamdaImpl lamda;

    @RequestMapping("/notoken/testuse")
    public Object testuse(Iuse iuse){
        return useService.use(iuse);
    }

    @RequestMapping("/notoken/testlamda")
    public Object testlam(int a,int b){
        return lamda.lam(a,b);
    }
}

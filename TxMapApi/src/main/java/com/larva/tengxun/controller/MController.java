package com.larva.tengxun.controller;

import com.larva.tengxun.service.impl.MapServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-07 8:56
 * @describe:
 **/
@RestController
public class MController {
    @Autowired
    MapServiceImpl mapService;
    @ApiOperation(value = "测试Map...")
    @RequestMapping(value = "/key/request",method= RequestMethod.GET)
    public Object apit(@RequestBody Object data, HttpServletRequest request) throws Exception {
        Map map = (Map) data;
        return mapService.getMap(map.get("code").toString(),request.getAttribute("param").toString());
    }
}

package com.Larva.demo.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-24 14:33
 * @describe:
 **/
public interface IService {
    boolean Login(HttpServletRequest request);
    int getOnlinePeople();
    boolean outLogin();
}

package com.larva.tengxun.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-07 10:26
 * @describe:
 **/
@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface Imap {

    String geturl(String code);
}

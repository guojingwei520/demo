package com.larva.tengxun.service.impl;

import com.larva.tengxun.mapper.Imap;
import com.larva.tengxun.service.IMapService;
import com.larva.tengxun.utils.TXMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-07 9:02
 * @describe:
 **/
@Service
public class MapServiceImpl implements IMapService {
    @Autowired
    TXMapUtil txMapUtil;
    @Autowired
    Imap imap;
    @Override
    public Object getMap(String code,String param) throws Exception  {
        return this.txMapUtil
                .getMapInfo(this.imap.geturl(code) + param);
    }
}

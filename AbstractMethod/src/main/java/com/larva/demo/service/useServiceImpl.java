package com.larva.demo.service;

import com.larva.demo.mapper.Iuse;
import org.springframework.stereotype.Service;

/**
 * @author 郭景伟Larva
 * @Date 2022-10-27 9:23
 * @describe:
 **/
@Service
public class useServiceImpl implements Iuse{
    @Override
    public boolean usemouse() {
        return true;
    }

    @Override
    public boolean usekeyborad() {
        return true;
    }
    @Override
    public Object use(Iuse iuse){
        if (iuse instanceof Iuse){
            return "use mouse"+this.usemouse();
        }else {
            return "nothing do ";
        }
    }
}

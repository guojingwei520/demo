package com.gjw.action.servics;

import java.io.IOException;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-01 16:37
 * @describe:
 **/
public interface iservice {
    void testCreateIndex() throws IOException;
    boolean testExistIndex(String index) throws IOException;
    Object testSearch() throws IOException;
    Object testSearch3(Object ...objects) throws IOException;
}

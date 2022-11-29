package com.larva.tengxun.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-07 8:58
 * @describe:
 **/
@Component
public class TXMapUtil {
 /*   @Value("${tengxun.map.key}")
    private String key;*/
    /**
     * 读取
     * @param rd
     * @return
     * @throws IOException
     */
    private static String readAll(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * 创建链接
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public JSONObject getMapInfo(String url) throws IOException, JSONException {
        //String uri =url+"&key="+key;
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = JSONObject.parseObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}

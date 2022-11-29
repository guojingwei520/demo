package com.larva.common.basic.json;

import com.larva.common.basic.json.reader.BasicJsonReader;
import com.larva.common.basic.json.writer.BasicJsonwriter;

import java.util.List;
import java.util.Map;

/**
 * @author 郭景伟Larva
 * @Date 2022-11-24 10:53
 * @describe: json serialization and deserialization library.
 **/
public class BasicJsonTool {

    private static final BasicJsonReader basicJsonReader = new BasicJsonReader();
    private static final BasicJsonwriter basicJsonwriter = new BasicJsonwriter();

    /**
     * object to json
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return basicJsonwriter.toJson(object);
    }

    /**
     * json to List<Object>
     *
     * @param json
     * @return
     */
    public static List<Object> parseList(String json) {
        return basicJsonReader.parseList(json);
    }

    /**
     * json to Map<String, Object>
     *
     * @param json
     * @return
     */
    public static Map<String, Object> parseMap(String json) {
        return basicJsonReader.parseMap(json);
    }

}

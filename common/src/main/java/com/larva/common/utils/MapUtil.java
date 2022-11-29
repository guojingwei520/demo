package com.larva.common.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author 郭景伟Larva
 * @Date 2022-11-24 14:15
 * @describe:
 **/
@SuppressWarnings({"rawtypes", "unchecked"})
@Component
public class MapUtil {
    Map map = null;
    List items = null;

    public MapUtil() {
        this.map = new HashMap();
        this.items = new ArrayList();
    }

    public static MapUtil Cr() {
        MapUtil m = new MapUtil();
        return m;
    }

    public MapUtil add(Object key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public MapUtil addMap(Map m) {
        if (map != null) {
            this.map.putAll(m);
        }
        return this;
    }

    public MapUtil addList(Object obj) {
        this.items.add(obj);
        return this;
    }

    public MapUtil addLists(Collection c) {
        this.items.addAll(c);
        return this;
    }

    public MapUtil remove(Object key) {
        if (key != null) {
            this.map.remove(key);
        }
        return this;
    }

    public MapUtil clear() {
        this.map.clear();
        return this;
    }

    public MapUtil clearList() {
        this.items.clear();
        return this;
    }

    public Map toMap() {
        return this.map;
    }

    public Map toMap(String json) {
        this.map.clear();
        this.map.putAll((Map) MapUtil.toObject(json));
        return this.map;
    }

    public static Map toEmptyMap() {
        return MapUtil.Cr().toMap();
    }

    //Feature.OrderedField 保持字段顺序不变
    public static Object toObject(String json) {
        return JSON.parse(json, Feature.OrderedField);
    }

    public List toList() {
        return this.items;
    }

    public List toList(String json) {
        return (List) MapUtil.toObject(json);
    }

    public static List toEmptyList() {
        return MapUtil.Cr().toList();
    }


    public String toJson() {
        return JSON.toJSONString(this.map);
    }

    public String toListJson() {
        return JSON.toJSONString(this.items);
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return JSON.toJSONString(obj);
        }
    }

    public Object get(Object key) {
        return this.map.get(key);
    }

    public Set getMapKeys() {
        return this.map.keySet();
    }

    public boolean isMapEmpty() {
        return this.map.isEmpty();
    }

    public boolean isListEmpty() {
        return this.items.isEmpty();
    }

    public Integer getInteger(Object key) {
        return this.getInteger(this.map, key);
    }

    public Integer getInteger(Map m, Object key) {
        if (m.containsKey(key)) {
            Object o = m.get(key);
            if (o instanceof Integer) {
                return (Integer) o;
            } else {
                try {
                    return Integer.valueOf(o.toString());
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public Double getDouble(Object key) {
        return this.getDouble(this.map, key);
    }

    public Double getDouble(Map m, Object key) {
        if (m.containsKey(key)) {
            Object o = m.get(key);
            if (o instanceof Double) {
                return (Double) o;
            } else {
                try {
                    return Double.valueOf(o.toString());
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            if (map.get(field.getName()) != null) {
                field.set(obj, map.get(field.getName()));
            }
        }
        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    public static void MapCopy(Map source, Map dest) throws Exception {
        if (source == null) {
            return;
        }
        if (dest == null) {
            dest = MapUtil.toEmptyMap();
        }
        Iterator it = source.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            dest.put(key, source.get(key));
        }
    }
}

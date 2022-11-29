package com.larva.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 郭景伟Larva
 * @Date 2022-10-25 14:37
 * @describe:
 **/
@SpringBootTest
class CountIpApplicationTests {
    @Test
    void test1() {
        Map map = new HashMap<>();
        map.put("a", 1);
        Map map2 = new HashMap<>();
        map2.put("b", map.get("c"));
        map2.put("d", map.get("a"));
        System.out.println(map2);
        System.out.println(map2.get("b"));
        System.out.println(map2.get("d"));
    }
}

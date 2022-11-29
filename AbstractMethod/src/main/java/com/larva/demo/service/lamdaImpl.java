package com.larva.demo.service;

import com.larva.demo.mapper.Ilamda;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 郭景伟Larva
 * @Date 2022-10-27 16:55
 * @describe:
 **/

@Service
public class lamdaImpl implements Ilamda {

    Ilamda lamda = (int a,int b)->a*b;

    @Override
    public Object lam(int a, int b) {
        // 1、转换Stream
        List<String> list = Arrays.asList("java+；", "c++；", "net；");
        list.stream();
        // 2、forEach操作
        list.stream().forEach(System.out::print);
        // 3、map映射，输出 3，4
        IntStream.rangeClosed(2,3).map(x->x*2).forEach(System.out::println);
        // 4、filter过滤
        list.stream().filter(str -> str.contains("t")).forEach(System.out::print);
        // 5、distinct去重
        Integer[] arr = new Integer[]{3, 1, 3, 1, 2,4};
        Stream.of(arr).distinct().forEach(System.out::println);
        // 6、sorted排序
        Stream.of(arr).sorted().forEach(System.out::println);
        // 7、collect转换
        List<String> newList = list.stream().filter(str -> str.contains("；"))
                .collect(Collectors.toList());
        newList.stream().forEach(System.out::print);
        return lamda.lam(a,b);
    }
}

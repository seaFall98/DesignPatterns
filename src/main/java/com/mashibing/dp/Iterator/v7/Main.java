package com.mashibing.dp.Iterator.v7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * v1:构建一个容器，可以添加对象
 * v2:用链表来实现一个容器
 * v3:添加容器的共同接口，实现容器的替换
 * v4:如何对容器遍历呢？
 * v4:用一种统一的遍历方式，要求每一个容器都要提供Iterator的实现类
 *    作业：实现LinkedList的Iterator
 * v6:JDK的容器实现
 * v7:实现泛型版本
 */

public class Main {
    public static void main(String[] args) {
        Collection_<String> list = new ArrayList_<>();
        for(int i=0; i<15; i++) {
            list.add(new String("s" + i));
        }
        System.out.println(list.size());

        //这个接口的调用方式：
        Iterator_<String> it = list.iterator();
        while(it.hasNext()) {
            String o = it.next();
            System.out.println(o);
        }

        System.out.println("=====================");

        Collection<String> list2 = new ArrayList<>();
        for(int i=0; i<15; i++) {
            list2.add(new String("s" + i));
        }
        System.out.println(list2.size());

        //这个接口的调用方式：
        Iterator<String> it2 = list2.iterator();
        while(it.hasNext()) {
            String o = it2.next();
            System.out.println(o);
        }

    }
}



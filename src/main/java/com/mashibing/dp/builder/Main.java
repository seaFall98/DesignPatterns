package com.mashibing.dp.builder;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        TerrainBuilder builder = new ComplexTerrainBuilder();
        Terrain t = builder.buildFort().buildMine().buildWall().build();
        //new Terrain(Wall w, Fort f, Mine m)
        //Effective Java

        //注意调用方法 new Person类里的静态内部类PersonBuilder
        Person p = new Person.PersonBuilder()
                .basicInfo(1, "zhangsan", 18)
                //.score(20) 不需要的直接注释掉
                .weight(200)
                //.loc("bj", "23")
                .build();


        System.out.println("=========================");

        //String Reader
        Reader reader2 = new StringReader("reader");

        // System.in 是 InputStream（字节流）
        InputStream byteStream = System.in;

        // 使用 InputStreamReader 适配为 Reader（字符流）
        Reader charStream = new InputStreamReader(byteStream);

        // 进一步包装为 BufferedReader（装饰器模式）
        BufferedReader reader = new BufferedReader(charStream);

        System.out.print("请输入内容：");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("您输入的是：" + input);

    }
}

package com.mashibing.dp.builder;

//适合builder模式的情况：对象属性非常多，比如20个，你不可能每次都new(20个参数)吧，而且有的需要传，有的是空值，有的还需要额外的处理
public class Person {
    int id;
    String name;
    int age;
    double weight;
    int score;
    Location loc;

    //私有化构造函数
    private Person() {}

    //静态内部类
    public static class PersonBuilder {
        Person p = new Person();

        //有的属性是一定有的，可以放在一起
        public PersonBuilder basicInfo(int id, String name, int age) {
            p.id = id;
            p.name = name;
            p.age = age;
            return this;
        }
        //有的属性需要就传，不需要就不传
        public PersonBuilder weight(double weight) {
            p.weight = weight;
            return this;
        }

        public PersonBuilder score(int score) {
            p.score = score;
            return this;
        }
        //有的属性偏复杂，比如是一个类
        public PersonBuilder loc(String street, String roomNo) {
            p.loc = new Location(street, roomNo);
            return this;
        }

        public Person build() {
            return p;
        }
    }
}

class Location {
    String street;
    String roomNo;

    public Location(String street, String roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }
}
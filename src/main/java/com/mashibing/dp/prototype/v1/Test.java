package com.mashibing.dp.prototype.v1;

/**
 * prototype java自带:实现Cloneable接口 重写clone()方法
 * 浅克隆
 */

public class Test {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person();
        Person p2 = (Person)p1.clone();
        System.out.println(p2.age + " " + p2.score);
        System.out.println(p2.loc);

        //true 浅克隆
        System.out.println(p1.loc == p2.loc);
        p1.loc.street = "sh";
        //p1 loc属性的修改 会影响p2 的loc属性 这就可能与实际需求违背了，我们可能希望p1 clone得到的p2是独立的
        System.out.println(p2.loc);

    }
}

//Cloneable接口虽然没有任何内容，但是必需的，去掉Cloneable会报错
class Person implements Cloneable {
    int age = 8;
    int score = 100;

    Location loc = new Location("bj", 22);
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

//Location不实现Cloneable接口 浅克隆 可能有问题
class Location {
    String street;
    int roomNo;

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", roomNo=" + roomNo +
                '}';
    }

    public Location(String street, int roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }
}

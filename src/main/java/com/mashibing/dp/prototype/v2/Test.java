package com.mashibing.dp.prototype.v2;

/**
 * 深克隆的处理
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Person p1 = new Person();
        Person p2 = (Person)p1.clone();
        System.out.println(p2.age + " " + p2.score);
        System.out.println(p2.loc);

        //false 引用类型实现了深克隆
        System.out.println(p1.loc == p2.loc);
        p1.loc.street = "sh";
        //p1的修改不影响p2 实现了独立性
        System.out.println(p2.loc);



    }
}

class Person implements Cloneable {
    int age = 8;
    int score = 100;

    Location loc = new Location("bj", 22);
    @Override
    public Object clone() throws CloneNotSupportedException {
        Person p = (Person)super.clone();
        //loc属性也单独调用clone()方法
        p.loc = (Location)loc.clone();
        return p;
    }
}

//Location类也实现Cloneable接口
class Location implements Cloneable {
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

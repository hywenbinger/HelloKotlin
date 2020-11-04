package com.wayne.kotlin.reflection;

public class ModelJava {

    private int age = 18;
    private String name = "wayne";

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void myFun() {
        System.out.println("age is " + age + ", name is " + name);
    }

    public static void myStatic() {
        System.out.println("static fun");
    }

}

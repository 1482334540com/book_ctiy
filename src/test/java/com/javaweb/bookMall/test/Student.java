package com.javaweb.bookMall.test;



public class Student {

    static int id = 5;
    String name="admin";
     static   String password="root";

     public  void  get(){
         System.out.println("get方法");
     }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

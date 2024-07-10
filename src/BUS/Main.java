/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author huhuh
 */
class Base {
    int value=0 ;
    Base() {
        System.out.println("base");
        addValue();
    }
    void addValue() {
        value+=10;
        System.out.println("xin chào");
    }
    int getvalue() {
        return value;
    }
}

class test extends Base {
    test() {
        System.out.println("test");
        addValue();
    }
    void addValue(){
        value+=20;
        System.out.println("hi");
    }
}

public class Main{
    public static void main(String[] args) {
        test b=new test();
        System.out.println(b.getvalue());
    }
}
package com.j13.jax;

import com.alibaba.fastjson.JSON;

public class JsonTest {

    public static void main(String[] args) {
        A a = JSON.parseObject("{b:1}", A.class);
        System.out.println(a);
        System.out.println(a.getA());
    }

}


class A {
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}

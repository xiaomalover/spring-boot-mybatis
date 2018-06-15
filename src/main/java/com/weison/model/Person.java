package com.weison.model;

import java.io.Serializable;

public class Person implements Serializable {

    /**
     * 所有接承Serializable 的接口都要用这个属性
     * Idea中Setting->Editor->Inspections->Serializable class without 'serialVersionUID' 勾选
     * 光标在类名上，同时按alt+enter自动生成
     */
    private static final long serialVersionUID = 7783474046639979083L;

    //每次jvm引入时执行一次
    static {
        System.out.println("static 块语句");
    }


    private int id;

    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

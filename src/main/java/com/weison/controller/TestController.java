package com.weison.controller;

import com.weison.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping("/serialize")
    public void serialize1() throws FileNotFoundException {
        Person person = new Person(2, "test");
        System.out.println("Person serialize" + person);
        try {
            FileOutputStream fo = new FileOutputStream("Person.txt");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(person);
            oo.flush();
            oo.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping("unserialize")
    public void unserialize() throws  FileNotFoundException {
        try {
            FileInputStream fi = new FileInputStream("Person.txt");
            ObjectInputStream oi = new ObjectInputStream(fi);
            Person p = (Person) oi.readObject();
            oi.close();
            System.out.println(p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @RequestMapping("test-class")
    public void testClass() {
        Integer b = 2;
        Class c1 = b.getClass();
        Class c2 = c1.getSuperclass();
        Class c3 = c2.getSuperclass();
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.forEach(System.out::println);

        Map<String, Object> map = new HashMap<>();
        map.put("hello", arrayList);
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }
}

package com.weison.controller.dataStruct;

import com.weison.model.dataStruct.hashSet.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Set不能有重复元素
 * 无素需要实现 equals(), hashCode()两个方法来确定元素是否相等，和元素位置
 * 1) HashSet的性能总是比TreeSet好(特别是最常用的添加、查询元素等操作)，因为TreeSet需要额外的红黑树算法来维护集合元素的次序。
 * 只有当需要一个保持排序的Set时，才应该使用TreeSet，否则都应该使用HashSet
 * 2) 对于普通的插入、删除操作，LinkedHashSet比HashSet要略慢一点，这是由维护链表所带来的开销造成的。不过，因为有了链表的存在，
 * 遍历LinkedHashSet会更快
 * 3) EnumSet是所有Set实现类中性能最好的，但它只能保存同一个枚举类的枚举值作为集合元素
 * 4) HashSet、TreeSet、EnumSet都是"线程不安全"的，通常可以通过Collections工具类的synchronizedSortedSet方法来"包装"该Set集合。
   SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));
 */
@RequestMapping("/set")
@RestController
public class DataStructSetController
{
    /**
     * 枚举，用于EnumSet
     */
    enum Season
    {
        SPRING, SUMMER, FALL, WINTER
    }

    /**
     * HashSet 元素是无序的，不可重复的
     */
    @RequestMapping("/hash-set")
    public void hashSet ()
    {
        HashSet<Object> books = new HashSet<>();

        //两个A的实例实现equals方法，显示指定元素相同，但是没有实现hashCode方法，所以会插入相同的过元素到不同的位置
        books.add(new A());
        books.add(new A());

        //两个B类实例实现hashCode方法，显示指定元素位置相同，但是没有实现equals方法，所以会在这个位置用链式结构来保存两个对象
        books.add(new B());
        books.add(new B());

        //两个C类实现了hasCode, equals方法，显示指定两都相同，所以，只会存入一个元素
        books.add(new C());
        books.add(new C());

        System.out.println(books);
    }

    /**
     * LinkedHashSet集合也是根据元素的hashCode值来决定元素的存储位置，
     * 但和HashSet不同的是，它同时使用链表维护元素的次序，这样使得元素看起来是以插入的顺序保存的。
     */
    @RequestMapping("/linked-hash-set")
    public void linkedHashSet() {
        LinkedHashSet<String> books1 = new LinkedHashSet<>();
        books1.add("Java");
        books1.add("LittleHann");
        System.out.println(books1);

        //删除 Java
        books1.remove("Java");
        //重新添加 Java
        books1.add("Java");
        System.out.println(books1);
    }

    /**
     * TreeSet是SortedSet接口的实现类，TreeSet可以确保集合元素处于排序状态
     * TreeSet 用红黑树来存储
     * TreeSet 的元素必须实现Comparable 接口，可比较
     */
    @RequestMapping("/tree-set")
    public void treeSet()
    {
        //因为Integer实现了Comparable接口，所以能加到到TreeSet中
        TreeSet<Integer> nums = new TreeSet<>();
        //向TreeSet中添加四个Integer对象
        nums.add(5);
        nums.add(2);
        nums.add(10);
        nums.add(-9);

        //输出集合元素，看到集合元素已经处于排序状态
        System.out.println(nums);
        //输出集合里的第一个元素
        System.out.println(nums.first());
        //输出集合里的最后一个元素
        System.out.println(nums.last());
        //返回小于4的子集，不包含4
        System.out.println(nums.headSet(4));
        //返回大于5的子集，如果Set中包含5，子集中还包含5
        System.out.println(nums.tailSet(5));
        //返回大于等于-9，小于5的子集。
        System.out.println(nums.subSet(-9 , 5));

        //TreeSet的元素必须实现Comparable接口，否则会在运行时抛异常，编译时不会异常
        //以下会异常,因为C类没有实现Comparable接口(要运行下面的代码请先注释掉以下抛异常的代码)
        /*TreeSet<C> ao = new TreeSet<>();
        ao.add(new C());
        System.out.println(ao);*/

        //以下会正常，因为实现了Comparable接口
        TreeSet<D> bo = new TreeSet<>();
        bo.add(new D(17, "lisi"));
        bo.add(new D(15, "zhangsan"));
        System.out.println("First is:" + bo.first().name + ";Last is:" + bo.last().name);

        //以下也不会，因为给TreeSet给了初始的排序规则
        TreeSet<E> co = new TreeSet<>((E ob1, E ob2) -> ob1.age < ob2.age ? -1 : (ob1.age > ob2.age ? 1 : 0));
        co.add(new E(1, "zhangsan"));
        co.add(new E(3, "wangwu"));
        co.add(new E(2, "lisi"));

        System.out.println("First is:" + co.first().name + ";Last is:" + co.last().name);
    }


    /**
     * EnumSet
     */
    @RequestMapping("/enum-set")
    public void enumSet()
    {
        //创建一个EnumSet集合，集合元素就是Season枚举类的全部枚举值
        EnumSet<Season> es1 = EnumSet.allOf(Season.class);
        //输出[SPRING,SUMMER,FALL,WINTER]
        System.out.println(es1);

        //创建一个EnumSet空集合，指定其集合元素是Season类的枚举值。
        EnumSet<Season> es2 = EnumSet.noneOf(Season.class);
        //输出[]
        System.out.println(es2);
        //手动添加两个元素
        es2.add(Season.WINTER);
        es2.add(Season.SPRING);
        //输出[SPRING,WINTER]
        System.out.println(es2);

        //以指定枚举值创建EnumSet集合
        EnumSet<Season> es3 = EnumSet.of(Season.SUMMER , Season.WINTER);
        //输出[SUMMER,WINTER]
        System.out.println(es3);

        EnumSet<Season> es4 = EnumSet.range(Season.SUMMER , Season.WINTER);
        //输出[SUMMER,FALL,WINTER]
        System.out.println(es4);

        //新创建的EnumSet集合的元素和es4集合的元素有相同类型，
        //es5的集合元素 + es4集合元素 = Season枚举类的全部枚举值
        EnumSet<Season> es5 = EnumSet.complementOf(es4);
        //输出[SPRING]
        System.out.println(es5);
    }

    @RequestMapping("/get-element")
    public void getElement()
    {
        //创建一个集合
        Collection<String> books = new HashSet<>();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        books.add("疯狂Android讲义");
        System.out.println(books);

        Iterator it = books.iterator();
        while (it.hasNext()) {
            //it.next()方法返回的数据类型是Object类型，
            //需要强制类型转换
            String book = (String)it.next();
            if (book.equals("疯狂Java讲义")) {
                //从集合中删除上一次next方法返回的元素
                books.remove(book);
            }
            //对book变量赋值，不会改变集合元素本身
            book = "测试字符串";
        }

        System.out.println(books);

        /*
         *  books.forEach((book) -> {
         *      System.out.println(book);
         *  }); 简化如下
         */
        books.forEach(System.out::println);
    }
}

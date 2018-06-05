package com.weison.controller.dataStruct;

import com.weison.model.dataStruct.hashSet.A;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.weison.model.dataStruct.hashSet.F;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1、一般情况下，我们用的最多的是HashMap。HashMap里面存入的键值对在取出的时候是随机的，它根据键的HashCode值存储数据，
 * 根据键可以直接获取它的值，具有很快的访问速度。在Map 中插入、删除和定位元素，HashMap 是最好的选择。
 * 2、TreeMap取出来的是排序后的键值对。但如果您要按自然顺序或自定义顺序遍历键，那么TreeMap会更好。
 * 3、LinkedHashMap 是HashMap的一个子类，如果需要输出的顺序和输入的相同,那么用LinkedHashMap可以实现,
 * 它还可以按读取顺序来排列，像连接池中可以应用。、
 * 4、Set和Map的关系十分密切，java源码就是先实现了HashMap、TreeMap等集合，
 * 然后通过包装一个所有的value都为null的Map集合实现了Set集合类
 */
@RequestMapping("/map")
@RestController
public class DataStructMapController
{
    /**
     * 建议不再用
     */
    @RequestMapping("/hash-table")
    public void hashTable(){
        Hashtable ht = new Hashtable();
        ht.put(new F(60000) , "疯狂Java讲义");
        ht.put(new F(87563) , "轻量级Java EE企业应用实战");
        ht.put(new F(1232) , new A());
        System.out.println(ht);

        //只要两个对象通过equals比较返回true，
        //Hashtable就认为它们是相等的value。
        //由于Hashtable中有一个A对象，
        //它与任何对象通过equals比较都相等，所以下面输出true。
        System.out.println(ht.containsValue("测试字符串"));

        //只要两个F对象的count相等，它们通过equals比较返回true，且hashCode相等
        //Hashtable即认为它们是相同的key，所以下面输出true。
        System.out.println(ht.containsKey(new F(87563)));

        //下面语句可以删除最后一个key-value对
        ht.remove(new F(1232));

        //通过返回Hashtable的所有key组成的Set集合，
        //从而遍历Hashtable每个key-value对
        for (Object key : ht.keySet())
        {
            System.out.print(key + "---->");
            System.out.print(ht.get(key) + "\n");
        }

        //Foreach 遍历
        ht.forEach((key, value) -> {
            System.out.print(key + "---->");
            System.out.print(value + "\n");
        });
    }

    /**
     * 1) HashMap 是一个最常用的Map，它根据键的HashCode值存储数据，根据键可以直接获取它的值，具有很快的访问速度。
     *   遍历时，取得数据的顺序是完全随机的。
     * 2) HashMap最多只允许一条记录的键为Null；允许多条记录的值为 Null。
     * 3) HashMap不支持线程的同步（即任一时刻可以有多个线程同时写HashMap），可能会导致数据的不一致。如果需要同步，
     *   可以用 Collections的synchronizedMap方法使HashMap具有同步的能力，或者使用ConcurrentHashMap
     * 4) Hashtable与 HashMap类似，它继承自Dictionary类。不同的是：它不允许记录的键或者值为空；
     *   它支持线程的同步（即任一时刻只有一个线程能写Hashtable），因此也导致了 Hashtable在写入时会比较慢。
     */
    @RequestMapping("/hash-map")
    public void hashMap()
    {
        HashMap<F, String> hm = new HashMap<>();
        hm.put(new F(60000) , "疯狂Java讲义");
        hm.put(new F(87563) , "轻量级Java EE企业应用实战");
        hm.put(new F(1111) , "cccc");
        System.out.println(hm);

        Iterator it = hm.values().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        for (F key : hm.keySet()) {
            System.out.print(key + "---->");
            System.out.print(hm.get(key) + "\n");
        }

        hm.forEach((key, value) -> {
            System.out.print(key + "---->");
            System.out.print(value + "\n");
        });
    }


    /**
     * 线程安全的ConcurrentHashMap
     */
    @RequestMapping("/concurrent-hash-map")
    public void concurrentHashMap()
    {
        ConcurrentHashMap<F, String> chm = new ConcurrentHashMap<>();
        chm.put(new F(60000) , "疯狂Java讲义");
        chm.put(new F(87563) , "轻量级Java EE企业应用实战");
        chm.put(new F(1111) , "Thread safe");
        System.out.println(chm);

        Iterator it = chm.values().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        for (F key : chm.keySet()) {
            System.out.print(key + "---->");
            System.out.print(chm.get(key) + "\n");
        }

        chm.forEach((key, value) -> {
            System.out.print(key + "---->");
            System.out.print(value + "\n");
        });
    }

    /**
     * 内部维护了双向链表，按加入时的顺序存放
     * LinkedHashMap保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的。
     * 也可以在构造时带参数，按照应用次数排序。
     * 在遍历的时候会比HashMap慢，不过有种情况例外：当HashMap容量很大，实际数据较少时，遍历起来可能会比LinkedHashMap慢。
     * 因为LinkedHashMap的遍历速度只和实际数据有关，和容量无关，而HashMap的遍历速度和他的容量有关。
     */
    @RequestMapping("/linked-hash-map")
    public void linkedHashMap() {
        LinkedHashMap<String, Integer> scores = new LinkedHashMap<>();
        scores.put("语文" , 80);
        scores.put("英文" , 82);
        scores.put("数学" , 76);
        //遍历scores里的所有的key-value对
        for (String key : scores.keySet()) {
            System.out.println(key + "------>" + scores.get(key));
        }

        scores.forEach((key, value) -> {
            System.out.print(key + "---->");
            System.out.print(value + "\n");
        });
    }

    /**
     * 元素必须实现了Comparable接口，如果没有继承，要在初始化时传入值
     */
    @RequestMapping("/tree-map")
    public void treeMap() {
        TreeMap<F, String> tm = new TreeMap<>((F f1, F f2) -> {
            return f1.getCount() < f2.getCount() ? -1 : (f1.getCount() > f2.getCount() ? 1 : 0);
        });
        tm.put(new F(3) , "轻量级Java EE企业应用实战");
        tm.put(new F(-5) , "疯狂Java讲义");
        tm.put(new F(9) , "疯狂Android讲义");

        System.out.println(tm);

        //返回该TreeMap的第一个Entry对象
        System.out.println(tm.firstEntry());

        //返回该TreeMap的最后一个key值
        System.out.println(tm.lastKey());

        //返回该TreeMap的比new R(2)大的最小key值。
        System.out.println(tm.higherKey(new F(2)));

        //返回该TreeMap的比new R(2)小的最大的key-value对。
        System.out.println(tm.lowerEntry(new F(2)));

        //返回该TreeMap的子TreeMap
        System.out.println(tm.subMap(new F(-1) , new F(4)));
    }

    /**
     * 没有引用的元素，会被自动回收
     * 如果需要使用WeakHashMap的key来保留对象的弱引用，则不要让key所引用的对象具有任何强引用，否则将失去使用WeakHashMap的意义
     */
    @RequestMapping("/week-hash-map")
    public void weekHashMap() {
        WeakHashMap<String, String> whm = new WeakHashMap<>();
        //将WeakHashMap中添加三个key-value对，
        //三个key都是匿名字符串对象（没有其他引用）
        whm.put(new String("语文") , new String("良好"));
        whm.put(new String("数学") , new String("及格"));
        whm.put(new String("英文") , new String("中等"));

        //将WeakHashMap中添加一个key-value对，
        //该key是一个系统缓存的字符串对象。"java"是一个常量字符串强引用
        whm.put("java" , new String("中等"));
        //输出whm对象，将看到4个key-value对。
        System.out.println(whm);
        //通知系统立即进行垃圾回收
        System.gc();
        System.runFinalization();
        //通常情况下，将只看到一个key-value对。
        System.out.println(whm);
    }

    /**
     * 而对于IdentityHashMap则不同，他是非分明，他只承认key==e.key的结果为true时，才认为是相同的Entry。
     * 不管双胞胎弟弟今天穿绿色，明天穿蓝色，他都认为你是同一个人，不会“脸盲”。
     */
    @RequestMapping("identity-hash-map")
    public void identityHashMap() {
        IdentityHashMap<String, Integer> ihm = new IdentityHashMap<>();
        //下面两行代码将会向IdentityHashMap对象中添加两个key-value对
        ihm.put(new String("语文") , 89);
        ihm.put(new String("语文") , 78);

        //下面两行代码只会向IdentityHashMap对象中添加一个key-value对
        ihm.put("java" , 93);
        ihm.put("java" , 98);
        System.out.println(ihm);
    }

    enum Season
    {
        SPRING,SUMMER,FALL,WINTER
    }
    @RequestMapping("/enum-map")
    public void enumMap() {
        EnumMap<Season, String> enumMap = new EnumMap<>(Season.class);
        enumMap.put(Season.SUMMER , "夏日炎炎");
        enumMap.put(Season.SPRING , "春暖花开");
        System.out.println(enumMap);
    }
}

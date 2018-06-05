package com.weison.controller.dataStruct;

import com.weison.model.dataStruct.hashSet.E;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 1. java提供的List就是一个"线性表接口"，ArrayList(基于数组的线性表)、LinkedList(基于链的线性表)是线性表的两种典型实现
 * 2. Queue代表了队列，Deque代表了双端队列(既可以作为队列使用、也可以作为栈使用)
 * 3. 因为数组以一块连续内存来保存所有的数组元素，所以数组在随机访问时性能最好。所有的内部以数组作为底层实现的集合在随机访问时性能最好。
 * 4. 内部以链表作为底层实现的集合在执行插入、删除操作时有很好的性能
 * 5. 进行迭代操作时，以链表作为底层实现的集合比以数组作为底层实现的集合性能好
 */
@RequestMapping("/list")
@RestController
public class DataStructListController
{

    /**
     * ArrayList
     * 1) add(element)加入元素到ArrayList,可指定位置
     * 2) size() 获取ArrayList的长度
     * 3）remote(index) 删除指定位置的元素
     * 4) indexOf(element) 获取指定元素的位置
     * 5) set(index, element) 覆盖指定位置的元素
     * 6) subList(indexStart, indexEnd) 获取包含左边界，不包含右边界的元素
     * 7) addAll(Collection) 添加集合
     */
    @RequestMapping("/array-list")
    public void arrayList()
    {
        List<String> books = new ArrayList<>();

        //向books集合中添加三个元素
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        books.add("疯狂Android讲义");
        //List元素可以重复
        books.add("疯狂Android讲义");
        System.out.println(books);
        System.out.println("The size of this array list is:" + books.size());

        //将新字符串对象插入在第二个位置
        books.add(1 , "疯狂Ajax讲义");
        books.forEach(System.out::println);

        //删除第三个元素
        books.remove(2);
        System.out.println(books);

        //判断指定元素在List集合中位置：输出1，表明位于第二位
        System.out.println(books.indexOf("疯狂Ajax讲义"));
        //将第二个元素替换成新的字符串对象
        books.set(1, "LittleHann");
        System.out.println(books);

        //将books集合的第二个元素（包括）
        //到第三个元素（不包括）截取成子集合
        System.out.println(books.subList(1 , 2));

        //将集合加入到arrayList
        List<String> books1 = new ArrayList<>();
        books1.addAll(books);
        System.out.println(books1);
    }

    /**
     * Stack 栈
     * 1) push()入栈
     * 2) peek()取出栈顶元素，但不出栈
     * 3) pop()出栈
     */
    @RequestMapping("/stack")
    public void stack()
    {
        Stack<String> v = new Stack<>();
        //依次将三个元素push入"栈"
        v.push("疯狂Java讲义");
        v.push("轻量级Java EE企业应用实战");
        v.push("疯狂Android讲义");

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);

        //访问第一个元素，但并不将其pop出"栈"，输出：疯狂Android讲义
        System.out.println(v.peek());

        //依然输出：[疯狂Java讲义, 轻量级Java EE企业应用实战 , 疯狂Android讲义]
        System.out.println(v);

        //pop出第一个元素，输出：疯狂Android讲义
        System.out.println(v.pop());

        //输出：[疯狂Java讲义, 轻量级Java EE企业应用实战]
        System.out.println(v);
    }

    /**
     * 双端队列,list实现类
     */
    @RequestMapping("/linked-list")
    public void linkedList()
    {

        LinkedList<String> books = new LinkedList<>();

        //将字符串元素加入队列的尾部(双端队列)
        books.offer("疯狂Java讲义");

        //将一个字符串元素加入栈的顶部(双端队列)
        books.push("轻量级Java EE企业应用实战");

        //将字符串元素添加到队列的头(相当于栈的顶部)
        books.offerFirst("疯狂Android讲义");

        books.forEach(System.out::println);


        //访问、并不删除栈顶的元素
        System.out.println(books.peekFirst());
        //访问、并不删除队列的最后一个元素
        System.out.println(books.peekLast());
        //将栈顶的元素弹出"栈"
        System.out.println(books.pop());
        //下面输出将看到队列中第一个元素被删除
        System.out.println(books);
        //访问、并删除队列的最后一个元素
        System.out.println(books.pollLast());
        //下面输出将看到队列中只剩下中间一个元素：
        //轻量级Java EE企业应用实战
        System.out.println(books);
    }

    /**
     * 1) 自然排序:
     * 采用自然顺序的PriorityQueue集合中的元素对象都必须实现了Comparable接口，而且应该是同一个类的多个实例，
     * 否则可能导致ClassCastException异常
     * 2) 定制排序
     * 创建PriorityQueue队列时，传入一个Comparator对象，该对象负责对队列中的所有元素进行排序
     * 关于自然排序、定制排序的原理和之前说的TreeSet类似
     */
    @RequestMapping("/priority-queue")
    public void priorityQueue()
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //下面代码依次向pq中加入四个元素
        pq.offer(6);
        pq.offer(-3);
        pq.offer(9);
        pq.offer(0);

        //输出pq队列，并不是按元素的加入顺序排列，
        //而是按元素的大小顺序排列，输出[-3, 0, 9, 6]
        System.out.println(pq);
        //访问队列第一个元素，其实就是队列中最小的元素：-3

        //peek()访问不删除
        System.out.println(pq.peek());
        System.out.println(pq);

        //poll()访问删除;
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq);

        //队列的元素必须实现了Comparable接品, 否则会报错, 运行后面的代码，请注释此处
        /*PriorityQueue<C> pq0 = new PriorityQueue<>();
        pq0.add(new C());
        pq0.add(new C());*/

        PriorityQueue<E> pq1 = new PriorityQueue<>((E e1, E e2) -> {
            return e1.getAge() < e2.getAge() ? -1 : (e1.getAge() > e2.getAge() ? 1 : 0);
        });
        pq1.add(new E(11, "AAA"));
        pq1.add(new E(22, "BBB"));
        System.out.println(pq1.poll().age);
    }

    /**
     * 双端队列 array实现类
     * 1. 无限的扩展，自动扩展队列大小的。（当然在不会内存溢出的情况下。）
     * 2. 非线程安全的，不支持并发访问和修改。
     * 3. 支持fast-fail.
     * 4. 作为栈使用的话比比栈要快.
     * 5. 当队列使用比linklist要快。
     * 6. null元素被禁止使用。
     */
    @RequestMapping("/array-deque")
    public void arrayDeque()
    {
        /*
         * 作为栈使用
         */
        ArrayDeque<String> stack = new ArrayDeque<>();

        //依次将三个元素push入"栈", push()等价addFirst()
        stack.push("First In");
        stack.push("Middle In");
        stack.push("Last In");

        //输出：[First In, Middle In, Last In]
        System.out.println(stack);

        //访问第一个元素，但并不将其pop出"栈"，peek()默认为peekFirst() 输出：Last In
        System.out.println(stack.peek());

        //依然输出：[First In, Middle In, Last In]
        System.out.println(stack);

        //pop出第一个元素，pop() 等价于 pollFirst()，输出：Last In
        System.out.println(stack.pop());

        //输出：[First In, Middle In]
        System.out.println(stack);

        //pop出第一个元素， pop() 等价于 pollFirst()，输出：Last In
        System.out.println(stack.pop());

        //输出：[First In, Middle In]
        System.out.println(stack);

        ArrayDeque<String> stack1 = new ArrayDeque<>();

        /*
         * 作为队列使用
         */
        //依次将三个元素push入"栈"
        stack1.push("First In Another");
        stack1.push("Middle In Another");
        stack1.push("Last In Another");

        //输出：[First In Another, Middle In Another, Last In Another]
        System.out.println(stack1);

        //访问最先放入的元素，但并不将其poll出"队列"，输出：First In Another
        System.out.println(stack1.peekLast());

        //依然输出：[First In Another, Middle In Another, Last In Another]
        System.out.println(stack1);

        //取出最先入的元素，输出：First In Another
        System.out.println(stack1.pollLast());

        //输出：[Last In Another, Middle In Another]
        System.out.println(stack1);
    }
}

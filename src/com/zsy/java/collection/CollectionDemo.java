package com.zsy.java.collection;

import java.util.*;

/**
 * Title com.zsy.java.collection
 * .
 * Collection {
 * .          iterator()     //迭代器
 * .          size(),isEmpty(),contains(Object o), iterator(), toArray(), toArray(Object[] a),
 * .          add(Object o),remove(Object o),containsAll(Collection c),addAll(Collection c),
 * removeAll(Collection c),retainAll(Collection c),clear()
 * .     List{
 * .         ArrayList{             //索引在数组中搜索和读取数据(查询快)(增删整体拷贝)
 * .           clone(),             //不同
 * .         }
 * .         Vector{                 //Vector类操作时synchronized线程安全(增删整体拷贝)
 * .           Enumeration = elements() 遍历, clone();//不同
 * .         }
 * .         LinkList{               //link链表(插入,删除快)
 * .            addFirst(), getFirst(), addLast(), getLast();
 * .         }
 * .     }
 * .     Set{
 * .         TreeSet{               //排序
 * .            1.Comparator比较器  2.Comparable接口
 * .         }
 * .         HashSet{
 * .             LinkedHashSet{      //底层的数据结构是链表和哈希表
 * .
 * .                }
 * .         }
 * .     }
 * . }
 *
 * @author Zsy
 * @date 2019/8/10 15:54
 */
public class CollectionDemo {

    public static void main(String[] args) {
//        listTest();
        setTest();
    }

    private static void setTest() {
        Collection collection = new HashSet();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<Integer>() ;
        linkedHashSet.add(2);
        for (int i = 0; i < 5; i++) {
            linkedHashSet.add(i);
        }
        System.out.println(linkedHashSet);

        //1.类实现 Comparable 接口 2.使用Comparator
        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (int)o1-(int)o2;
            }
        });
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(2);
        System.out.println(treeSet);
    }

    private static void listTest() {
        Vector vector = new Vector(20);
        vector.add(1);
        vector.add(2);
        int capacity = vector.capacity();
        System.out.println(capacity + " " + vector.size());
        for (int i = 3; i < 150; i++) {
            vector.add(i);
        }
        Enumeration enumeration = vector.elements() ;
        while(enumeration.hasMoreElements()){
            Object o = enumeration.nextElement();
            System.out.println(o);
        }
        System.out.println(capacity + " " + vector.size());

        LinkedList linkedList = new LinkedList();
        System.out.println("");

        ArrayList arrayList = new ArrayList(20);
        arrayList.add(1);
        arrayList.add(2);
        ArrayList clone = (ArrayList) arrayList.clone();//不同
        clone.add(3);
        System.out.println(arrayList);
        System.out.println(clone);
    }

}

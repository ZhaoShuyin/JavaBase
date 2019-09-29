package com.zsy.java.other.linkqueue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Title ConcurrentLinkedQueue先进先出队列
 *
 * @Date 2019/9/29
 * @Autor Zsy
 */
public class Grammar {
    public static void main(String[] args) {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        //queue.isEmpty()
        System.out.println("从队列中peek：" + queue.peek());//只读
        System.out.println("size== "+queue.size());
        System.out.println("从队列中poll：" + queue.poll());//取出最前数据
        System.out.println("size== "+queue.size());
        System.out.println("从队列中poll：" + queue.poll());//取出最前数据
        System.out.println("size== "+queue.size());
        System.out.println("从队列中poll：" + queue.poll());//取出最前数据
    }
}

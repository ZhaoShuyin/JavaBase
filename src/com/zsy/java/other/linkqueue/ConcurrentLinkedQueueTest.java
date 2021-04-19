package com.zsy.java.other.linkqueue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueTest {


    public static void main(String[] args) throws InterruptedException {
        int peopleNum = 10000;//吃饭人数
        int tableNum = 10;//饭桌数量

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        CountDownLatch countDownLatch = new CountDownLatch(tableNum);//计数器

        //将吃饭人数放入队列（吃饭的人进行排队）
        for (int i = 1; i <= peopleNum; i++) {
            queue.offer("消费者_" + i);
        }
        //执行10个线程从队列取出元素（10个桌子开始供饭）
        System.out.println("-----------------------------------开饭了-----------------------------------");
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(tableNum);
        for (int i = 0; i < tableNum; i++) {
            executorService.submit(new Dinner("00" + (i + 1), queue, countDownLatch));
        }
        //计数器等待(暂时占据线程执行)，知道队列为空（所有人吃完）
        countDownLatch.await();

        long time = System.currentTimeMillis() - start;
        System.out.println("-----------------------------------所有人已经吃完-----------------------------------");
        System.out.println("共耗时：" + time);
        //停止线程池
        executorService.shutdown();
    }

    private static class Dinner implements Runnable {
        private String name;
        private ConcurrentLinkedQueue<String> queue;
        private CountDownLatch countDownLatch;

        public Dinner(String name, ConcurrentLinkedQueue<String> queue, CountDownLatch count) {
            this.name = name;
            this.queue = queue;
            this.countDownLatch = count;
        }

        @Override
        public void run() {
//            while (queue.size() > 0){//需要遍历所有元素,链表succ()获取下一个
            while (!queue.isEmpty()) { //只需判断first() == null
                //从队列取出一个元素 排队的人少一个
                System.out.println("【" + queue.poll() + "】----已吃完...， 饭桌编号：" + name);
            }
            countDownLatch.countDown();//计数器-1
        }
    }
}
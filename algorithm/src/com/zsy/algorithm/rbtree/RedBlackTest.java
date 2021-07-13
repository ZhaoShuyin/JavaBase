package com.zsy.algorithm.rbtree;

import java.util.Random;

/**
 * @Title com.zsy.algorithm.rbtree
 * @date 2021/6/21
 * @autor Zsy
 */

public class RedBlackTest {

    public static void main(String[] args) {
        RadBlack radBlack = new RadBlack();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            try {
                Thread.sleep(1000);
                radBlack.add(i+1);
                System.out.println("添加节点 : "+(i+1));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("异常:"+(i+1));
                return;
            }
        }
        /*for (int i = 0; i < 10; i++) {
            radBlack.add(random.nextInt(100));
        }*/
        System.out.println(radBlack.show(null));
    }

    private static void a() {
        RadBlack radBlack = new RadBlack();
        Node boot = new Node(1);
        radBlack.treeRoot = boot;

        Node node2 = new Node(2, boot);
        radBlack.treeRoot.left = node2;

        Node node3 = new Node(3, boot);
        radBlack.treeRoot.right = node3;

        node2.left = new Node(4, node2);
        node2.right = new Node(5, node2);

        node3.left = new Node(6, node3);
        node3.right = new Node(7, node3);

        System.out.println(radBlack.show(null));
    }

}

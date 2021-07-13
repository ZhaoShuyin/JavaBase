package com.zsy.algorithm.rbtree;

/**
 * @Title com.zsy.algorithm.rbtree
 * @date 2021/6/21
 * @autor Zsy
 */
public class Node {

    public Node boot;
    public Node left;
    public Node right;

    public int value;
    public boolean color = true;  //红/黑

    public int x;
    public int y;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node boot) {
        this.value = value;
        this.boot = boot;
    }

    @Override
    public String toString() {
        return "{v:" + value + ",x:" + x + ",y:" + y + ",c:" + (color ? "黑" : "红") + "}";
    }
}

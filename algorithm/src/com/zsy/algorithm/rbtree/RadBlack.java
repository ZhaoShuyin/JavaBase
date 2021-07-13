package com.zsy.algorithm.rbtree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title 红黑树
 * @date 2021/6/21
 * @autor Zsy
 * https://www.jianshu.com/p/e136ec79235c             (30张图带你彻底理解红黑树)
 * https://www.cnblogs.com/newobjectcc/p/11293689.html(数据结构之红黑树-动图演示(上))
 * https://www.cnblogs.com/newobjectcc/p/11295652.html(数据结构之红黑树-动图演示(下))
 * 性质1：节点为黑色或者红色。
 * 性质2：根节点是黑色。
 * 性质3：每个叶子节点（NIL）是黑色。
 * 性质4：每个红色结点的两个子结点一定都是黑色。
 * 性质5：任意结点到每个叶子结点的路径都包含数量相同的黑结点
 * 红黑树的生长是自底向上的
 */
public class RadBlack {

    private int WIDTH = 50;
    private int number = 0;
    public Node treeRoot;
    private char c = '*';
    private final Node nil = new Node(-1); //叶子节点

    /**
     * 添加节点
     */
    public void add(int vlaue) {
        Node addNode = new Node(vlaue);
        if (treeRoot == null) {
            treeRoot = addNode;
            treeRoot.left = new Node(-1, treeRoot);
            treeRoot.right = new Node(-1, treeRoot);
            number++;
            return;
        }
        Node upstream = null;
        Node compile = treeRoot;
        while (compile.value != -1) {
            upstream = compile;
            if (vlaue < compile.value) {       //小值置于左侧
                compile = compile.left;
            } else if (vlaue > compile.value) {//大值置于右侧
                compile = compile.right;
            } else {                       //重复结点不插入
                return;
            }
        }
        number++;
        addNode.boot = upstream;
        if (addNode.value < upstream.value) {
            upstream.left = addNode;
        } else {
            upstream.right = addNode;
        }
        addNode.left = new Node(-1, addNode);
        addNode.right = new Node(-1, addNode);
        addNode.color = false;  //默认红色
        addBalance(addNode);

    }

    /**
     * 插入修复
     */
    private void addBalance(Node node) {
        //遍历父节点直至为黑色时
        while (node.boot != null && node.boot.color == false) {
            if (node.boot == node.boot.boot.left) {
                Node y = node.boot.boot.right;
                if (y.color == false) {
                    node.boot.color = true;
                    y.color = true;
                    node.boot.boot.color = false;
                    node = node.boot.boot;
                } else {
                    if (node == node.boot.right) {
                        node = node.boot;
                        leftRotate(node);
                    }
                    node.boot.color = true;
                    node.boot.boot.color = false;
                    rightRotate(node.boot.boot);
                }
                show("添加 平衡调整 左侧");
            } else {
                Node y = node.boot.boot.left;
                if (y.color == false) {
                    node.boot.color = true;
                    y.color = true;
                    node.boot.boot.color = false;
                    node = node.boot.boot;
                } else {
                    if (node == node.boot.left) {
                        node = node.boot;
                        rightRotate(node);
                    }
                    node.boot.color = true;
                    node.boot.boot.color = false;
                    leftRotate(node.boot.boot);
                }
                show("添加 平衡调整 左侧");
            }
        }
        treeRoot.color = true;    //根节点必须为黑色
    }

    public Node find(int value) {
        return null;
    }

    public void delete(int value) {

    }


    /**
     * 左旋
     */
    private void leftRotate(Node to) {
        Node form = to.right;     //左旋起点
        to.right = form.left;     //起点坐支置为终点右支
        if (form.left != null) {
            form.left.boot = to;  //切换分支设置父节点
        }
        form.boot = to.boot;      //设置上游分支
        if (to.boot == null) {
            treeRoot = form;
        } else if (to == to.boot.left) {
            to.boot.left = form;
        } else {
            to.boot.right = form;
        }
        form.left = to;
        to.boot = form;
    }

    /**
     * 右旋
     */
    private void rightRotate(Node to) {
        Node form = to.left;
        to.left = form.right;
        if (form.right != null) {
            form.right.boot = to;
        }
        form.boot = to.boot;
        if (to.boot == null) {
            treeRoot = form;
        } else if (to == to.boot.left) {
            to.boot.left = form;
        } else {
            to.boot.right = form;
        }
        form.right = to;
        to.boot = form;
    }

    /**
     * 坐标格式化
     */
    public void locate(Node node) {
        if (node == null) {
            return;
        }
        int difX;
        if (node.boot == null) {  //根节点
            difX = 0;
        } else if (node.boot.boot == null) {//第二级节点
            difX = WIDTH / 4;
        } else {
            difX = Math.abs(node.boot.boot.x - node.boot.x) / 2;
        }
        if (node.boot == null) {
            node.x = WIDTH / 2;
            node.y = 0;
        } else {
            node.y = node.boot.y + 1;        //层级+1
            //x坐标 左减右加
            node.x = (node == node.boot.left) ? node.boot.x - difX : node.boot.x + difX;
        }
        locate(node.left);
        locate(node.right);
    }

    /**
     * return 通过字符串的形式显示当前红黑树的结构数据
     */
    public String show(String tag) {
        if (treeRoot == null) {
            return "null";
        }
        locate(treeRoot);
        List<StringBuilder> list = new ArrayList<>();
        nodeInfo(treeRoot, list);
        StringBuilder stringBuilder = list.get(list.size() - 1);
        int max = stringBuilder.length();
        int index = 0;
        while (true) {
            if (c != stringBuilder.charAt(index++)) {
                max += index;
                break;
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append("=========== " + tag + " ============\n");
        for (int i = 0; i < list.size(); i++) {
            StringBuilder b = list.get(i);
            while (b.length() < max) {
                b.append(c);
            }
            builder.append(b.toString() + "\n");
        }
        return builder.toString();
    }

    /**
     * @param node 节点
     * @param list Node节点的信息添到StringBuilder集合(按行)中
     */
    private void nodeInfo(Node node, List<StringBuilder> list) {
        if (node == null) {
            return;
        }
        while (list.size() <= node.y) {
            list.add(new StringBuilder());
        }
        StringBuilder builder = list.get(node.y);
        while (builder.length() < node.x) {
            builder.append(c);
        }
        if (node.value == -1) {
            builder.append("<N>");
        } else if (node.color) {
            builder.append("黑[" + node.value + "]");
        } else {
            builder.append("红(" + node.value + ")");
        }
        nodeInfo(node.left, list);
        nodeInfo(node.right, list);
    }

    public static void main(String[] args) {
        RadBlack radBlack = new RadBlack();
        for (int i = 1; i < 8; i++) {
            radBlack.add(i);
            System.out.println("添加节点: " + i);
        }
        System.out.println(radBlack.show("测试添加1~8节点"));
    }

}

package com.zsy.pattern;

/**
 * Title: 设计模式之桥接模式
 * 介绍:将抽象部分与实现部分分离，使它们都可以独立的变化
 * 例子:
 *
 * @author Zsy
 * @date 2019/8/2 11:16
 */
public class J_Bridge {
    RantangleShape rantangleShape = new RantangleShape()
}


class DP1 {
    public void draw_1_Rantanle() {
        System.out.println("DP1画矩形");
    }

    public void draw_1_Circle() {
        System.out.println("DP1画圆形");
    }
}

class DP2 {
    public void drawRantanle() {
        System.out.println("DP2画矩形");
    }

    public void drawCircle() {
        System.out.println("DP2画圆形");
    }
}

interface Drawing {
    public void drawRantangle();

    public void drawCircle();
}

abstract class BaseShape {
    protected Drawing myDrawing;

    public BaseShape(Drawing drawing) {
        this.myDrawing = drawing;
    }

    abstract void draw();

    protected void drawRectangle() {
        myDrawing.drawRantangle();
    }

    protected void drawCircle() {
        myDrawing.drawCircle();
    }
}

class RantangleShape extends BaseShape {
    public RantangleShape(Drawing drawing) {
        super(drawing);
    }

    @Override
    public void draw() {
        drawRectangle();
    }
}

class CircleShape extends BaseShape {
    public CircleShape(Drawing drawing) {
        super(drawing);
    }

    @Override
    public void draw() {
        drawCircle();
    }
}

class DrawingV1 implements Drawing {
    DP1 dp1;

    public DrawingV1() {
        dp1 = new DP1();
    }

    @Override
    public void drawRantangle() {
        dp1.draw_1_Rantanle();
    }

    @Override
    public void drawCircle() {
        dp1.draw_1_Circle();
    }
}

class DrawingV2 implements Drawing {
    DP2 dp2;

    public DrawingV2() {
        dp2 = new DP2();
    }

    @Override
    public void drawRantangle() {
        dp2.drawRantanle();
    }

    @Override
    public void drawCircle() {
        dp2.drawCircle();
    }
}

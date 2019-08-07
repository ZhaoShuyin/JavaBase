package com.zsy.pattern;

/**
 * Title: 设计模式之桥接模式
 * 介绍:将抽象部分与实现部分分离，使它们都可以独立的变化
 * 例子:ListView和BaseAdpater其实就是Bridge模式
 *
 * @author Zsy
 * @date 2019/8/2 11:16
 */
public class J_Bridge {
    public static void main(String[] args) {
        DrawingV1 drawingV1 = new DrawingV1();
        drawingV1.circle();
        drawingV1.rantangle();

        DrawingV2 drawingV2 = new DrawingV2();
        drawingV2.circle();
        drawingV2.rantangle();

        BaseShape shape1 = new CircleShape(new Drawing() {
            @Override
            public void rantangle() {
                System.out.println("shape1 画矩形");
            }

            @Override
            public void circle() {
                System.out.println("shape1 画圆形");
            }
        });
        shape1.draw();


        BaseShape shape2 = new RantangleShape(new Drawing() {
            @Override
            public void rantangle() {
                System.out.println("shape2 画矩形");
            }

            @Override
            public void circle() {
                System.out.println("shape2 画圆形");
            }
        });
        shape2.draw();


    }

}

//画笔1
class DP1 {
    public void draw_1_Rantanle() {
        System.out.println("DP1画矩形");
    }
    public void draw_1_Circle() {
        System.out.println("DP1画圆形");
    }
}

//画笔2
class DP2 {
    public void drawRantanle() {
        System.out.println("DP2画矩形");
    }
    public void drawCircle() {
        System.out.println("DP2画圆形");
    }
}

//绘制接口
interface Drawing {
    public void rantangle();

    public void circle();
}

abstract class BaseShape {
    protected Drawing myDrawing;

    public BaseShape(Drawing drawing) {
        this.myDrawing = drawing;
    }

    abstract void draw();

    protected void drawRectangle() {
        myDrawing.rantangle();
    }

    protected void drawCircle() {
        myDrawing.circle();
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
    public void rantangle() {
        dp1.draw_1_Rantanle();
    }

    @Override
    public void circle() {
        dp1.draw_1_Circle();
    }
}

class DrawingV2 implements Drawing {
    DP2 dp2;

    public DrawingV2() {
        dp2 = new DP2();
    }

    @Override
    public void rantangle() {
        dp2.drawRantanle();
    }

    @Override
    public void circle() {
        dp2.drawCircle();
    }
}

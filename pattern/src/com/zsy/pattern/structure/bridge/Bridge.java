package com.zsy.pattern.structure.bridge;

/**
 * Title: 设计模式之桥接模式
 * 介绍:将抽象部分与实现部分分离，使它们都可以独立的变化
 * 例子:ListView和BaseAdpater其实就是Bridge模式
 *
 * @author Zsy
 * @date 2019/8/2 11:16
 */
public class Bridge {

    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        drawing.circle();
        drawing.rantangle();

        //内部类实现绘制
        BaseShape shape1 = new CircleShape(new IDraw() {
            @Override
            public void rantangle() {
                System.out.println("<圆> -> 画矩形");
            }

            @Override
            public void circle() {
                System.out.println("<圆> -> 画圆形");
            }
        });
        shape1.draw();

        //内部类实现绘制
        BaseShape shape2 = new RantangleShape(new IDraw() {
            @Override
            public void rantangle() {
                System.out.println("<矩> -> 画矩形");
            }

            @Override
            public void circle() {
                System.out.println("<矩> -> 画圆形");
            }
        });
        shape2.draw();
    }
}

//画笔
class Paint {
    public void drawRantanle() {
        System.out.println("画笔--> 画矩形");
    }

    public void drawCircle() {
        System.out.println("画笔--> 画圆形");
    }
}

//绘制接口
interface IDraw {
    public void rantangle();

    public void circle();
}

//内部持有画笔,使用画笔进行绘制
class Drawing implements IDraw {
    Paint paint;

    public Drawing() {
        paint = new Paint();
    }

    @Override
    public void rantangle() {
        paint.drawRantanle();
    }

    @Override
    public void circle() {
        paint.drawCircle();
    }
}

abstract class BaseShape {
    protected IDraw myDrawing;

    public BaseShape(IDraw drawing) {
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
    public RantangleShape(IDraw drawing) {
        super(drawing);
    }

    @Override
    public void draw() {
        drawRectangle();
    }
}

class CircleShape extends BaseShape {
    public CircleShape(IDraw drawing) {
        super(drawing);
    }

    @Override
    public void draw() {
        drawCircle();
    }
}





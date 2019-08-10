package com.zsy.java.eenum.sample;

/**
 * 颜色定义方法
 */
public enum Color {
    RED("红色", 1, 0xffff0000),
    GREEN("绿色", 2, 0xff00ff00),
    BLUE("蓝色", 3, 0xff0000ff);
    // 成员变量  
    public String name;
    public int index;
    public int color;

    // 构造方法  
    private Color(String name, int index, int color) {
        this.name = name;
        this.index = index;
        this.color = color;
    }

    // 普通方法  
    public static Color getColorByIndex(int index) {
        for (Color c : Color.values()) {//Color.values()方法获取
            if (c.index == index) {
                return c;
            }
        }
        return null;
    }


}
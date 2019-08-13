package com.zsy.java.eenum;

import com.zsy.java.eenum.sample.Color;
import com.zsy.java.eenum.sample.Direction;
import com.zsy.java.eenum.sample.WeekDay;

import java.util.EnumSet;

import static com.zsy.java.eenum.sample.WeekDay.TUE;

/**
 * Title 枚举范例
 *
 * @author Zsy
 * @date 2019/8/7 21:21
 */
public class EnumDemo {

    public static void main(String[] args) {

        enumSetTest();
//        colorTest();

//        //创建elementType类型的空集
//        EnumSet<WeekDay> week = EnumSet.noneOf(WeekDay.class);
//
//        week.add(WeekDay.MON);
//        System.out.println("EnumSet中的元素：" + week);
//
//        week.remove(WeekDay.MON);
//        System.out.println("EnumSet中的元素：" + week);
//
//        week.addAll(EnumSet.complementOf(week));
//        System.out.println("EnumSet中的元素：" + week);
//
//        week.removeAll(EnumSet.range(WeekDay.FRI, WeekDay.SAT));
//        System.out.println("EnumSet中的元素：" + week);
    }

    /**
     * EnumSet用法
     */
    private static void enumSetTest() {
        //获取所有枚举元素集合
        EnumSet<Color> colorEnumSet = EnumSet.allOf(Color.class);
        System.out.println(colorEnumSet);

        //获取空枚举元素集合
        EnumSet<Color> colorEnumSet1 = EnumSet.noneOf(Color.class);
        colorEnumSet1.add(Color.RED);
        System.out.println(colorEnumSet1);

        //以指定元素创建枚举集合
        EnumSet<Color> colorEnumSet2 = EnumSet.of(Color.RED, Color.BLUE);
        System.out.println(colorEnumSet2);

        //创建指定范围内枚举元素集合
        EnumSet<WeekDay> weekDayEnumSet1 = EnumSet.range(WeekDay.MON, WeekDay.THT);
        System.out.println(weekDayEnumSet1);

        //指定元素集合的补集元素集合
        EnumSet<WeekDay> weekDayEnumSet2 = EnumSet.complementOf(weekDayEnumSet1);
        System.out.println(weekDayEnumSet2);

        //复制某元素集合的元素集合
        EnumSet<WeekDay> weekDayEnumSet3 = EnumSet.copyOf(weekDayEnumSet2);
        System.out.println(weekDayEnumSet3);
    }

    /**
     *
     */
    public static void colorTest() {
        Color[] values = Color.values();
        for (Color color : values) {
            int ordinal = color.ordinal();
            System.out.println("枚举元素次序 ordinal = "+ordinal);
            System.out.println(color.name + "-" + color.index + "-" + color.color);
        }
        Color colorByIndex = Color.getColorByIndex(3);
        System.out.println(colorByIndex);
    }

    /**
     * 枚举中常见的方法:
     *
     * 	int ordinal()			: 获取枚举项对应的编号 , 这个编号从0开始
     *  int compareTo(E o)		: 返回的是对应的编号的差值
     *  String name()			: 获取的是枚举项对应的名称
     *  String toString()		: 返回的是枚举项的名称
     *  static <T> T valueOf(Class<T> type,String name); 在指定的枚举中获取指定名称的枚举项
     *  static <T> T[] values() :		或所有的枚举项
     */
    public static void directionTest(){
        Direction back = Direction.BACK ;
        System.out.println(back.ordinal());

        Direction left = Direction.LEFT ;
        System.out.println(left.ordinal());

        System.out.println(left.compareTo(back));

        System.out.println(back.name());

        System.out.println(back.toString());

        Direction direction = Direction.valueOf(Direction.class, "LEFT") ;
        System.out.println("valueOf() "+direction);

        Direction[] values = Direction.values() ;
        for(Direction direction2 : values) {
            System.out.println(direction2);
        }

    }
}

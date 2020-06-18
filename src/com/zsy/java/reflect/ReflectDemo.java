package com.zsy.java.reflect;

import com.zsy.java.reflect.sample.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Title com.zsy.java.reflect
 * @author Zsy
 * @date 2019/8/10 9:46
 */
public class ReflectDemo {
    public static String packageName = "com.zsy.java.reflect.sample.Student";

    public static void main(String[] args) {
        //testGetClass();      // 1.获取class
        //testGetConstructor();// 2.构造方法
        //testGetFiled();      // 3.成员变量
        //testGetMethed();     // 4.方法
    }

    /**
     * public Method[] getMethods()		: 获取的是所有的公共的成员方法,包含从父类中继承过来的
     * public Method[] getDeclaredMethods(): 获取本类中所有的成员方法,包含私有的
     *
     * public Method getMethod(String name,  Class<?>... parameterTypes)
     * public Method getDeclaredMethod(String name,   Class<?>... parameterTypes)
     */
    private static void testGetMethed() {
        try {
            Class clazz = Class.forName("com.zsy.java.reflect.sample.Student");
            Constructor constructor = clazz.getConstructor(String.class, int.class, int.class);
            //私有方法,取消语法检查
            constructor.setAccessible(true);
            Student student = (Student) constructor.newInstance("赵六", 22, 1);

            Method getName = clazz.getMethod("getName");
            System.out.println(""+getName.invoke(student));

            Method getRank = clazz.getDeclaredMethod("getRank");
            //取消语法检查
            getRank.setAccessible(true);
            System.out.println(""+getRank.invoke(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * public Field[] getFields()			: 所有的公共的成员变量 , 包含从父类中继承过来
     * public Field[] getDeclaredFields()	: 获取的本类中所有的成员变量,包含私有的
     * <p>
     * public Field getField(String name)
     * public Field getDeclaredField(String name)
     */
    private static void testGetFiled() {
        try {
            Class clazz = Class.forName("com.zsy.java.reflect.sample.Student");
            Constructor constructor = clazz.getConstructor();
            Student student = (Student) constructor.newInstance();
            //获取成员变量
            Field field = clazz.getField("name");
            field.set(student, "王五");
            Field field2 = clazz.getField("age");
            field2.set(student, 18);

            Field field3 = clazz.getDeclaredField("ranking");
            //取消语法检查
            field3.setAccessible(true);
            field3.set(student, 29);
            System.out.println("student:" + student);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * public Constructor<?>[] getConstructors()                                //所有构造方法
     * public Constructor<?>[] getDeclaredConstructors()                        //所有(私有)的构造方法
     * <p>
     * public Constructor<T> getConstructor(Class<?>... parameterTypes)         //使用某些参数的方法
     * public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) //使用(私有)某些参数的方法
     */
    public static void testGetConstructor() {
        try {
            Class clazz = Class.forName("com.zsy.java.reflect.sample.Student");
            Constructor[] constructors = clazz.getDeclaredConstructors();
            for (Constructor con : constructors) {
                System.out.println("反射获取构造方法 " + con);
            }
            //获取某个指定参数的构造方法
            Constructor constructor = clazz.getConstructor(String.class, int.class);
            Student student = (Student) constructor.newInstance("张三", 123);
            System.out.println("student --> " + student);

            //获取某个指定参数的构造方法
            Constructor constructor2 = clazz.getConstructor(String.class, int.class, int.class);
            //私有方法,取消语法检查
            constructor2.setAccessible(true);
            Student student2 = (Student) constructor2.newInstance("李四", 123, 2);
            System.out.println("student2 --> " + student2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取class
     */
    public static void testGetClass() {
        Student s1 = new Student();
        Student s2 = new Student();
        Class clazz1 = s1.getClass();          // 调用Object类中的getClass方法
        Class clazz2 = s2.getClass();          // 调用Object类中的getClass方法
        System.out.println(clazz1 == clazz2);  //true

        Class clazz3 = Student.class;         // 通过静态的class属性获取
        System.out.println(clazz1 == clazz3); //true

        Class clazz4 = null;                  // 通过Class类中的静态方法:
        try {
            clazz4 = Class.forName("com.zsy.java.reflect.sample.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(clazz1 == clazz4);//true
    }

}

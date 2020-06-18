package com.zsy.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://blog.csdn.net/yjclsx/article/details/52101922
 * 元注解
 * 1.@Target,        //说明了Annotation所修饰的对象范围
 * 2.@Retention,
 * 3.@Documented,
 * 4.@Inherited
 *
 * @Target           // 用于描述注解的使用范围
 * 1.CONSTRUCTOR:用于描述构造器
 * 2.FIELD:用于描述域
 * 3.LOCAL_VARIABLE:用于描述局部变量
 * 4.METHOD:用于描述方法
 * 5.PACKAGE:用于描述包
 * 6.PARAMETER:用于描述参数
 * 7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 * /
 * @Retention        //定义了该Annotation被保留的时间长短
 * 1.SOURCE:在源文件中有效（即源文件保留）
 * 2.CLASS:在class文件中有效（即class保留）
 * 3.RUNTIME:在运行时有效（即运行时保留）
 * /
 * @Documented
 *
 * *
 * @Title 自定义注解
 * * @date 2020/4/13
 * * @autor Zsy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String name() default "123";
}

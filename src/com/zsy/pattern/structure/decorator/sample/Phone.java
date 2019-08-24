package com.zsy.pattern.structure.decorator.sample;

/**
 * Title com.zsy.pattern.d_Decorator.sample
 *
 * @author Zsy
 * @date 2019/8/21 23:17
 */
public class Phone implements IPhone {
    @Override
    public void call() {
        System.out.println("基本功能 打电话");
    }
}

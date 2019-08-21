package com.zsy.pattern.t_template;

/**
 * Title: 设计模式之模板模式
 * 介绍:定义一个操作中的算法的框架，而将一些步骤延迟到子类中(抽象类的基本用法)
 * 例子:AsyncTask 异步任务方法
 *
 * @author Zsy
 * @date 2019/8/1 11:06
 */
public class Template {
    public static void main(String[] args) {
        AbstractComputer comp = new CoderComputer();
        comp.startUp();
        comp = new MilitaryComputer();
        comp.startUp();

    }
}
//抽象的Computer
abstract class AbstractComputer {

    protected void powerOn() {
        System.out.println("开启电源");
    }

    protected void checkHardware() {
        System.out.println("硬件检查");
    }

    protected void loadOS() {
        System.out.println("载入操作系统");
    }

    protected void login() {
        System.out.println("小白的电脑无验证，直接进入系统");
    }

    /**
     * 启动电脑方法, 步骤固定为开启电源、系统检查、加载操作系统、用户登录。该方法为final， 防止算法框架被覆写.
     */
    public final void startUp() {
        System.out.println("------ 开机 START ------");
        powerOn();
        checkHardware();
        loadOS();
        login();
        System.out.println("------ 开机 END ------");
    }
}


//码农的计算机
class CoderComputer extends AbstractComputer {
    @Override
    protected void login() {
        System.out.println("码农只需要进行用户和密码验证就可以了");
    }
}


//军用计算机
class MilitaryComputer extends AbstractComputer {
    @Override
    protected void checkHardware() {
        super.checkHardware();
        System.out.println("检查硬件防火墙");
    }

    @Override
    protected void login() {
        System.out.println("进行指纹之别等复杂的用户验证");
    }
}

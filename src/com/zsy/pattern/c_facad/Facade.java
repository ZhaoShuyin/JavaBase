package com.zsy.pattern.c_facad;

/**
 * Title: 设计模式之外观模式
 * 介绍 :要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行
 * 例子 : Context 即为多种类的外观(门面)模式,封装了多种get和set方法
 *
 * @author Zsy
 * @date 2019/8/1 10:25
 */
public class Facade {
    public static void main(String[] args) {
        TvController tvController = new TvController();
        tvController.powerOn();
        tvController.nextChannel();
        tvController.turnUp();
        tvController.powerOff();
    }
}

/**
 * 外观模式 (为多个复杂的子系统提供一个一致的接口，使这些子系统更加容易被访问)
 */
class TvController {
    //电源功能
    private PowerSystem mPowerSystem = new PowerSystem();
    //音量功能
    private VoiceSystem mVoiceSystem = new VoiceSystem();
    //频道功能
    private ChannelSystem mChannelSystem = new ChannelSystem();

    public void powerOn() {
        mPowerSystem.powerOn();
    }

    public void powerOff() {
        mPowerSystem.powerOff();
    }

    public void turnUp() {
        mVoiceSystem.turnUp();
    }

    public void turnDown() {
        mVoiceSystem.turnDown();
    }

    public void nextChannel() {
        mChannelSystem.next();
    }

    public void prevChannel() {
        mChannelSystem.prev();
    }
}


/**
 * 电源控制系统
 */
class PowerSystem {
    public void powerOn() {
        System.out.println("开机");
    }

    public void powerOff() {
        System.out.println("关机");
    }
}


/**
 * 声音控制系统
 */
class VoiceSystem {
    public void turnUp() {
        System.out.println("音量增大");
    }

    public void turnDown() {
        System.out.println("音量减小");
    }
}


/**
 * 频道控制系统
 */
class ChannelSystem {
    public void next() {
        System.out.println("下一频道");
    }

    public void prev() {
        System.out.println("上一频道");
    }
}
package com.zsy.pattern.behavior.state;

/**
 * Title com.zsy.pattern.behavior.state
 * 介绍: 状态模式
 * 对有状态的对象，把复杂的“判断逻辑”提取到不同的状态对象中，
 * 允许状态对象在其内部状态发生改变时改变其行为。
 *
 * @author Zsy
 * @date 2019/8/24 0:51
 */
public class StateTest {

    public static void main(String[] args) {
        Context context = new Context();    //创建环境
        context.Handle();    //执行,切换状态
        context.Handle();    //执行,切换状态
        context.Handle();    //执行,切换状态
        context.Handle();    //执行,切换状态
    }

}

//环境类
class Context {
    private State state;

    //定义环境类的初始状态
    public Context() {
        this.state = new StateA();
    }

    //设置新状态
    public void setState(State state) {
        this.state = state;
    }

    //读取状态
    public State getState() {
        return (state);
    }

    //对请求做处理
    public void Handle() {
        state.Handle(this);
    }
}

//抽象状态类
abstract class State {
    public abstract void Handle(Context context);
}

//具体状态A类
class StateA extends State {
    public void Handle(Context context) {
        System.out.println("当前状态是 A.");
        context.setState(new StateB());
    }
}

//具体状态B类
class StateB extends State {
    public void Handle(Context context) {
        System.out.println("当前状态是 B.");
        context.setState(new StateA());
    }
}
package com.zsy.pattern.behavior.strategy;

public class StrategyPattern {
    public static void main(String[] args) {
        Context c = new Context();
        BaseStrategy base = new ConcreteStrategyA();
        c.setStrategy(base);//使用A策略
        c.strategyMethod();

        System.out.println("-----------------");
        base = new ConcreteStrategyB();
        c.setStrategy(base);//使用B策略
        c.strategyMethod();
    }
}

//抽象策略类
interface BaseStrategy {
    public void strategyMethod();    //策略方法
}

//具体策略类A
class ConcreteStrategyA implements BaseStrategy {
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问！");
    }
}

//具体策略类B
class ConcreteStrategyB implements BaseStrategy {
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被访问！");
    }
}

//环境类
class Context {
    private BaseStrategy strategy;

    public BaseStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(BaseStrategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod() {
        strategy.strategyMethod();
    }
}
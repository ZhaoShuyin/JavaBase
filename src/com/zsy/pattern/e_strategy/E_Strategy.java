package com.zsy.pattern.e_strategy;

/**
 * Title: 设计模式之策略模式
 * 介绍: 策略模式定义了一系列的算法，并将每一个算法封装起来，而且使它们还可以相互替换。策略模式让算法独立于使用它的客户而独立变化。
 * 例子: Tween(补间)动画 动画插值器Interpolator的使用
 *
 * @author Zsy
 * @date 2019/8/1 17:06
 */
public class E_Strategy {
    public static void main(String[] args) {
        double paramA = 2;
        double paramB = 2;

        System.out.println("------------ 策略模式  ----------------");
        System.out.println("加法结果是：" + calc(new AddStrategy(), paramA, paramB));
        System.out.println("减法结果是：" + calc(new SubStrategy(), paramA, paramB));
        System.out.println("乘法结果是：" + calc(new MultiStrategy(), paramA, paramB));
        System.out.println("除法结果是：" + calc(new DivStrategy(), paramA, paramB));
    }

    //执行方法
    public static double calc(Strategy strategy, double paramA, double paramB) {
        Calc calc = new Calc();
        calc.setStrategy(strategy);
        return calc.calc(paramA, paramB);
    }

}

//上下文环境的实现
class Calc {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public double calc(double paramA, double paramB) {
        // TODO Auto-generated method stub
        // doing something
        if (this.strategy == null) {
            throw new IllegalStateException("你还没有设置计算的策略");
        }
        return this.strategy.calc(paramA, paramB);
    }
}


//针对操作进行抽象
interface Strategy {
    public double calc(double paramA, double paramB);
}


//加法的具体实现策略
class AddStrategy implements Strategy {
    @Override
    public double calc(double paramA, double paramB) {
        System.out.println("执行加法策略...");
        return paramA + paramB;
    }
}

//减法的具体实现策略
class SubStrategy implements Strategy {
    @Override
    public double calc(double paramA, double paramB) {
        System.out.println("执行减法策略...");
        return paramA - paramB;
    }
}

//乘法的具体实现策略
class MultiStrategy implements Strategy {
    @Override
    public double calc(double paramA, double paramB) {
        System.out.println("执行乘法策略...");
        return paramA * paramB;
    }
}

//除法的具体实现策略
class DivStrategy implements Strategy {
    @Override
    public double calc(double paramA, double paramB) {
        System.out.println("执行除法策略...");
        if (paramB == 0) {
            throw new IllegalArgumentException("除数不能为0!");
        }
        return paramA / paramB;
    }
}


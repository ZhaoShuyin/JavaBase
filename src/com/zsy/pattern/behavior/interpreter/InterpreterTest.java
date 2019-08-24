package com.zsy.pattern.behavior.interpreter;

import java.util.HashSet;
import java.util.Set;

/**
 * Title com.zsy.pattern.z_interpreter
 * 介绍:解释器模式
 * 给分析对象定义一个语言，并定义该语言的文法表示，再设计一个解析器来解释语言中的句子。
 * 也就是说，用编译语言的方式来分析应用中的实例。
 * 这种模式实现了文法表达式处理的接口，该接口解释一个特定的上下文。
 *
 * @author Zsy
 * @date 2019/8/24 0:03
 */
public class InterpreterTest {

    /**/
    public static void main(String[] args) {
        Context bus = new Context();
        bus.freeRide("韶关的老人");
        bus.freeRide("韶关的年轻人");
        bus.freeRide("广州的妇女");
        bus.freeRide("广州的儿童");
        bus.freeRide("山东的儿童");
    }

}


//解释规则接口
interface Expression {
    public boolean interpret(String info);
}

//解释规则表达式类
class TerminalExpression implements Expression {
    private Set<String> set = new HashSet<String>();

    public TerminalExpression(String[] data) {
        for (int i = 0; i < data.length; i++) set.add(data[i]);
    }

    public boolean interpret(String info) {
        if (set.contains(info)) {
            return true;
        }
        return false;
    }
}

//非终结符表达式类
class AndExpression implements Expression {
    private Expression city = null;
    private Expression person = null;

    public AndExpression(Expression city, Expression person) {
        this.city = city;
        this.person = person;
    }

    public boolean interpret(String info) {
        String s[] = info.split("的");
        return city.interpret(s[0]) && person.interpret(s[1]);
    }
}

//环境类
class Context {
    private String[] citys = {"韶关", "广州"};
    private String[] persons = {"老人", "妇女", "儿童"};
    private Expression cityPerson;

    public Context() {
        Expression city = new TerminalExpression(citys);
        Expression person = new TerminalExpression(persons);
        cityPerson = new AndExpression(city, person);
    }

    public void freeRide(String info) {
        boolean ok = cityPerson.interpret(info);
        if (ok) {
            System.out.println("您是" + info + "，您本次乘车免费！");
        } else {
            System.out.println(info + "，您不是免费人员，本次乘车扣费2元！");
        }
    }
}
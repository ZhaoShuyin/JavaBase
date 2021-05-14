package com.zsy.pattern.behavior.chain;

/**
 * Title: 设计模式之责任链模式
 * 介绍:为了避免请求发送者与多个请求处理者耦合在一起，
 * *    将所有请求的处理者通过前一对象记住其下一个对象的引用而连成一条链；
 * *    当有请求发生时，可将请求沿着这条链传递，直到有对象处理它为止。
 * <p>
 * 例子:ViewGroup中对事件处理
 *
 * @author Zsy
 * @date 2019/8/2 11:12
 */
public class ChainTest {

    public static void main(String[] args) {

        Leader leader = new GroupLeader();
        Leader director = new Director();
        Leader manager = new Manager();
        Leader boss = new Boss();
        leader.setLeader(director);
        director.setLeader(manager);
        manager.setLeader(boss);

        ProgramApe ape = new ProgramApe((int) (Math.random() * 30000));
        leader.handleRequest(ape);
    }

}


/**
 * 程序猿类
 */
class ProgramApe {
    private int expenses;// 声明整型成员变量表示出差费用
    private String apply = "申请经费";// 声明字符串型成员变量表示差旅申请

    public ProgramApe(int expenses) { //含参构造方法
        this.expenses = expenses;
    }

    public int getExpenses() {//获取程序员具体的差旅费用
        return expenses;
    }

    public String getApply() {//获取差旅费申请
        return apply;
    }

    public void approval(String name) {
        System.out.println(apply + " ->" + expenses + " - " + name + " 批准");
    }
}


/**
 * 领导人抽象类
 */
abstract class Leader {
    private int expenses;          // 当前领导能批复的金额
    private Leader mSuperiorLeader;// 上级领导

    public Leader(int expenses) {
        this.expenses = expenses;
    }

    protected abstract void reply(ProgramApe ape); //回应程序猿

    public void handleRequest(ProgramApe ape) { //处理请求
        if (ape.getExpenses() <= expenses) {//如果说程序猿申请的money在当前领导的批复范围内
            reply(ape);
        } else {
            if (null != mSuperiorLeader) {          //否则看看当前领导有木有上级
                mSuperiorLeader.handleRequest(ape); // 有的话简单撒直接扔给上级处理即可
            } else {
                System.out.println("is too more");
            }
        }
    }

    public void setLeader(Leader superiorLeader) {
        this.mSuperiorLeader = superiorLeader;
    }
}


/**
 * 小组长类
 */
class GroupLeader extends Leader {

    public GroupLeader() {
        super(1000);//权限为1000
    }

    @Override
    protected void reply(ProgramApe ape) {
        ape.approval("GroupLeader");
    }
}


/**
 * 项目主管类
 */
class Director extends Leader {
    public Director() {
        super(5000);  //权限5000
    }

    @Override
    protected void reply(ProgramApe ape) {
        ape.approval("Director");
    }
}

/**
 * 部门经理类
 */
class Manager extends Leader {
    public Manager() {
        super(10000); //权限10000
    }

    @Override
    protected void reply(ProgramApe ape) {
        ape.approval("Manager");
    }
}

/**
 * 老总类
 */
class Boss extends Leader {
    public Boss() {
        super(50000);//权限50000
    }

    @Override
    protected void reply(ProgramApe ape) {
        ape.approval("Boss");
    }
}
package com.zsy.pattern.behavior.command;

/**
 * Title: 设计模式之命令模式
 *
 * @author Zsy
 * @date 2019/8/2 11:15
 */
public class CommandTest {
    public static void main(String[] args) {
        Client client = new Client();
        client.assembleAction();
    }
}


//命令对象和接受者对象的组装类[客户角色].
// 我这把类名定义成ClientRole更方便读者理解这只是命令模式中的一个客户角色，而不是我们常规意义上说的客户端
class Client {
    //组装操作
    public void assembleAction() {
        //创建一个命令接收者
        Receiver receiver = new Receiver();                //创建一个命令的具体实现对象，并指定命令接收者
        ICommand command1 = new CommandA(receiver);
        ICommand command2 = new CommandB(receiver);

        Invoker invoker = new Invoker();                             //创建一个命令调用者
        invoker.setCommand1(command1);                               //为调用者指定命令对象1
        invoker.setCommand2(command2);                               //为调用者指定命令对象2
        invoker.invoke(0);                                     //发起调用命令请求
        invoker.invoke(1);                                     //发起调用命令请求
    }
}


//命令的具体执行类[接收者角色], 命令接收者可以是任意的类，只要实现了命令要求实现的相应功能即可。
class Receiver {
    private PeopleBean people;
    private PeopleBean peopleCache = new PeopleBean();//具体命令操作的缓存栈，用于回滚。这里为了方便就用一个PeopleBean来代替

    public Receiver() {
        this.people = new PeopleBean(-1, "NULL");//初始化年龄为-1，姓名为NULL
    }

    public Receiver(PeopleBean people) {
        this.people = people;
    }

    public void opActionUpdateAge(int age) {
        System.out.println("执行命令前：" + people.toString());
        this.people.update(age);
        System.out.println("执行命令后：" + people.toString() + "\n");
    }

    public void opActionUpdateName(String name) {
        System.out.println("执行命令前：" + people.toString());
        this.people.update(name);
        System.out.println("执行命令后：" + people.toString() + "\n");
    }

    //回滚操作，用于撤销opAction执行的改变
    public void rollBackAge() {
        people.setAge(peopleCache.getAge());
        System.out.println("命令回滚后：" + people.toString() + "\n");
    }

    //回滚操作，用于撤销opAction执行的改变
    public void rollBackName() {
        people.setName(peopleCache.getName());
        System.out.println("命令回滚后：" + people.toString() + "\n");
    }
}


//命令调用>调用者角色
class Invoker {
    private ICommand command1;
    private ICommand command2;
    //持有多个命令对象[实际的情况也可能是一个命令对象的集合来保存命令对象]

    public void setCommand1(ICommand command1) {
        this.command1 = command1;
    }

    public void setCommand2(ICommand command2) {
        this.command2 = command2;
    }

    //执行正常命令，1执行回滚命令 , //可以根据具体情况选择执行某些命令
    public void invoke(int args) {
        if (args == 0) {
            command1.execute();
            command2.execute();
        } else if (args == 1) {
            command1.undo();
            command2.undo();
        }
    }
}


/**
 * 命令接口    [命令角色]
 */
interface ICommand {
    public void execute(); //基本命令-执行

    public void undo();    //基本命令-撤销

    public void redo();    //基本命令-重复
}

//更新年龄的命令类  [ 具体命令角色 ]
class CommandA implements ICommand {
    private Receiver receiver;

    public CommandA(Receiver receiverRole1) {
        this.receiver = receiverRole1;
    }

    @Override
    public void execute() {
        //可以加入命令排队等等，未执行的命令支持redo操作
        receiver.opActionUpdateAge(1001);//执行具体的命令操作
    }

    @Override
    public void undo() {
        receiver.rollBackAge();//执行具体的撤销回滚操作
    }

    @Override
    public void redo() {
        //在命令执行前可以修改命令的执行
    }
}


//更新姓名的命令类[具体命令角色]
class CommandB implements ICommand {
    private Receiver receiver;

    public CommandB(Receiver receiverRole1) {
        this.receiver = receiverRole1;
    }

    @Override
    public void execute() {
        //可以加入命令排队等等，未执行的命令支持redo操作
        receiver.opActionUpdateName("lijunhuayc");//执行具体的命令操作
    }

    @Override
    public void undo() {
        receiver.rollBackName();//执行具体的撤销回滚操作
    }

    @Override
    public void redo() {
        //在命令执行前可以修改命令的执行
    }

}


//辅助类，作为接收者Receiver的成员，包含两个属性，用来观察命令的执行情况
class PeopleBean {
    private int age = -1;             //年龄
    private String name = "NULL";     //姓名

    public PeopleBean() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PeopleBean(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void update(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void update(int age) {
        this.age = age;
    }

    public void update(String name) {
        this.name = name;
    }

    /**
     * @return 返回一个PeopleBean的克隆对象
     */
    protected PeopleBean clone() {
        return new PeopleBean(age, name);
    }

    @Override
    public String toString() {
        return " 【年龄：" + age + "\t姓名：" + name + "】";
    }
}
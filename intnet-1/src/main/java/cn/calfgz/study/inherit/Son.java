package cn.calfgz.study.inherit;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-22 16:27
 */
public class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.println(6);
    }

    Son() {
        System.out.println(7);
    }
    {
        System.out.println(8);
    }

    public int test() {
        System.out.println(9);
        return 0;
    }

    public static int method() {
        System.out.println(10);
        return 0;
    }

    /**
     * 类的初始化过程
     * 1. 一个类要创建实例需要先加载并初始化该类
     *   main方法所在的类需要先加载和初始化
     * 2. 一个子类要初始化需要先初始化父类
     * 3. 一个类初始化就是执行<clinit>()方法
     *   <clinit>()方法由静态类变量显示赋值代码和静态代码块组成
     *   类变量显示赋值代码和静态代码块代码从上到下顺序执行
     *   <linit>()方法只执行一次
     *
     * 实例初始化过程
     * 1. 实例初始化就是执行init()方法
     *  - init()方法可能重载有多个，有几个构造器就有几个init()方法
     *  - init()方法由非静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成
     *  - 非静态实例变量显示赋值代码和非静态代码块代码从上到下顺序执行，而对应构造器的代码最后执行
     *  - 每次创建实例对象，调用对应构造器，执行的就是对应的init()方法。
     *  - init()方法的首行是super()或super(实参列表)，即父类的init方法
     * @param args
     */
    public static void main(String[] args) {
        // 5,1,10,6,4,3,2,9,8,7
        Son s1 = new Son();
        System.out.println();
        //4,3,2,9,8,7
        Son s2 = new Son();
    }
}

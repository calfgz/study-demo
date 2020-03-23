package cn.calfgz.study.single;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-22 16:18
 */
public class Singleton5 {
    private Singleton5() {};

    /**
     * 在内部类被加载和初始化时，才创建INSTANCE实例对象
     * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是要单独去加载和初始化的。
     * 因为是在内部类加载和初始化时创建的，因此是线程安全的
     */
    private static class Inner{
        private static Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return Inner.INSTANCE;
    }
}

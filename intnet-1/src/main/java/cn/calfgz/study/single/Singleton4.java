package cn.calfgz.study.single;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-22 14:12
 */
public class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {}

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}

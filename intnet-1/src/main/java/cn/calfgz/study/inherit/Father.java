package cn.calfgz.study.inherit;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-22 16:24
 */
public class Father {
    private int i = test();

    private static int j = method();

    static {
        System.out.println(1);
    }

    Father() {
        System.out.println(2);
    }

    {
        System.out.println(3);
    }

    private static int method() {
        System.out.println(5);
        return 1;
    }

    private int test() {
        System.out.println("4");
        return 1;
    }
}

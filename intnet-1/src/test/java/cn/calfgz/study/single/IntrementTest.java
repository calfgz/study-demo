package cn.calfgz.study.single;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-21 16:53
 */
public class IntrementTest {
    public static void main(String[] args) {
        intrement();
    }

    //自增
    private static void intrement() {
        int i = 1;
        //1
        i = i++;
        //j=1 i=2
        int j = i++;
        // k = 2 + 3 * 3
        int k = i + ++i * i++;
        //4 1 11
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }

    private static void singleton() {

    }
}

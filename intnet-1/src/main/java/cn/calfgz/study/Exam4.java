package cn.calfgz.study;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-23 15:36
 */
public class Exam4 {
    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 2;
        int[] arr = {1,2,3,4,5};
        MyData my = new MyData();

        change(i, str, num, arr, my);

        //2, hello, 2, 22345, 11
        System.out.println("i = " + i);
        System.out.println("str = " + str);
        System.out.println("num = " + num);
        System.out.println("arr = " + arr);
        System.out.println("my.a = " + my.a);
    }

    public static void change(int j, String s, Integer n, int[] a, MyData m) {
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }
}

class MyData{
    int a = 10;
}

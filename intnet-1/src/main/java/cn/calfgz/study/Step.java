package cn.calfgz.study;

/**
 * 有n步台阶，一次只能上1步或2步，共有多少种走法？
 * 1. 递归
 * 2. 循环迭代
 * @author calfgz
 * @description:
 * @date 2020-03-23 16:37
 */
public class Step {

    /**
     * 递归
     * 实现f(n):求n步台阶，一共有几种走法
     */
    public int f(int n) throws IllegalAccessException {
        if (n < 1) {
            throw new IllegalAccessException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return f(n-2) + f(n - 1);
    }

    /**
     * 循环迭代
     * @param n
     * @return
     * @throws IllegalAccessException
     */
    public int loop(int n) throws IllegalAccessException {
        if (n < 1) {
            throw new IllegalAccessException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }

        //初始化为走到第二级台阶的走法
        int one = 2;
        //初始化为走到第一级台阶的走法
        int two = 1;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            //最后跨2步 + 最后跨1步的走法
            sum = two + one;
            two = one;
            one = sum;
        }
        return sum;

    }
}

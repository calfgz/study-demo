package cn.calfgz.study.single;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-22 14:01
 */
public class SIngletonTest {

    @Test
    public void singleton1() {
        Singleton1 s = Singleton1.INSTANCE;
        System.out.println(s);
    }

    @Test
    public void singleton2() {
        Singleton2 s = Singleton2.INSTANCE;
        System.out.println(s);
    }

    @Test
    public void singleton3() {
        Singleton3 s = Singleton3.INSTANCE;
        System.out.println(s);
    }

    @Test
    public void singleton4() {
        Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }

    @Test
    public void singleton5() throws ExecutionException, InterruptedException {
        Callable<Singleton4> c = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        //es.submit(c);

        Future<Singleton4> f1 = es.submit(c);
        Future<Singleton4> f2 = es.submit(c);

        Singleton4 s1 = f1.get();
        Singleton4 s2 = f2.get();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);

        es.shutdown();
    }

    @Test
    public void singleton6() throws ExecutionException, InterruptedException {
        Callable<Singleton5> c = new Callable<Singleton5>() {
            @Override
            public Singleton5 call() throws Exception {
                return Singleton5.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        //es.submit(c);

        Future<Singleton5> f1 = es.submit(c);
        Future<Singleton5> f2 = es.submit(c);

        Singleton5 s1 = f1.get();
        Singleton5 s2 = f2.get();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);

        es.shutdown();
    }

}

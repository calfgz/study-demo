package cn.calfgz.study;

import org.junit.Test;

import static org.junit.Assert.*;

public class StepTest {

    @Test
    public void f() throws IllegalAccessException {
        Step step = new Step();
        long start = System.currentTimeMillis();
        System.out.println(step.f(40));
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void loop() throws IllegalAccessException {
        Step step = new Step();
        long start = System.currentTimeMillis();
        System.out.println(step.loop(40));
        System.out.println(System.currentTimeMillis() - start);
    }
}
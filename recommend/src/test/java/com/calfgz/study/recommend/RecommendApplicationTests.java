package com.calfgz.study.recommend;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendApplicationTests {

    @Test
    public void contextLoads() {
        NormalDistribution normalDistribution = new NormalDistribution();
        System.out.println(normalDistribution.cumulativeProbability(0.99));
        System.out.println(normalDistribution.cumulativeProbability(0.89));
    }

    public static void main(String[] args) {
        NormalDistribution normalDistribution = new NormalDistribution();
        System.out.println(normalDistribution.cumulativeProbability(0.99));
        System.out.println(normalDistribution.cumulativeProbability(0.89));
        System.out.println(normalDistribution.cumulativeProbability(0.79));
        System.out.println(normalDistribution.cumulativeProbability(0.69));
        System.out.println(normalDistribution.cumulativeProbability(0.59));
        System.out.println(normalDistribution.cumulativeProbability(0.49));

        System.out.println((Math.exp(5) * 4)/(1+ Math.exp(5)) - 2);
        System.out.println((Math.exp(4) * 4)/(1+ Math.exp(4)) - 2);
        System.out.println((Math.exp(3) * 4)/(1+ Math.exp(3)) - 2);
        System.out.println((Math.exp(2) * 4)/(1+ Math.exp(2)) - 2);
        System.out.println((Math.exp(1) * 4)/(1+ Math.exp(1)) - 2);
        System.out.println((Math.exp(0.8) * 4)/(1+ Math.exp(0.8)) - 2);
        System.out.println((Math.exp(0.5) * 4)/(1+ Math.exp(0.5)) - 2);
        System.out.println((Math.exp(0.3) * 4)/(1+ Math.exp(0.3)) - 2);
        System.out.println((Math.exp(0) * 4)/(1+ Math.exp(0)) - 2);
    }

}

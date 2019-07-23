package com.calfgz.study.redislock.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author calfgz
 * @description:
 * @date 2019-07-22 22:31
 */
@RestController
public class IndexController {

    @Autowired
    RedissonClient redissonClient;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/deduct.do")
    public String deductStock() {
        String lockKey = "product_1";
        String clientId = UUID.randomUUID().toString();
        String productStockKey = "product_stock_1";

        try {
            //加锁
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);

            if (result == null || !result) {
                return "try again later.";
            }

            String value = stringRedisTemplate.opsForValue().get(productStockKey);
            //拿库存
            int stock = Integer.parseInt(value);
            if (stock > 0) {
                stringRedisTemplate.opsForValue().set(productStockKey, String.valueOf(stock - 1));
                System.out.println("扣减成功，剩余库存：" + (stock -1));
            } else {
                System.out.println("扣减失败，库存不足.");
            }
        } finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                //释放锁
                stringRedisTemplate.delete(lockKey);
            }
        }
        return "ok";
    }

    @GetMapping("/setStock.do")
    public String setStock() {
        String productStockKey = "product_stock_1";
        stringRedisTemplate.opsForValue().set(productStockKey, "10");
        return "ok";
    }

    @GetMapping("/deduct2.do")
    public String deductStock2() {
        String key = "lock_key";
        String productStockKey = "product_stock_1";
        RLock lock = redissonClient.getLock(key);
        lock.lock();
        String value = stringRedisTemplate.opsForValue().get(productStockKey);
        //拿库存
        int stock = Integer.parseInt(value);
        if (stock > 0) {
            stringRedisTemplate.opsForValue().set(productStockKey, String.valueOf(stock - 1));
            System.out.println("扣减成功，剩余库存：" + (stock -1));
        } else {
            System.out.println("扣减失败，库存不足.");
        }

        lock.unlock();
        return "ok";
    }

}

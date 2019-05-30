package com.calfgz.study.recommend.controller;

import com.calfgz.study.recommend.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongwm
 * @title: KafkaController
 * @description:
 * @date 2019-05-30 13:54
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping(value = "/kafka/send.do")
    public String send(@RequestParam(name = "content") String content) {
        kafkaProducer.send(content);
        return "ok";
    }
}

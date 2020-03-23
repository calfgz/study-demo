package com.calfgz.study.recommend.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author calfgz
 * @title: KafkaProducer
 * @description: Kafka生产者
 * @date 2019-05-30 12:32
 */
@Component
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send("test", message);
    }
}

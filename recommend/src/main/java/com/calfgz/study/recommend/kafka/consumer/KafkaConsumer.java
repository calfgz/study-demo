package com.calfgz.study.recommend.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhongwm
 * @title: KafkaConsumer
 * @description: 消费者
 * @date 2019-05-30 13:51
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test")
    public void listener(ConsumerRecord<?, ?> record) {
        System.out.printf("topic = %s, offset = %d, value = %s, partition = %d \n", record.topic(), record.offset(), record.value(), record.partition());
    }
}

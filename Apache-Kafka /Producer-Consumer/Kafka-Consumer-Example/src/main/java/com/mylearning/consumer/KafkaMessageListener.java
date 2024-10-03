package com.mylearning.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup-1")
    public void consume1(String message) {
        log.info("Consumer 1 consumed the message -> {}", message+1);
    }

    // specific partition and offset
    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup-1",topicPartitions = {
            @TopicPartition(topic = "myTopicDemo",partitions = {"2"}, partitionOffsets = @PartitionOffset(partition = "2", initialOffset = "0")) // Start from offset 0 for partition 2)
    })
    public void consume2(String message) {
        log.info("Consumer 2 consumed the message -> {}", message+2);
    }

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup-1")
    public void consume3(String message) {
        log.info("Consumer 3 consumed the message -> {}", message+3);
    }

    @KafkaListener(topics = "myTopicDemo", groupId = "DemoGroup-1")
    public void consume4(String message) {
        log.info("Consumer 4 consumed the message -> {}", message+4);
    }
}

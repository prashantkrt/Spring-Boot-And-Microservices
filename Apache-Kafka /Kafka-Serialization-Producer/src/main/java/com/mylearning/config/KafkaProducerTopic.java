package com.mylearning.config;



import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerTopic {

    /*
     * creating a topic programmatically
     * */

    @Bean
    public NewTopic createTopic() {
        return new NewTopic("myTopicDemo2", 3, (short) 1);
    }


//    Map<String, String> config = new HashMap<>();
//    config.put("retention.ms", "604800000");  // Retain data for 7 days (in milliseconds)
//    config.put("cleanup.policy", "compact");  // Log compaction instead of deleting records
//    config.put("compression.type", "gzip");   // Compress data using GZIP
//
//    return new NewTopic("myTopicDemo", 3, (short) 2)
//            .configs(config);  // Passing the topic-level configurations

    // if we don't want to define in app.props file

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }


    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

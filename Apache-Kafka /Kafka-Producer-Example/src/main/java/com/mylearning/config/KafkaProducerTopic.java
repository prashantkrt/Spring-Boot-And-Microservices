package com.mylearning.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerTopic {

    /*
     * creating a topic programmatically
     * */

    @Bean(name="myConfig")
    public NewTopic createTopic() {
        return new NewTopic("myTopicDemo", 3, (short) 1);
    }


// also we can add this way
//    Map<String, String> config = new HashMap<>();
//    config.put("retention.ms", "604800000");  // Retain data for 7 days (in milliseconds)
//    config.put("cleanup.policy", "compact");  // Log compaction instead of deleting records
//    config.put("compression.type", "gzip");   // Compress data using GZIP
//
//    return new NewTopic("myTopicDemo", 3, (short) 2)
//            .configs(config);  // Passing the topic-level configurations


}

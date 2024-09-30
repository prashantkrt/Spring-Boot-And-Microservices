package com.mylearning.controller;

import com.mylearning.dto.PaymentRequest;
import com.mylearning.dto.PaytmRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class PaytmController {

    @Value("${paytm.producer.topic1.name}")
    private String topicName;

    @Value("${paytm.producer.topic2.name}")
    private String paymentTopicName;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping(value = "/publish/{message}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessage(@PathVariable("message") String message) {
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send(topicName, message);
        }
    }
//  Postman request
//    {
//        "payload": {
//        "sourceAccount": "PradeepICICI",
//                "destinationAccount": "KumarHDFC",
//                "amount": 2300.00
//    }
//    }

    @RequestMapping(value = "/paytm/payment", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public String doPayment(@RequestBody PaytmRequest<PaymentRequest> paytmRequest) {
        for (int i = 1; i <= 500; i++) {
            PaymentRequest paymentRequest = paytmRequest.getPayload();
            paymentRequest.setTransactionId(UUID.randomUUID().toString());
            paymentRequest.setTransactionDate(new Date());
            kafkaTemplate.send(paymentTopicName, paymentRequest);
        }
        return "payment instantiate successfully...";
    }

}

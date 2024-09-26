package com.mylearning.scheduler.springbootscheduler.service;

import com.mylearning.scheduler.springbootscheduler.model.Order;
import com.mylearning.scheduler.springbootscheduler.repository.OrderRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    /*
     * On application startup string will run this service method
     * using @PostConstruct
     * */
    @PostConstruct
    public void insertOrder() {
        List<Order> orders = IntStream.rangeClosed(1, 20)
                .mapToObj(i ->
                        new Order("order" + i, new Random().nextInt(5), new Random().nextDouble(100000))).collect(Collectors.toList());
        orderRepo.saveAll(orders);
    }

    /*
    *
    *  Generating a random double between 1 and 99999 (5 digits max)
       double randomDouble = 1 + (99999 - 1) * random.nextDouble();
    *  At least 1
    * .mapToObj(i -> { return
           new Order ("order" + i, 1 + new Random().nextInt(4), new Random().nextDouble(100000))).collect(Collectors.toList()
           }
         );
    *
    *
    * */

    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

}

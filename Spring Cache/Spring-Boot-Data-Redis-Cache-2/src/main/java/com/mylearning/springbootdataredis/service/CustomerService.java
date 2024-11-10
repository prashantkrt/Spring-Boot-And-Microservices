package com.mylearning.springbootdataredis.service;

import com.mylearning.springbootdataredis.hash.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final String HASH_KEY = "customer:hash";

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @CachePut(key = "#customer.id",value = "customerCache")
    public Customer saveOrUpdateCustomer(Customer customer) {
        redisTemplate.opsForHash().put(HASH_KEY, customer.getId(), customer);
        return customer;
    }

    @Cacheable(value = "customerCache",key = "#id")
    public Customer getCustomerById(Integer id) {
        return (Customer) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    @CacheEvict(key = "#id",value = "customerCache")
    public void deleteCustomer(Integer id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
    }

    @Cacheable(value = "customerCache")
    public List<Customer> getAllCustomers() {
        List<Object> customers = redisTemplate.opsForHash().values(HASH_KEY);
        return customers.stream()
                .map(customer -> (Customer) customer)
                .collect(Collectors.toList());
    }

    @CachePut(key = "#id",value = "customerCache")
    public Customer updateCustomer(int id, Customer customer) {
        Customer existingCustomer = (Customer) redisTemplate.opsForHash().get(HASH_KEY, id);
        if (existingCustomer != null) {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setDateOfBirth(customer.getDateOfBirth());
            redisTemplate.opsForHash().put(HASH_KEY, id, existingCustomer);
            return existingCustomer;
        } else {
            throw new RuntimeException("Customer not found !");
        }
    }
}

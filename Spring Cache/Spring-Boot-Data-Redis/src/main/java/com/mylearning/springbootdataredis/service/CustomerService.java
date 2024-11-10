package com.mylearning.springbootdataredis.service;

import com.mylearning.springbootdataredis.hash.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final String HASH_KEY = "customer:hash";

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    // CRUD Operation

    // Saving or updating a customer
    public Customer saveOrUpdateCustomer(Customer customer) {
        // customer:hash 1 {"id":1, "name": "John Doe", "email": "john.doe@example.com"}
        // Storing the customer object in Redis hash, using customer.getId() as the field.
        redisTemplate.opsForHash().put(HASH_KEY, customer.getId(), customer);
        return customer;
        /*
         *   customer:hash 1 {"id":1, "name":"John Doe", "email":"john.doe@example.com"
         *
         *   customer:hash is the Redis hash key.
         *   1 is the field key (customer ID).
         *   The serialized customer object is the value associated with the field key.
         *
         * */
    }

    public Customer getCustomerById(Integer id) {
        // Retrieving the customer from Redis hash using the customer ID as the field
        return (Customer) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public void deleteCustomer(Integer id) {
        // Removing the customer from Redis hash by the field (ID)
        redisTemplate.opsForHash().delete(HASH_KEY, id);
    }

    public List<Customer> getAllCustomers() {
        // return (List)redisTemplate.opsForHash().values(HASH_KEY);
        // Fetch all customer objects from the Redis hash
        List<Object> customers = redisTemplate.opsForHash().values(HASH_KEY);

        List<Customer> customerList = customers.stream()
                .map(customer -> (Customer) customer)
                .collect(Collectors.toList());

        return customerList;
    }

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

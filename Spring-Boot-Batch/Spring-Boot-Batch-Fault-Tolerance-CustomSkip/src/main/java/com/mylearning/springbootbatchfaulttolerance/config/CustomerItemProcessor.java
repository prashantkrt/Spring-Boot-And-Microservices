package com.mylearning.springbootbatchfaulttolerance.config;

import com.mylearning.springbootbatchfaulttolerance.entity.Customer;
import jakarta.persistence.EntityManager;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Customer process(Customer item) throws Exception {

        if (item == null) {
            return null;
        }

        if (item.getVersion() == null) {
            // Optionally initialize version or handle accordingly
            item.setVersion(1L);  // If optimistic locking is required
        }

        item = entityManager.merge(item);

        System.out.println("Customer details before merge: " + item);

        if (item.getFirstName() == null || item.getLastName() == null || item.getEmail() == null) {
            throw new IllegalArgumentException("Required fields are missing");
        }

        // Add logging for individual fields
        System.out.println("FirstName: " + item.getFirstName());
        System.out.println("LastName: " + item.getLastName());
        System.out.println("Email: " + item.getEmail());
        System.out.println("Country: " + item.getCountry());
        System.out.println("DateOfBirth: " + item.getDateOfBirth());
        System.out.println("Age: " + item.getAge());

        // Do all validation checks

        // Exclude customers from a specific country
        if ("Canada".equalsIgnoreCase(item.getCountry())) {
            return null; // Returning null skips this item
        }

        // Validate email
        if (item.getEmail() == null || !item.getEmail().contains("@")) {
            return null;   // Returning null will cause the item to be skipped
        }

        //validate age
        int age = item.getAge();
        if (age < 18 || age > 60) {
            return null;   // Returning null will cause the item to be skipped
        }

        // Normalize first and last names
        item.setFirstName(item.getFirstName().trim().toUpperCase());
        item.setLastName(item.getLastName().trim().toUpperCase());

        // Mask contact number
        String contactNo = item.getContactNo();
        if (contactNo != null && contactNo.length() > 4) {
            item.setContactNo("******" + contactNo.substring(contactNo.length() - 4));
        }

        // Enrich customer data with country code
        switch (item.getCountry().toLowerCase()) {
            case "united states":
                item.setCountry("US");
                break;
            case "india":
                item.setCountry("IN");
                break;
            case "canada":
                item.setCountry("CA");
                break;
            default:
                item.setCountry("UNKNOWN");
        }

        // Convert dateOfBirth to a specific format
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = formatter.format(formatter.parse(item.getDateOfBirth()));
        item.setDateOfBirth(formattedDate);

        return item;

    }
}

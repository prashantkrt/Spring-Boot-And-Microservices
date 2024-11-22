package com.mylearning.springbootbatchfaulttolerance.config;

import com.mylearning.springbootbatchfaulttolerance.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

import java.text.SimpleDateFormat;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer item) throws Exception {

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


        // validation

        // Exclude customers from a specific country
        if ("UNKNOWN".equalsIgnoreCase(item.getCountry())) {
            return null; // Returning null skips this item
        }

        // Validate email
        if (item.getEmail() == null || !item.getEmail().contains("@")) {
            return null;
        }
        //validate age
        int age = item.getAge();
        if (age < 18 || age> 60) {
            return null;
        }

        return item;

    }
}

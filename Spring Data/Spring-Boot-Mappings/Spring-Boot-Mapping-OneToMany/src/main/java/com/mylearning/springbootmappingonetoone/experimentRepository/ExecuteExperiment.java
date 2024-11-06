package com.mylearning.springbootmappingonetoone.experimentRepository;

import com.mylearning.springbootmappingonetoone.experiment.Category;
import com.mylearning.springbootmappingonetoone.experiment.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ExecuteExperiment implements CommandLineRunner {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public void run(String... args) throws Exception {

        Category category = new Category();
        category.setCategoryId(10002);
        category.setCategoryDescription("Category Description");
        category.setCategoryName("Category Name");
        categoryRepo.save(category);

        Item item = new Item();
        item.setItemId(102);
        item.setItemDescription("Product Description");
        item.setItemName("Product Name");
        item.setItemPrice(20000.00);
        category.setItems(Set.of(item));
        itemRepo.save(item);
    }
}

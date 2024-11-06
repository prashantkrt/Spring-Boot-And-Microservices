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

        // using this for making some experimental observation over database tables
        Category category = new Category();
        category.setCategoryId(11);
        category.setCategoryDescription("Category Description");
        category.setCategoryName("Category Name");
        categoryRepo.save(category); // will be saved

        Item item = new Item();
        item.setItemId(1002);
        item.setItemDescription("Product Description");
        item.setItemName("Product Name");
        item.setItemPrice(20000.00);
        category.setItems(Set.of(item));
        //item.setCategory(category); //
        itemRepo.save(item); // will be saved


    }
}

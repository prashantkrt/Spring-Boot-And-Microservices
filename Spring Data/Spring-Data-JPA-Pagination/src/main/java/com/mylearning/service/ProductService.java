package com.mylearning.service;

import com.mylearning.entity.Product;
import com.mylearning.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> getProductsByType(String productType) {
        return productRepository.findByProductType(productType);
    }

    public List<Product> getProductsByNameAndProductType(String name, String productType) {
        return productRepository.findByNameAndProductType(name, productType);
    }

    public Product updateProduct(int id, Product productRequest) {
        Product existingProduct = productRepository.findById(id).get(); // DB
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(existingProduct.getProductType());
        return productRepository.save(existingProduct);
    }



    /*********Sorting*********/
    // Sorting based on the given filed by the user
    public List<Product> getProductsWithSorting(String fieldName) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
    }


    /*******Pagination****/
    //    int page offset initial Value
    //    int size limit value
    public Page<Product> getProductsWithPaging(int offset, int limit) {
        return productRepository.findAll(PageRequest.of(offset, limit));
    }

    /******Pagination + Sorting*****/
    public Page<Product> getProductsWithSortingAndPagination(String fieldName, int offset, int limit) {
        return productRepository.findAll(PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, fieldName)));
        //return productRepository.findAll(PageRequest.of(offset, limit).withSort(Sort.Direction.ASC, fieldName));
    }


}

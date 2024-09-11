package com.mylearning.productservice.firstproject.condition;

public class EnableDevDataSource implements DataSourceConfig{
    @Override
    public void makeConnection() {
        System.out.println("Making connection to database for dev data source");
    }
}

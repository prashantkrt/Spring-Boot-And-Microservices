package com.mylearning.condition.condition;

public class EnableProdDataSource implements DataSourceConfig {
    @Override
    public void makeConnection() {
        System.out.println("Making connection to database of production datasource");
    }
}

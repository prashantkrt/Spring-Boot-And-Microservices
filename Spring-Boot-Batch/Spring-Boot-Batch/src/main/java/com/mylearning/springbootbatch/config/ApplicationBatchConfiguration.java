package com.mylearning.springbootbatch.config;


import com.mylearning.springbootbatch.entity.Customer;
import com.mylearning.springbootbatch.repository.CustomerRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class ApplicationBatchConfiguration {

    @Autowired
    private CustomerRepository customerRepository;

    // Item Reader
    @Bean
    public FlatFileItemReader<Customer> customerFlatFileItemReader() {

        FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<>();

        // Set the resource to point to the file in your system's Documents folder
        // reader.setResource(new FileSystemResource("/Users/username/Documents/customers.csv"));

        // Set the resource to point to the file in the classpath
        itemReader.setResource(new ClassPathResource("MOCK_DATA.csv")); // Specify the relative path within the resources folder
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Customer> lineMapper() {

        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("customerId", "firstName","lastName","email","gender","contactNo","country","dateOfBirth");

        //set it to type customer type
        BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Customer.class);

        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineMapper.setLineTokenizer(lineTokenizer);

        return lineMapper;
    }

    // Item Processor
    @Bean
    public CustomerItemProcessor customerItemProcessor() {
        return new CustomerItemProcessor();
    }

    //Item Writer
    public RepositoryItemWriter<Customer> customerRepositoryItemWriter() {
        RepositoryItemWriter<Customer> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(customerRepository);
        itemWriter.setMethodName("save");
        return itemWriter;
    }


}

package com.mylearning.springbootbatch.config;


import com.mylearning.springbootbatch.entity.Customer;
import com.mylearning.springbootbatch.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
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
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableBatchProcessing
public class ApplicationBatchConfiguration {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;


    // Item Reader
    @Bean
    public FlatFileItemReader<Customer> customerItemReader() {

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
        lineTokenizer.setNames("customerId", "firstName", "lastName", "email", "gender", "contactNo", "country", "dateOfBirth");

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

    // Item Writer
    @Bean
    public RepositoryItemWriter<Customer> customerItemWriter() {
        RepositoryItemWriter<Customer> itemWriter = new RepositoryItemWriter<>();
        itemWriter.setRepository(customerRepository);
        itemWriter.setMethodName("save");
        return itemWriter;
    }


    // Step
    @Bean
    public Step customerStep() {
        return new StepBuilder("step-1", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager) //a chunk is a way to process a large volume of data efficiently and transactionally by dividing it into smaller, manageable pieces (or "chunks").
                .reader(customerItemReader())
                .processor(customerItemProcessor())
                .writer(customerItemWriter())
                .build();

//        Here, the chunk(10) means:
//        10 items will be read, processed, and written together.
//        A single transaction will encompass these 10 items.
//        If any of these 10 items fail at any stage, the entire chunk is rolled back.
//
//        ACID Property
//        If one record in the chunk causes a failure (e.g., due to a database constraint), the transaction ensures that:
//          => All operations in the chunk are rolled back.
//          => No partial writes occur.
//          => The job can restart from the last successful chunk.
//        The TransactionManager in Spring Batch is a key component responsible for managing transactions during the execution of a batch step.
//        It ensures that the operations within a chunk (such as reading, processing, and writing data) are executed in a transactional context.

    }

    // better understanding with params
    // lets create a step-2
    @Bean(name="step2")
    public Step customerStep2(ItemReader<Customer> reader,
                             ItemProcessor<Customer, Customer> processor,
                             ItemWriter<Customer> writer,
                             JobRepository jobRepository,
                             PlatformTransactionManager transactionManager) {
        return new StepBuilder("step-2", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)  // The chunk size is 10, transaction manager is used for transactional processing
                .reader(reader)                                    // The ItemReader to read data
                .processor(processor)                               // The ItemProcessor to process each item
                .writer(writer)                                     // The ItemWriter to write processed data
                .build();
    }


    //Job
    @Bean
    public Job job() {
        ////return new JobBuilder("customers-import", jobRepository).start(step()).next(step2()).next(step3()).build();
        return new JobBuilder("customers-import", jobRepository)
                .flow(customerStep())
                .end()
                .build();
    }
}

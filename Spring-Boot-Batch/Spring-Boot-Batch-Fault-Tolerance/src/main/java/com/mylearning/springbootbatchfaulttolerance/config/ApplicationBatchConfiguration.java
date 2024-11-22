package com.mylearning.springbootbatchfaulttolerance.config;

import com.mylearning.springbootbatchfaulttolerance.entity.Customer;
import com.mylearning.springbootbatchfaulttolerance.repository.CustomerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
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
        itemReader.setResource(new ClassPathResource("MOCK_DATA_FAULT.csv"));
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Customer> lineMapper() {

        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("customerId", "firstName", "lastName", "email", "gender", "contactNo", "country", "dateOfBirth","age");

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


    //step
    @Bean(name = "customerStep")
    public Step customerStep(ItemReader<Customer> reader,
                             ItemProcessor<Customer, Customer> processor,
                             ItemWriter<Customer> writer) {
        return new StepBuilder("step-2", jobRepository)
                .<Customer, Customer>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant() //Specifies the maximum number of items that can be skipped during the step execution before the job fails.
                .skipLimit(Integer.MAX_VALUE)
                .skip(NullPointerException.class) // Specify the exception type to skip
                .taskExecutor(taskExecutor())
                .build();
    }


    //Job
    @Bean
    public Job job(Step customerStep) {
        return new JobBuilder("customers-import", jobRepository)
                .flow(customerStep)
                .end()
                .build();
    }


    // optional to create
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);// Limits the number of concurrent tasks to 10
        return taskExecutor;
    }
}

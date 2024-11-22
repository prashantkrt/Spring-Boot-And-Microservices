package com.mylearning.springbootbatchfaulttolerance.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.springbootbatchfaulttolerance.entity.Customer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;

@Slf4j
public class MyStepEventListener implements SkipListener<Customer, Number> {

    @Override
    public void onSkipInRead(Throwable t) {
        System.out.println("onSkipInRead");
        log.info("onSkipInRead A failure on read {}", t.getMessage());
    }

    @Override
    public void onSkipInWrite(Number itemNumber, Throwable t) {
        System.out.println("onSkipInWrite");
        log.info("onSkipInWrite A failure on write {} {}", t.getMessage(), itemNumber);
    }

    @SneakyThrows
    @Override
    public void onSkipInProcess(Customer customer, Throwable t) {
        System.out.println("onSkipInProcessing");
        log.info("onSkipInProcessing A failure on processing {} {} ", t.getMessage(), new ObjectMapper().writeValueAsString(customer));

    }
}

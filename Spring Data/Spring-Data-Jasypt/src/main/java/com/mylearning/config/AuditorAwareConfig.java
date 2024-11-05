package com.mylearning.config;

import com.mylearning.audits.AuditorAwareImpl;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorAwareConfig {

    @Bean("auditorAware")
    public AuditorAware<String> getAuditorAware() {
        return new AuditorAwareImpl();
    }

    //our own encryptor  optional
//    @Bean(name="jasptyStringEncryptor") // same should be this only in order to use this bean
//    public StringEncryptor getStringEncryptor() {
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//        config.setPassword("myKey");// private -key
//        config.setAlgorithm("PBEWithMD5AndDES");
//        config.setKeyObtentionIterations(1000);
//        config.setPoolSize(1);
//        config.setProviderName("SunJCE");
//        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
//        config.setStringOutputType("base64");
//        encryptor.setConfig(config);
//        return encryptor;
//    }

}

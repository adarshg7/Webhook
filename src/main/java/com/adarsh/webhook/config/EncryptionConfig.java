package com.adarsh.webhook.config;

import com.adarsh.webhook.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfig {

    @Bean
    public EncryptionUtil encryptionUtil(
            @Value("${app.encryption.password}") String password,
            @Value("${app.encryption.salt") String salt
    ){
        return new EncryptionUtil(password,salt);
    }

}

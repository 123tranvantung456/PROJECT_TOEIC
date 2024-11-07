package com.javaweb.toeic;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.javaweb.toeic.config.StorageProperties;
import com.javaweb.toeic.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(StorageProperties.class) // Enable storage properties configuration
public class ToeicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToeicApplication.class, args);
    }

    // Bean configuration for initializing storage service
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return args -> {
            storageService.init();
        };
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dfb1utqck",
                "api_key", "896436333846459",
                "api_secret", "h8HGSZmDVmQiL6jS7KjsZXz7-NA",
                "secure",true
        ));
    }
}

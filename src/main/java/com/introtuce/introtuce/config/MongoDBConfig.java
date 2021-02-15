package com.introtuce.introtuce.config;


import com.introtuce.introtuce.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {
        UserRepository.class
})
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(){
        return strings -> {

        };
    }

}

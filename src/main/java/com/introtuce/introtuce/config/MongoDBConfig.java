package com.introtuce.introtuce.config;


import com.introtuce.introtuce.repository.user.UserRepository;
import com.introtuce.introtuce.repository.user.status.CommentsRepository;
import com.introtuce.introtuce.repository.user.status.StatusListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {
        UserRepository.class,
        StatusListRepository.class,
        CommentsRepository.class
})
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StatusListRepository statusListRepository,
            CommentsRepository commentsRepository
    ){
        return strings -> {

        };
    }

}

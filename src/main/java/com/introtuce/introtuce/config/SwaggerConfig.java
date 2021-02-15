package com.introtuce.introtuce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket IntrotuceApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.introtuce.introtuce"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Introtuce Task API By Sai Ashish", "Spring Boot API ", "1.0", "Tos", new Contact("abcd", "abcd.com", "abcd@gmail.com"),
                "Apache Licence",
                "https://introtuce-task-by-sai.herokuapp.com/"
        );
        ApiInfo apiInfo1 = apiInfo;
        return apiInfo1;
    }

}

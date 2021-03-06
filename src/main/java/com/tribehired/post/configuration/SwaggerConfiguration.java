package com.tribehired.post.configuration;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.*;

@Profile(value = {"dev"})
@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Post Service")
                .description("Post management service")
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .version("0.0.1")
                .contact(new Contact("", "", ""))
                .build();
    }

    @Bean
    List<Tag> tags() {
        return Arrays.asList(
                new Tag("Post", "Post Management")
        );
    }

    @Bean
    public Docket customImplementation() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(DateTime.class, Date.class)
                .apiInfo(apiInfo());
        tags().forEach(tag -> docket.tags(tag));
        return docket;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

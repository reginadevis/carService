package com.casestudy.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;

import java.time.Duration;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.casestudy.controller"))
                .paths(PathSelectors.any()).build();
    }

    @Bean
    public CircuitBreakerConfigCustomizer someRemoteSvcCircuitBreaker() {
        //https://resilience4j.readme.io/docs/circuitbreaker
        return CircuitBreakerConfigCustomizer
                .of("getCar", builder -> {
                    builder.slidingWindowSize(5);
                    builder.failureRateThreshold(3);
                    builder.waitDurationInOpenState(Duration.ofSeconds(10));
                    builder.slowCallDurationThreshold(Duration.ofSeconds(5));
                    builder.slowCallRateThreshold(100);
                    builder.permittedNumberOfCallsInHalfOpenState(2);
                    builder.slidingWindowType(COUNT_BASED);
                });
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
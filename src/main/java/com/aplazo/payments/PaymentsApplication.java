package com.aplazo.payments;

import com.aplazo.payments.utils.SwaggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@SpringBootApplication
@EnableSwagger2
public class PaymentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsApplication.class, args);
    }

    @Bean
    @Autowired
    public Docket api(ServletContext servletContext, Environment env) {
        return new SwaggerUtils().getSwaggerDocket("Payments Service",
                "This service handles payments.");
    }
}
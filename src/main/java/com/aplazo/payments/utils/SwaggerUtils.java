package com.aplazo.payments.utils;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * This is the Swagger configuration
 *
 * @author Enrique Diaz
 * */
public class SwaggerUtils {

	public Docket getSwaggerDocket(String appName, String description) {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		return docket.select()
				.paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.aplazo.payments.controller"))
                .build().apiInfo(apiInfo(appName, description));
	}

    private ApiInfo apiInfo(String appName, String description) {
        return new ApiInfo(
          appName,
          description,
          "0.0.1-SNAPSHOT",
          "Terms of service URL",
          new Contact("Enrique Diaz", "www.aplazo.com", "jd_0005@hotmail.com"),
          "License of API", "API license URL", Collections.emptyList());
    }
}
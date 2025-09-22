package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
@Configuration
	public class SwaggerConfig implements WebMvcConfigurer {

	    @Bean
	    GroupedOpenApi publicApi() {
	        return GroupedOpenApi.builder()
	                .group("v1")
	                .pathsToMatch("/api/**")  // 
	                .packagesToScan("com.example.demo") // 
	                .addOpenApiMethodFilter(method -> method.getDeclaringClass().isAnnotationPresent(RestController.class))
	                .addOpenApiCustomizer(customOpenApi())
	                .build();
	    }
	    @Bean
	    public OpenApiCustomizer customOpenApi() {
	        return openApi ->{
	            openApi.getInfo().setTitle("Api-Chat-realtime"); 
	            openApi.getInfo().setVersion("0.0.1"); 
	            openApi.getInfo().setDescription("Api Restfull + WebSocket para chat em tempo real");
	        };
	    }
	}



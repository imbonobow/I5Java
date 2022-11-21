package com.rioc.ws.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfigure extends WebMvcConfigurerAdapter {
	@Bean
	public Docket apiDocket() {
		return new Docket( DocumentationType.SWAGGER_2 )
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.rioc.ws.controllers"))
					.paths( PathSelectors.any() )
					.build();
	}
}

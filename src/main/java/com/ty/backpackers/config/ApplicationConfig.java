package com.ty.backpackers.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig {
	
	@Bean
	public Docket getDocket() {
		
		Contact contact = new Contact("Testyantra Global Team", "https://testyantraglobal.com/",
				"test@testyantraglobal.com");

		@SuppressWarnings("rawtypes")
		List<VendorExtension> vendorExtensions = new ArrayList<>();

		ApiInfo apiInfo = new ApiInfo("Backpackers Application will provide Room Booking service",
				"This has a list of information about the Backpackers Application", "1.0.2",
				"https://testyantraglobal.com/services/software-development/", contact, "Apache 2.0",
				"https://testyantraglobal.com/services/software-development/", vendorExtensions);
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ty.backpackers")).build().apiInfo(apiInfo)
				.useDefaultResponseMessages(false);
	}


}



package com.edurbs.bill.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.edurbs.bill.api.config.property.BillApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(value = BillApiProperty.class)
public class BillApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillApiApplication.class, args);
	}

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}


}

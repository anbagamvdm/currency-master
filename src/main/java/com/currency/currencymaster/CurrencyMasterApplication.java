package com.currency.currencymaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CurrencyMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyMasterApplication.class, args);
	}
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:messages/message");
	    messageSource.setCacheSeconds(10); //reload messages every 10 seconds
	    return messageSource;
	}

}

package com.stacksimplify.restservices;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

//TODO (exclude = HypermediaAutoConfiguration.class)
//added exclude because of hateoas format on individual and other in collections
//(like _link and link)... idk why it does that.
//It seems hateoas has a default way of showing things. You can with this line
//make all the same (only link and not _link)
@SpringBootApplication(exclude = HypermediaAutoConfiguration.class)
public class SpringbootBuildingblocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBuildingblocksApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver(){
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}

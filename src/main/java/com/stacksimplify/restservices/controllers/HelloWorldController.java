package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {


    private ResourceBundleMessageSource messageSource;

    @Autowired
    public HelloWorldController(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/helloworld1")
    public String helloWorld(){
        return "Hello World1";
    }
    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Karen", "Kariky", "M", "k@email.com","sd");
    }

    @GetMapping("/hello-int")
    public String publicMessagesInI18NFormat(@RequestHeader(name = "Accept-Language", required = false) String locale){
        return messageSource.getMessage("label.hello",null, new Locale(locale));
    }

    @GetMapping("/hello-int2")
    public String publicMessagesInI18NFormat2(){
        return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
    }
}

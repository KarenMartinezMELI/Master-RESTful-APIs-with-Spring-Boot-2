package com.stacksimplify.restservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// With https://editor.swagger.io/ and metadata you can see info about error with standars :)

@Import(BeanValidatorPluginsConfiguration.class)
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(getApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.stacksimplify.restservices"))
        .paths(PathSelectors.ant("/users/**"))
        .build();
  }
  //Swagger Metadata: http://localhost:8080/v2/api-docs
  //Swagger UI URL: http://localhost:8080/swagger-ui.html

  private ApiInfo getApiInfo(){
    return new ApiInfoBuilder()
        .title("Stack Simplify User Management Service")
        .description("This page lists all API's for User Management")
        .version("2.0")
        .contact(new Contact("Karen","N/A","N/A@na.com"))
        .license("License 2.0")
        .licenseUrl("N/A@na.com")
        .build();
  }
}

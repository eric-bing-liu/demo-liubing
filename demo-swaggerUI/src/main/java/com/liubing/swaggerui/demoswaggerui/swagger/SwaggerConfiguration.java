package com.liubing.swaggerui.demoswaggerui.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;


@Configuration
@EnableOpenApi
public class SwaggerConfiguration implements WebMvcConfigurer {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("apiInfo.title") //一般是系统名称
                .description("apiInfo.description")  //项目描述
                .contact(new Contact("Contact.name", "Contact.url", "Contact.email")) //联系人信息
                .version("1.0")//版本号
                .build();
    }

//    private SecurityScheme basicAuthScheme() {
//        return HttpAuthenticationScheme.BASIC_AUTH_BUILDER.name("dc_auth").build();
//    }
//
//    private SecurityScheme apiKeyScheme() {
//        return new ApiKey("dc_token", "Authorization", In.HEADER.toValue());
//    }
//    private List<SecurityScheme> securitySchemes() {
//        ArrayList<SecurityScheme> auth = new ArrayList<>();
//        auth.add(basicAuthScheme());
//        auth.add(apiKeyScheme());
//        return auth;
//    }
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liubing.swaggerui.demoswaggerui.controller"))//范围
                .paths(PathSelectors.any())
                .build()
                .protocols(new LinkedHashSet<>(Arrays.asList("HTTPS", "HTTP")))
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts())
                ;
    }
}

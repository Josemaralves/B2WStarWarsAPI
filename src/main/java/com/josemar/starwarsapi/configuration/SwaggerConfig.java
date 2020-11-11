package com.josemar.starwarsapi.configuration;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Star Wars Planet API")
                .description("API dos planetas do universo Star Wars")
                .version("1.0")
                .contact(new Contact("Josemar Alves", "https://www.linkedin.com/in/josemar-alves/", "henriquejosemar@gmail.com"))
                .build();
    }

}

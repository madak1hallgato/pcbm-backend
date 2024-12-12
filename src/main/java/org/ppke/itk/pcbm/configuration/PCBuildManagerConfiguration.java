package org.ppke.itk.pcbm.configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PCBuildManagerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PCBM - PC Build Manager")
                        .version("0.0.1-SNAPSHOT")
                        .description("A web application that allows users to browse available PC parts, " +
                                "configure simple PC builds, and manage component inventory.")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }

}

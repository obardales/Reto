package com.reto.obardales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = {"com.reto.obardales"})
public class RetoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetoApplication.class, args);
    }

}

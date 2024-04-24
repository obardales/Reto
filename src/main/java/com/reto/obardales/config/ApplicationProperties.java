package com.reto.obardales.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value("${locale}")
    public String locale;

    @Value("${jwt.secret}")
    public String jwtsecret;

}

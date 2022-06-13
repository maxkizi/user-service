package org.maxkizi.userservice.config;

import org.mapstruct.factory.Mappers;
import org.maxkizi.userservice.converter.ApplicationUserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationUserConverterConfig {

    @Bean
    public ApplicationUserConverter converter(){
        return Mappers.getMapper(ApplicationUserConverter.class);
    }
}

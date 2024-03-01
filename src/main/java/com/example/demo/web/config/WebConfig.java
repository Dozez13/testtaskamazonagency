package com.example.demo.web.config;


import com.example.demo.core.serializer.AppLocalDateDeserializer;
import com.example.demo.core.serializer.AppLocalDateSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class WebConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public Module appLocalDateTime(AppLocalDateSerializer appLocalDateSerializer, AppLocalDateDeserializer appLocalDateDeserializer) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, appLocalDateSerializer);
        module.addDeserializer(LocalDate.class, appLocalDateDeserializer);
        return module;
    }
}

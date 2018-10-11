package com.thoughtworks.aceleradora.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
public class ConfiguracaoMvc implements WebMvcConfigurer {
    private static final DateTimeFormatter PADRAO_DE_DATA_BRASILEIRO = ofPattern("dd/MM/yyyy");
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar jsr310 = new DateTimeFormatterRegistrar();
        jsr310.setDateFormatter(PADRAO_DE_DATA_BRASILEIRO);
        jsr310.registerFormatters(registry);
    }
}

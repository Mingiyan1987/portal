package ru.basanov.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "ru.basanov.controller")
@Import(DataSourceConfiguration.class)
public class AppConfig {
}

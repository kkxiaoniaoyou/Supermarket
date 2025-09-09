package com.cbz.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "custom")
@Data
public class CustomConfig {

    public static final String TO_MESSAGE = "TO_MESSAGE";
    public static final String TO_DATA = "TO_DATA";

    private String paramsCheckResult;
}

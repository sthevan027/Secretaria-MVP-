package com.devloop.secretariat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "my-app.gemini")
public record AppGeminiProperties(String url, String model, String apikey) {}

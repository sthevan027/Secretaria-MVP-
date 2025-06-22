package com.devloop.secretariat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "my-app.evolution")
public record AppEvolutionProperties(String url) {}

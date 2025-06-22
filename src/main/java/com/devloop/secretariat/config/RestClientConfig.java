package com.devloop.secretariat.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Configuration
public class RestClientConfig {
    @Bean
    @Qualifier("geminiRestClient")
    public RestClient geminiRestClient(AppGeminiProperties appGeminiProperties) {
        String baseUrl = appGeminiProperties.url() + "/" + appGeminiProperties.model() + ":generateContent?key={key}";

        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("Content-Type", "application/json");
                    httpHeaders.add("Accept", "application/json");
                })
                .defaultUriVariables(Map.of("key", appGeminiProperties.apikey()))
                .build();
    }

    @Bean
    @Qualifier("evolutionRestClient")
    public RestClient evolutionRestClient(AppEvolutionProperties appEvolutionProperties) {
        return RestClient.builder()
                .baseUrl(appEvolutionProperties.url())
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("Content-Type", "application/json");
                    httpHeaders.add("Accept", "application/json");
                })
                .build();
    }
}

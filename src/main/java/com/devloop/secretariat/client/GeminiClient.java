package com.devloop.secretariat.client;

import com.devloop.secretariat.dto.GeminiGenerateContentRequestDTO;
import com.devloop.secretariat.dto.GeminiGenerateContentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GeminiClient {
    @Autowired
    @Qualifier("geminiRestClient")
    private RestClient restClient;

    public GeminiGenerateContentResponseDTO generateContent(GeminiGenerateContentRequestDTO requestDTO) {
        return restClient
                .post()
                .body(requestDTO)
                .retrieve()
                .body(GeminiGenerateContentResponseDTO.class);
    }
}

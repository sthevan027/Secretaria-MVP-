package com.devloop.secretariat.service;

import com.devloop.secretariat.client.GeminiClient;
import com.devloop.secretariat.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeminiService {
    private final GeminiSystemInstructionDTO systemInstruction;
    private final List<GeminiToolsDTO> tools;
    private final GeminiClient geminiClient;

    public GeminiService(GeminiSystemInstructionDTO systemInstruction, List<GeminiToolsDTO> tools,
                         GeminiClient geminiClient) {
        this.systemInstruction = systemInstruction;
        this.tools = tools;
        this.geminiClient = geminiClient;
    }

    public GeminiGenerateContentResponseDTO generateContent(String message) {
        GeminiContentsDTO.PartDTO partDTO = new GeminiContentsDTO.PartDTO(message);
        List<GeminiContentsDTO> geminiContentsDTO = List.of(new GeminiContentsDTO(List.of(partDTO)));
        GeminiGenerateContentRequestDTO geminiGenerateContentRequestDTO =
                new GeminiGenerateContentRequestDTO(systemInstruction, tools, geminiContentsDTO);

        return geminiClient.generateContent(geminiGenerateContentRequestDTO);
    }
}

package com.devloop.secretariat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GeminiGenerateContentRequestDTO(
        @JsonProperty("system_instruction")
        GeminiSystemInstructionDTO systemInstruction,
        List<GeminiToolsDTO> tools,
        List<GeminiContentsDTO> contents
) {}

package com.devloop.secretariat.dto;

import java.util.List;

public record GeminiSystemInstructionDTO(List<PartDTO> parts) {
    public record PartDTO(String text) {}
}

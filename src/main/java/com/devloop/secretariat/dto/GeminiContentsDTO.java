package com.devloop.secretariat.dto;

import java.util.List;

public record GeminiContentsDTO(List<PartDTO> parts) {
    public record PartDTO(String text) {}
}

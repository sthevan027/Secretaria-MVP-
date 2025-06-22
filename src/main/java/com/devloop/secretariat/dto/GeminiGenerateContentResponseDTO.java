package com.devloop.secretariat.dto;

import java.util.List;
import java.util.Map;

public record GeminiGenerateContentResponseDTO(List<Candidate> candidates) {
    public record Candidate(Content content) {
        public record Content(List<Part> parts) {
            public record Part(String text, FunctionCall functionCall) {
                public record FunctionCall(String name, Map<String, String> args) {}
            }
        }
    }
}

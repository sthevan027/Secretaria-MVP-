package com.devloop.secretariat.dto;

import java.util.List;
import java.util.Map;

public record GeminiToolsDTO(List<FunctionDeclaration> functionDeclarations) {

    public record FunctionDeclaration(
            String name,
            String description,
            Parameters parameters
    ) {}

    public record Parameters(
            String type,
            Map<String, Property> properties,
            List<String> required
    ) {}

    public record Property(
            String type,
            String description
    ) {}
}

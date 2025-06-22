package com.devloop.secretariat.config;

import com.devloop.secretariat.dto.GeminiSystemInstructionDTO;
import com.devloop.secretariat.dto.GeminiToolsDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class GeminiConfig {
    @Bean
    GeminiSystemInstructionDTO getSystemInstruction() throws IOException, FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("gemini/system_instruction.json")) {
            if (is != null) {
                return mapper.readValue(is, GeminiSystemInstructionDTO.class);
            } else {
                throw new FileNotFoundException("Arquivo gemini/system_instruction.json não encontrado.");
            }
        }
    }

    @Bean
    List<GeminiToolsDTO> getTools() throws IOException, FileNotFoundException {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("gemini/tools.json")) {
            if (is != null) {
                return mapper.readValue(is, new TypeReference<>() {});
            } else {
                throw new FileNotFoundException("Arquivo gemini/tools.json não encontrado.");
            }
        }
    }
}

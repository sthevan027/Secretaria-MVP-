package com.devloop.secretariat.service;

import com.devloop.secretariat.client.EvolutionClient;
import com.devloop.secretariat.dto.EvolutionMessageSendTextRequestDTO;
import com.devloop.secretariat.dto.EvolutionMessagesUpsertResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class EvolutionService {
    private final EvolutionClient evolutionClient;

    public EvolutionService(EvolutionClient evolutionClient) {
        this.evolutionClient = evolutionClient;
    }

    public void sendText(EvolutionMessagesUpsertResponseDTO evolutionMessagesUpsertResponseDTO,
                         EvolutionMessageSendTextRequestDTO evolutionMessageSendTextRequestDTO) {
        evolutionClient.sendText(evolutionMessagesUpsertResponseDTO, evolutionMessageSendTextRequestDTO);
    }
}

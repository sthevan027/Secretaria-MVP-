package com.devloop.secretariat.client;

import com.devloop.secretariat.dto.EvolutionMessageSendTextRequestDTO;
import com.devloop.secretariat.dto.EvolutionMessagesUpsertResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class EvolutionClient {
    @Autowired
    @Qualifier("evolutionRestClient")
    private RestClient restClient;

    public void sendText(EvolutionMessagesUpsertResponseDTO messagesUpsertResponseDTO,
                         EvolutionMessageSendTextRequestDTO messageSendTextRequestDTO) {
        restClient.post().uri("/message/sendText/{instance}", messagesUpsertResponseDTO.instance())
                .header("apikey", messagesUpsertResponseDTO.apikey()).body(messageSendTextRequestDTO)
                .retrieve().toBodilessEntity();
    }
}

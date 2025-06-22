package com.devloop.secretariat.controller;

import com.devloop.secretariat.dto.EvolutionMessagesUpsertResponseDTO;
import com.devloop.secretariat.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/wh")
    public void webhook(@RequestBody EvolutionMessagesUpsertResponseDTO evolutionMessagesUpsertResponseDTO) {
        chatService.enviarMensagem(evolutionMessagesUpsertResponseDTO);
    }
}

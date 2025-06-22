package com.devloop.secretariat.service;

import com.devloop.secretariat.dto.AgendaDTO;
import com.devloop.secretariat.dto.EvolutionMessageSendTextRequestDTO;
import com.devloop.secretariat.dto.EvolutionMessagesUpsertResponseDTO;
import com.devloop.secretariat.dto.GeminiGenerateContentResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ChatService {
    private final GeminiService geminiService;
    private final EvolutionService evolutionService;
    private final AgendaService agendaService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ChatService(GeminiService geminiService, EvolutionService evolutionService, AgendaService agendaService) {
        this.geminiService = geminiService;
        this.evolutionService = evolutionService;
        this.agendaService = agendaService;
    }

    public void enviarMensagem(EvolutionMessagesUpsertResponseDTO evolutionMessagesUpsertResponseDTO) {
        String messageFromUser = evolutionMessagesUpsertResponseDTO.data().message().conversation();
        GeminiGenerateContentResponseDTO geminiGenerateContentResponseDTO = geminiService.generateContent(messageFromUser);
        String llmResponse = geminiGenerateContentResponseDTO.candidates().getFirst().content().parts().getFirst().text();
        var functionCall = geminiGenerateContentResponseDTO.candidates().getFirst().content().parts().getFirst().functionCall();

        String senderNumber = evolutionMessagesUpsertResponseDTO.data().key().remoteJid().split("@")[0];
        String senderName = evolutionMessagesUpsertResponseDTO.data().pushName();

        if (functionCall != null) {
            if (functionCall.name().contains("agendamento_reuniao")) {
                String dataHoraStr = functionCall.args().get("data") + " " + functionCall.args().get("horario");
                LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
                AgendaDTO agendaDTO = new AgendaDTO(senderNumber, senderName, functionCall.args().get("assunto"), dataHora);

                try {
                    AgendaDTO agendaSavedDTO = agendaService.criarAgenda(agendaDTO);
                    llmResponse = "Agenda Salva com sucesso! \n\n" +
                            "\uD83D\uDCC5 Data: " + agendaSavedDTO.dataHora().format(formatter) + "\n" +
                            "\uD83D\uDCDD Assunto: " + agendaSavedDTO.assunto() + "\n";
                } catch (IllegalStateException e) {
                    llmResponse = e.getMessage();
                }
            }

            if (functionCall.name().contains("listar_reunioes")) {
                StringBuilder sb = new StringBuilder();
                sb.append("Suas agendas:\n\n");
                agendaService.findAllBySenderNumber(senderNumber).forEach(a -> sb.append("\uD83D\uDCDD Assunto: ").append(a.assunto()).append("\n")
                        .append("\uD83D\uDCDD Data: ").append(a.dataHora().format(formatter)).append("\n")
                        .append("\n"));

                llmResponse = sb.toString();
            }
        }

        EvolutionMessageSendTextRequestDTO evolutionMessageSendTextRequestDTO =
                new EvolutionMessageSendTextRequestDTO(senderNumber, llmResponse, 2000);

        evolutionService.sendText(evolutionMessagesUpsertResponseDTO, evolutionMessageSendTextRequestDTO);
    }
}

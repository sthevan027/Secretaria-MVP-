package com.devloop.secretariat.service;

import com.devloop.secretariat.domain.Agenda;
import com.devloop.secretariat.dto.AgendaDTO;
import com.devloop.secretariat.repository.AgendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AgendaService {
    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public AgendaDTO criarAgenda(AgendaDTO agendaDTO) {
        if (agendaDTO.dataHora().isBefore(java.time.LocalDateTime.now())) {
            throw new IllegalStateException("Não é possível agendar para uma data/hora anterior à atual!");
        }

        Agenda agenda = new Agenda();
        BeanUtils.copyProperties(agendaDTO, agenda);

        agendaRepository.findAllBySenderNumber(agenda.getSenderNumber()).forEach(a -> {
            if (a.getDataHora().equals(agendaDTO.dataHora())) {
                throw new IllegalStateException("Já existe uma agenda com essa data e essa hora!");
            }
        });

        agendaRepository.save(agenda);

        return agendaDTO;
    }

    public List<AgendaDTO> findAllBySenderNumber(String senderNumber) {
        return agendaRepository.findAllBySenderNumber(senderNumber)
                .stream()
                .map(a -> new AgendaDTO(a.getSenderNumber(), a.getSenderName(), a.getAssunto(), a.getDataHora()))
                .toList();
    }
}

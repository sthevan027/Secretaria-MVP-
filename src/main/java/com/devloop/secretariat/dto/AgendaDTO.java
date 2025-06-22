package com.devloop.secretariat.dto;

import java.time.LocalDateTime;

public record AgendaDTO (String senderNumber, String senderName, String assunto, LocalDateTime dataHora) {}
